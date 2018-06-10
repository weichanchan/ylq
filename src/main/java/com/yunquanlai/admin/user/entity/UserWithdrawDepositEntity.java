package com.yunquanlai.admin.user.entity;

import java.io.Serializable;
import java.util.Date;



/**
 * 客户押金提现申请表
 * 
 * @author liyanjun
 * @email 
 * @date 2018-06-04 15:44:08
 */
public class UserWithdrawDepositEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
    /**
     * 设置：主键 ID
     */
	private Long id;
    /**
     * 设置：对应用户 ID
     */
	private Long userInfoId;
    /**
     * 设置：是否处理，10：未处理，20：已处理
     */
	private Integer isHandle;
    /**
     * 设置：创建时间
     */
	private Date creationTime;
    /**
     * 设置：处理时间
     */
	private Date handleTime;

	/**
	 * 设置：主键 ID
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：主键 ID
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：对应用户 ID
	 */
	public void setUserInfoId(Long userInfoId) {
		this.userInfoId = userInfoId;
	}
	/**
	 * 获取：对应用户 ID
	 */
	public Long getUserInfoId() {
		return userInfoId;
	}
	/**
	 * 设置：是否处理，0：未处理，1：已处理
	 */
	public void setIsHandle(Integer isHandle) {
		this.isHandle = isHandle;
	}
	/**
	 * 获取：是否处理，0：未处理，1：已处理
	 */
	public Integer getIsHandle() {
		return isHandle;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreationTime() {
		return creationTime;
	}
	/**
	 * 设置：处理时间
	 */
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	/**
	 * 获取：处理时间
	 */
	public Date getHandleTime() {
		return handleTime;
	}
}
