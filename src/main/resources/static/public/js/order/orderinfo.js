$(function () {
    $("option[value='']").prop("selected",true);
    vm.orderId = T.p("orderId");
    if(vm.orderId != null && vm.orderId != undefined){
        getDetail(vm.orderId);
    }
    $("#jqGrid").jqGrid({
        url: '../orderinfo/list',
        datatype: "json",
        colModel: [
            {label: 'id', name: 'id', index: 'id', width: 50, key: true},
            {label: '用户名', name: 'username', index: 'username', width: 80,
                formatter: function(value){
                    if(value != null){
                        return value.substr(0, 10);
                    }
                }
            },
            {label: '用户手机号', name: 'userPhone', index: 'user_phone', width: 80},
            {
                label: '订单金额', name: 'amount', index: 'amount', width: 80,
                formatter: function (value, options, row) {
                    return value + "元";
                }
            },
            {
                label: '订单状态', name: 'status', index: 'status', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '待支付';
                    } else if (value === 20) {
                        return '待配送'
                    } else if (value == 30) {
                        return '配送中';
                    } else if (value == 40) {
                        return '已到达';
                    } else if (value == 50) {
                        return '已关闭';
                    } else if(value == 60){
                        return "已评论";
                    }
                }
            },
            {
                label: '配送状态', name: 'type', index: 'type', width: 80,
                formatter: function (value, options, row) {
                    if (value === 10) {
                        return '<font color="green">正常</font>';
                    } else if (value === 20) {
                        return '<font color="red">异常</font>'
                    } else {
                        return '未知';
                    }
                }
            },
            {label: '配送员', name: 'deliveryDistributorName', index: 'delivery_distributor_name', width: 80},
            {label: '备注', name: 'remark', index: 'remark', width: 80,
                formatter: function(value){
                    if(value != null){
                        return value.substr(0, 10);
                    }
                }
             },
            {label: '下单时间', name: 'creationTime', index: 'creation_time', width: 80}
        ],
        viewrecords: true,
        height: 385,
        rowNum: 10,
        rowList: [10, 30, 50],
        rownumbers: true,
        rownumWidth: 25,
        autowidth: true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader: {
            root: "page.list",
            page: "page.currPage",
            total: "page.totalPage",
            records: "page.totalCount"
        },
        prmNames: {
            page: "page",
            rows: "limit",
            order: "order"
        },
        gridComplete: function () {
            //隐藏grid底部滚动条
            $("#jqGrid").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
        }
    });

});

var vm = new Vue({
    el: '#rrapp',
    data: {
        showList: true,
        showDetail: false,//显示配送单和订单商品信息
        title: null,
        q:{
            userName: null,
            userPhone: null,
            status: null,
            type: null
        },
        orderInfo: {},
        orderDeliveryInfo: {},
        orderProductList: [],
        statusSelect:[
            {id:"10",name:"待支付"},
            {id:"20",name:"待配送"},
            {id:"30",name:"配送中"},
            {id:"40",name:"已到达"},
            {id:"50",name:"已关闭"},
            {id:"60",name:"已评论"}
        ],
        orderId: null
    },
    methods: {
        query: function () {
            vm.reload();
        },
        reset: function () {
            $("#userName").val("");
            $("#userPhone").val("");
            $("#statusSelect").val("");
            $("#typeSelect").val("");
            vm.q.userName = "";
            vm.q.userPhone = "";
            vm.q.status = "";
            vm.q.type = "";
            vm.reload();
        },
        detail: function () {
            var id = getSelectedRow();
            if (id == null) {
                return;
            }
            /*vm.showList = false;
            vm.showDetail = true;
            vm.title = "详情";
            vm.getInfo(id);
            vm.getOrderDeliveryInfo(id);
            vm.getOrderProductInfo(id);*/
            getDetail(id);
        },
        handDistribute: function () {
            var id = getSelectedRow();
            var gr = jQuery("#jqGrid").jqGrid('getRowData', id);
            if (id == null) {
                return;
            }
            var url = "./ordersubmit.html?orderId="+id
            // encodeURI 编码
            window.location.assign(encodeURI(url));
        },
        handle: function (event) {
            var remark = prompt("请填写手工处理备注信息", "")
            $.ajax({
                type: "POST",
                url: "../orderinfo/handle?orderId=" + vm.orderInfo.id + "&remark=" + remark,
                contentType: "application/json",
                data:{
                    "orderId":vm.orderInfo.id,
                    "remark":remark
                },
                success: function (r) {
                    if (r.code == 0) {
                        alert('操作成功', function (index) {
                            window.location.reload();
                        });
                    } else {
                        alert(r.msg);
                    }
                }
            });
        },
        getInfo: function (id) {
            $.get("../orderinfo/info/" + id, function (r) {
                vm.orderInfo = r.orderInfo;
            });
        },
        getOrderDeliveryInfo: function (id) {
            $.get("../orderdeliveryinfo/infoByOrderId/" + id, function (r) {
                vm.orderDeliveryInfo = r.orderDeliveryInfo;
            });
        },
        getOrderProductInfo: function (id) {
            $("#jqGridProduct").jqGrid({
                url: '../orderproductdetail/listByOrderId',
                postData: {
                    'id': id
                },
                datatype: "json",
                colModel: [
                    {label: 'id', name: 'productInfoId', index: 'productInfoId', width: 50, key: true},
                    {label: '商品名称', name: 'productName', index: 'product_name', width: 80},
                    {label: '商品数量', name: 'count', index: 'count', width: 80},
                ],
                viewrecords: true,
                height: 385,
                width: 600,
                rowNum: 10,
                rowList: [10, 30, 50],
                rownumbers: true,
                rownumWidth: 25,
                autowidth: false,
                multiselect: false,
                pager: "#jqGridProductPager",
                jsonReader: {
                    root: "page.list",
                    page: "page.currPage",
                    total: "page.totalPage",
                    records: "page.totalCount"
                },
                prmNames: {
                    page: "page",
                    rows: "limit",
                    order: "order"
                },
                gridComplete: function () {
                    //隐藏grid底部滚动条
                    $("#jqGridProduct").closest(".ui-jqgrid-bdiv").css({"overflow-x": "hidden"});
                    //去掉分页元素
                    $("#jqGridProductPager").remove();
                }
            });
        },
        reload: function (event) {
            vm.showList = true;
            var page = $("#jqGrid").jqGrid('getGridParam', 'page');
            $("#jqGrid").jqGrid('setGridParam', {
                postData:{
                    userName: vm.q.userName,
                    userPhone: vm.q.userPhone,
                    status: vm.q.status,
                    type: vm.q.type
                },
                page: page
            }).trigger("reloadGrid");
        }
    }
});

function getDetail(orderId) {
    vm.showList = false;
    vm.showDetail = true;
    vm.title = "详情";
    vm.getInfo(orderId);
    vm.getOrderDeliveryInfo(orderId);
    vm.getOrderProductInfo(orderId);
}