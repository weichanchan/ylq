package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.product.service.ProductInfoService;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.IgnoreAuth;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

/**
 * @author weicc
 * @date 2018/5/30 21:15
 * @desc
 **/
@RestController
@RequestMapping("/api")
@Api("商品接口")
public class ApiProductController {
    @Autowired
    private ProductInfoService productInfoService;

    /**
     * 获取商品首页轮播图
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductBanner")
    @ApiOperation(value = "商品轮播图信息")
    public R getProductBanner() {
        return R.ok();
    }

    /**
     * 获取商品品牌信息
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductBrand")
    @ApiOperation(value = "商品品牌信息")
    public R getProductBrand() {
        return R.ok();
    }

    /**
     * 获取商品详细信息
     *
     * @return
     */
    @IgnoreAuth
    @PostMapping("getProductDetail")
    @ApiOperation(value = "获取商品详细信息")
    public R getProductDetail(@ApiParam(value = "商品 ID") Integer id) {
        return R.ok();
    }

    /**
     * 商品查询
     */
    @IgnoreAuth
    @PostMapping("queryProduct")
    @ApiOperation(value = "商品查询")
    public R trackProduct(@ApiParam(value = "商品名称") String name,
                          @ApiParam(value = "商品品牌(传ID)")Integer brandId,
                          @ApiParam(value = "排序类型，10：按价格排序，20：按销量排序")Integer orderType,
                          @ApiParam(value = "桶型，10：一次性桶，20：可回收桶，不传为全部桶形")Integer bucketType,
                          @ApiParam(value = "翻页数")Integer page,
                          @ApiParam(value = "查询条数")Integer limit) {
        return R.ok();

    }
}