package com.yunquanlai.admin.delivery.entity;

import com.yunquanlai.utils.validator.group.AddGroup;
import com.yunquanlai.utils.validator.group.UpdateGroup;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.math.BigDecimal;


/**
 * 配送员信息
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 08:25:11
 */
public class DeliveryDistributorEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：
     */
	private Long id;
    /**
     * 设置：配送员姓名
     */
	@NotBlank(message="姓名不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String name;
    /**
     * 设置：配送员手机号
     */
	@NotBlank(message="手机号码不能为空", groups = {AddGroup.class, UpdateGroup.class})
    @Pattern(regexp="^((13[0-9])|(14[5,7,9])|(15[0-3,5-9])|(17[0,1,3,5-8])|(18[0-9])|166|198|199|(147))\\d{8}$",message="手机号码格式不正确",groups = {AddGroup.class, UpdateGroup.class})
	private String phone;
    /**
     * 设置：配送员登录密码
     */
	//@NotBlank(message="密码不能为空",groups = {AddGroup.class, UpdateGroup.class})
	//@Length(min=6,max=18,message="密码长度必须在6~18位之间",groups = {AddGroup.class, UpdateGroup.class})
	private String password;
    /**
     * 设置：配送员生日
     */
	@NotBlank(message="生日不能为空", groups = {AddGroup.class, UpdateGroup.class})
	private String birthday;
    /**
     * 设置：用于点对点登录时的推送，由APP在登录的时候一起上传
     */
	private String clientId;
    /**
     * 设置：当前状态，10：可配送，20：不可配送
     */
	private Integer status;
	/**
	 * 设置：登录平台，10：安卓，20：IOS
	 */
	private Integer platform;
	/**
	 * 配送员获得分润
	 */
	private BigDecimal amount;
    /**
     * 当前配送订单数
     */
    private Integer orderCount;
    /**
     * 设置：身份证号（备用）
     */
	//@Pattern(regexp="(^\\\\d{18}$)|(^\\\\d{15}$)",message="身份证号格式不正确",groups = {AddGroup.class, UpdateGroup.class})
	private String identifycation;
    /**
     * 设置：身份证照片地址
     */
	private String identifycationUrl;
    /**
     * 设置：健康证地址
     */
	private String healthUrl;
    /**
     * 设置：所属配送点id
     */
	private Long deliveryEndpointId;
	/**
	 * 设置：停用状态
	 */
	private Integer disable;
    /**
     * 设置：所属配送点名
     */
	@NotBlank(message="配送点名不能为空", groups = {AddGroup.class, UpdateGroup.class})
    private String deliveryEndpointName;

    /**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：配送员姓名
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：配送员姓名
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：配送员手机号
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * 获取：配送员手机号
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * 设置：配送员登录密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：配送员登录密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：配送员生日
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：配送员生日
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * 设置：用于点对点登录时的推送，由APP在登录的时候一起上传
	 */
	public void setClientId(String clientId) { this.clientId = clientId; }
	/**
	 * 获取：用于点对点登录时的推送，由APP在登录的时候一起上传
	 */
	public String getClientId() { return clientId; }

    public Integer getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Integer orderCount) {
        this.orderCount = orderCount;
    }

    /**
	 * 设置：当前状态，10：可配送，20：不可配送
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：当前状态，10：可配送，20：不可配送
	 */
	public Integer getStatus() {
		return status;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	/**
	 * 设置：身份证号（备用）
	 */
	public void setIdentifycation(String identifycation) {
		this.identifycation = identifycation;
	}
	/**
	 * 获取：身份证号（备用）
	 */
	public String getIdentifycation() {
		return identifycation;
	}
	/**
	 * 设置：身份证照片地址
	 */
	public void setIdentifycationUrl(String identifycationUrl) {
		this.identifycationUrl = identifycationUrl;
	}
	/**
	 * 获取：身份证照片地址
	 */
	public String getIdentifycationUrl() {
		return identifycationUrl;
	}
	/**
	 * 设置：健康证地址
	 */
	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}
	/**
	 * 获取：健康证地址
	 */
	public String getHealthUrl() {
		return healthUrl;
	}
	/**
	 * 设置：所属配送点id
	 */
	public void setDeliveryEndpointId(Long deliveryEndpointId) {
		this.deliveryEndpointId = deliveryEndpointId;
	}
	/**
	 * 获取：所属配送点id
	 */
	public Long getDeliveryEndpointId() {
		return deliveryEndpointId;
	}
	/**
	 * 设置：停用状态
	 */
	public void setDisable(Integer disable) { this.disable = disable; }

	/**
	 * 获取：停用状态
	 */
	public Integer getDisable() {
		return disable;
	}

    /**
     * 设置：配送点名
     */
    public void setDeliveryEndpointName(String deliveryEndpointName) {
        this.deliveryEndpointName = deliveryEndpointName;
    }

    /**
     * 获取：配送点名
     */
    public String getDeliveryEndpointName() { return deliveryEndpointName; }

	public Integer getPlatform() {
		return platform;
	}

	public void setPlatform(Integer platform) {
		this.platform = platform;
	}
}
