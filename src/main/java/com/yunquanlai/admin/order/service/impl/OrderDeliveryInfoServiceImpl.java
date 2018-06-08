package com.yunquanlai.admin.order.service.impl;

import com.yunquanlai.admin.delivery.dao.DeliveryDistributorDao;
import com.yunquanlai.admin.delivery.entity.DeliveryDistributorEntity;
import com.yunquanlai.admin.order.dao.OrderInfoDao;
import com.yunquanlai.admin.order.entity.OrderInfoEntity;
import com.yunquanlai.utils.RRException;
import com.yunquanlai.utils.validator.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.yunquanlai.admin.order.dao.OrderDeliveryInfoDao;
import com.yunquanlai.admin.order.entity.OrderDeliveryInfoEntity;
import com.yunquanlai.admin.order.service.OrderDeliveryInfoService;
import org.springframework.transaction.annotation.Transactional;


@Service("orderDeliveryInfoService")
@Transactional(rollbackFor = Exception.class)
public class OrderDeliveryInfoServiceImpl implements OrderDeliveryInfoService {
    @Autowired
    private OrderDeliveryInfoDao orderDeliveryInfoDao;

    @Autowired
    private DeliveryDistributorDao deliveryDistributorDao;

    @Autowired
    private OrderInfoDao orderInfoDao;

    @Override
    public OrderDeliveryInfoEntity queryObject(Long id) {
        return orderDeliveryInfoDao.queryObject(id, false);
    }

    @Override
    public List<OrderDeliveryInfoEntity> queryList(Map<String, Object> map) {
        return orderDeliveryInfoDao.queryList(map);
    }

    @Override
    public int queryTotal(Map<String, Object> map) {
        return orderDeliveryInfoDao.queryTotal(map);
    }

    @Override
    public void save(OrderDeliveryInfoEntity orderDeliveryInfo) {
        orderDeliveryInfoDao.save(orderDeliveryInfo);
    }

    @Override
    public void update(OrderDeliveryInfoEntity orderDeliveryInfo) {
        orderDeliveryInfoDao.update(orderDeliveryInfo);
    }

    @Override
    public void delete(Integer id) {
        orderDeliveryInfoDao.delete(id);
    }

    @Override
    public void deleteBatch(Integer[] ids) {
        orderDeliveryInfoDao.deleteBatch(ids);
    }

    @Override
    public List<OrderDeliveryInfoEntity> queryByDistributorId(Map<String, Object> filter) {
        return orderDeliveryInfoDao.queryByDistributorId(filter);
    }

    @Override
    public void orderDelivery(DeliveryDistributorEntity deliveryDistributorEntity, OrderDeliveryInfoEntity orderDeliveryInfoEntity) {

        orderDeliveryInfoEntity = orderDeliveryInfoDao.queryObject(orderDeliveryInfoEntity.getId(), true);
        Assert.isNull(orderDeliveryInfoEntity, "找不到配送单信息");
        Assert.isEqual(orderDeliveryInfoEntity.getStatus(), OrderDeliveryInfoEntity.STATUS_ON_DELIVERY, "配送单不是配送中状态，无法标记送达");
        orderDeliveryInfoEntity.setStatus(OrderDeliveryInfoEntity.STATUS_DELIVERY_END);
        orderDeliveryInfoDao.update(orderDeliveryInfoEntity);
        deliveryDistributorEntity = deliveryDistributorDao.queryObject(deliveryDistributorEntity.getId(), true);
        Assert.isNull(orderDeliveryInfoEntity, "找不到配送员信息");
        // 减掉配送员 当前配送订单数。
        deliveryDistributorEntity.setOrderCount(deliveryDistributorEntity.getOrderCount() - 1);
        deliveryDistributorDao.update(deliveryDistributorEntity);
        OrderInfoEntity orderInfoEntity = orderInfoDao.queryObject(orderDeliveryInfoEntity.getOrderInfoId(), true);
        Assert.isNull(orderDeliveryInfoEntity, "找不到订单信息");
        orderInfoEntity.setStatus(OrderInfoEntity.STATUS_DELIVERY_END);
        // TODO 记录送达时间
        orderInfoDao.update(orderInfoEntity);
    }

    @Override
    public OrderDeliveryInfoEntity queryObjectByOrderId(Long orderId) {
        return orderDeliveryInfoDao.queryObjectByOrderId(orderId,false);
    }

    @Override
    public void recyclingEmptyBarrels(Integer number, Long orderDeliveryId) {
        // TODO 查询配送单信息，从而查到用户信息
        // TODO 判断用户持有空桶数是否大于要回收的空桶
        // TODO 扣除用户空桶数，释放用户押金
    }

}
