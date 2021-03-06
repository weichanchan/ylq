package com.yunquanlai.api.comsumer;

import com.yunquanlai.admin.user.entity.UserAddressEntity;
import com.yunquanlai.admin.user.entity.UserInfoEntity;
import com.yunquanlai.admin.user.service.UserAddressService;
import com.yunquanlai.utils.DistanceUtils;
import com.yunquanlai.utils.R;
import com.yunquanlai.utils.annotation.LoginUser;
import io.swagger.annotations.*;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.math.BigDecimal;
import java.util.*;

/**
 * 用户地址管理接口
 *
 * @author weicc
 * @date 2018/5/30 18:30
 * @desc
 **/
@RestController
@RequestMapping("/client/api")
@Api(value = "客户端-地址", description = "配送地址相关接口")
public class ApiAddressController {

    @Autowired
    private UserAddressService userAddressService;

    /**
     * 获取用户地址信息
     *
     * @return
     */
    @PostMapping("getMinDistanceAddress")
    @ApiOperation(value = "根据坐标获取用户距离这个坐标最近的收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "double", name = "locationX", value = "腾讯地图坐标（loc.latlng.lat）", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "double", name = "locationY", value = "腾讯地图坐标（loc.latlng.lng）", required = true),
    })
    public R getMinDistanceAddress(@LoginUser @ApiIgnore UserInfoEntity user, @RequestParam BigDecimal locationX, @RequestParam BigDecimal locationY) {
        List<UserAddressEntity> userAddressEntityList = userAddressService.queryByUserId(user.getId());
        if (userAddressEntityList.size() > 0) {
            DistanceUtils.sortAddressEndpoint(locationX, locationY, userAddressEntityList);
            return R.ok().put("minUserAddress", userAddressEntityList.iterator().next());
        }
        return R.ok().put("minUserAddress", null);
    }

    /**
     * 获取用户地址信息
     *
     * @return
     */
    @PostMapping("getAddress")
    @ApiOperation(value = "获取用户收货地址")
    @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true)
    public R getAddress(@LoginUser @ApiIgnore UserInfoEntity user) {
        List<UserAddressEntity> userAddressEntityList = userAddressService.queryByUserId(user.getId());
        return R.ok().put("userAddressEntityList", userAddressEntityList);
    }

    /**
     * 获取用户地址信息
     *
     * @return
     */
    @PostMapping("saveAddress")
    @ApiOperation(value = "添加用户收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "userAddressId", value = "用户地址ID（添加不用填）"),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "name", value = "姓名", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "phone", value = "手机号", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "address", value = "坐标地址", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "addressDetail", value = "详细地址", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "double", name = "locationX", value = "腾讯地图坐标（loc.latlng.lat）", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "double", name = "locationY", value = "腾讯地图坐标（loc.latlng.lng）", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "int", name = "sex", value = "性别，10：男，20：女", required = true)
    })
    public R saveAddress(@LoginUser @ApiIgnore UserInfoEntity user,
                         @RequestBody UserAddressEntity userAddressEntity) {
        userAddressEntity.setUserInfoId(user.getId());
        // 在这里没有锁表，因为保持/修改地址也不是高并发操作
        if (userAddressEntity.getId() == null) {
            userAddressService.save(userAddressEntity);
        } else {
            if (user.getId().longValue() != userAddressEntity.getUserInfoId().longValue()) {
                return R.error("不能修改别人的地址");
            }
            userAddressService.update(userAddressEntity);
        }
        return R.ok();
    }

    /**
     * 获取用户地址信息
     *
     * @return
     */
    @PostMapping("removeAddress")
    @ApiOperation(value = "获取用户收货地址")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "header", name = "token", value = "token", required = true),
            @ApiImplicitParam(paramType = "query", dataType = "string", name = "userAddressId", value = "用户地址ID"),
    })
    public R removeAddress(@LoginUser @ApiIgnore UserInfoEntity user, @RequestParam Long userAddressId) {
        UserAddressEntity userAddressEntity = userAddressService.queryObject(userAddressId);
        if (user.getId().longValue() != userAddressEntity.getUserInfoId().longValue()) {
            return R.error("不能删除别人的地址");
        }
        userAddressService.delete(userAddressId);
        return R.ok();
    }
}
