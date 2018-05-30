package com.hushunjian.gradle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserBasicInfoDto {
	@ApiModelProperty(value="用户姓名")
	private String userName;
	@ApiModelProperty(value="用户年龄")
	private int userAge;
	@ApiModelProperty(value="用户状态:0:禁用;1:正常")
	private int userStatus;
	@ApiModelProperty(value="创建人姓名")
	private String operatorName;
	@ApiModelProperty(value="创建人年龄")
	private int operatorAge;
	@ApiModelProperty(value="创建人状态:0:禁用;1:正常")
	private int operatorStatus;
	
	public UserBasicInfoDto(){
		
	}
	
	public UserBasicInfoDto(String userName, int userAge, int userStatus, String operatorName, int operatorAge,int operatorStatus) {
		this.userName = userName;
		this.userAge = userAge;
		this.userStatus = userStatus;
		this.operatorName = operatorName;
		this.operatorAge = operatorAge;
		this.operatorStatus = operatorStatus;
	}
	
	
}
