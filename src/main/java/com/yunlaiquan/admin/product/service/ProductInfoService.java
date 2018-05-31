package com.yunlaiquan.admin.product.service;


import com.yunlaiquan.admin.product.entity.ProductInfoEntity;

import java.util.List;
import java.util.Map;

/**
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
public interface ProductInfoService {
	
	ProductInfoEntity queryObject(Integer id);
	
	List<ProductInfoEntity> queryList(Map<String, Object> map);
	
	int queryTotal(Map<String, Object> map);
	
	void save(ProductInfoEntity productInfo);
	
	void update(ProductInfoEntity productInfo);
	
	void delete(Integer id);
	
	void deleteBatch(Integer[] ids);
}