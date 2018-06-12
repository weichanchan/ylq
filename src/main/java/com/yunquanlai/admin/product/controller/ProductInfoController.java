package com.yunquanlai.admin.product.controller;

import com.yunquanlai.admin.comment.dao.CommentProductDao;
import com.yunquanlai.admin.comment.entity.CommentProductEntity;
import com.yunquanlai.admin.common.AbstractController;
import com.yunquanlai.admin.product.entity.ProductDetailEntity;
import com.yunquanlai.admin.product.entity.ProductInfoEntity;
import com.yunquanlai.admin.product.entity.ProductInfoVO;
import com.yunquanlai.admin.product.service.ProductInfoService;
import com.yunquanlai.utils.PageUtils;
import com.yunquanlai.utils.Query;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.validator.Assert;
import com.yunquanlai.utils.validator.ValidatorUtils;
import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * 商品信息表表
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2018-05-30 17:16:47
 */
@RestController
@RequestMapping("productinfo")
public class ProductInfoController extends AbstractController {
	@Autowired
	private ProductInfoService productInfoService;

    @Autowired
	private CommentProductDao commentProductDao;
	/**
	 * 列表
	 */
	@RequestMapping("/list")
	@RequiresPermissions("productinfo:list")
	public R list(@RequestParam Map<String, Object> params){
		//查询列表数据
        Query query = new Query(params);

		List<ProductInfoEntity> productInfoList = productInfoService.queryList(query);
		int total = productInfoService.queryTotal(query);
		
		PageUtils pageUtil = new PageUtils(productInfoList, total, query.getLimit(), query.getPage());
		
		return R.ok().put("page", pageUtil);
	}
	
	
	/**
	 * 信息
	 */
	@RequestMapping("/info/{id}")
	@RequiresPermissions("productinfo:info")
	public R info(@PathVariable("id") Long id){
		ProductInfoVO productInfoVO = productInfoService.queryProductInfoVO(id);
		
		return R.ok().put("productInfo", productInfoVO);
	}
	
	/**
	 * 保存
	 */
	@RequestMapping("/save")
	@RequiresPermissions("productinfo:save")
	public R save(@RequestBody ProductInfoVO productInfoVO){

		ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
        ProductDetailEntity productDetailEntity = productInfoVO.getProductDetailEntity();
		//校验商品信息
		ValidatorUtils.validateEntity(productInfoEntity, AddGroup.class);
		ValidatorUtils.validateEntity(productDetailEntity, AddGroup.class);
		Assert.isBlank(productInfoVO.getProductDetailEntity().getBanner().replace(",",""),"至少需要一张广告轮播图");
		productInfoEntity.setCreationTime(new Date());
		productInfoEntity.setCreatorName(getUser().getUsername());
		productInfoEntity.setCreatorId(getUserId());
		productInfoVO.setProductInfoEntity(productInfoEntity);
		productInfoService.save(productInfoVO);
		
		return R.ok();
	}
	
	/**
	 * 修改
	 */
	@RequestMapping("/update")
	@RequiresPermissions("productinfo:update")
	public R update(@RequestBody ProductInfoVO productInfoVO){
		ProductInfoEntity productInfoEntity = productInfoVO.getProductInfoEntity();
		ProductDetailEntity productDetailEntity = productInfoVO.getProductDetailEntity();
        //校验商品信息
        ValidatorUtils.validateEntity(productInfoEntity, UpdateGroup.class);
		ValidatorUtils.validateEntity(productDetailEntity, UpdateGroup.class);
		Assert.isBlank(productInfoVO.getProductDetailEntity().getBanner().replace(",",""),"至少需要一张广告轮播图");

		productInfoEntity.setUpdateTime(new Date());
		productInfoEntity.setUpdateName(getUser().getUsername());
		productInfoEntity.setUpdateId(getUserId());
		productInfoVO.setProductInfoEntity(productInfoEntity);
		productInfoService.update(productInfoVO);
		
		return R.ok();
	}
	
	/**
	 * 删除
	 */
	@RequestMapping("/delete")
	@RequiresPermissions("productinfo:delete")
	public R delete(@RequestBody Long[] ids){
		productInfoService.deleteBatch(ids);
		
		return R.ok();
	}

	// TODO: 2018/6/11 通过商品查看评论
	/*@RequestMapping("/comment")
	@RequiresPermissions("productinfo:comment")
	public R commentProductList(@RequestParam Map<String, Object> params){
	    String name = params.get("name").toString();
		//查询列表数据
        Query query = new Query(params);

		List<CommentProductEntity> commentProductEntityList = commentProductDao.queryCommentProductList((Long) query.get("id"));

        int total = productInfoService.queryTotal(query);

        PageUtils pageUtil = new PageUtils(commentProductEntityList, total, query.getLimit(), query.getPage());

        return R.ok().put("page", pageUtil);
	}*/
}
