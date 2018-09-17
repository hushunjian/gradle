package com.hushunjian.gradle.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserAndOperatorRequest {
	
	@ApiModelProperty(value = "用户姓名")
	private String userName;
	
	@ApiModelProperty(value = "操作者姓名")
	private String operatorName;
}
