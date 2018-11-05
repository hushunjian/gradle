package com.hushunjian.gradle.request;

import javax.validation.constraints.Min;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryUserRequest {
	@ApiModelProperty(value="页码",required=false)
	@Min(value = 1)
	private Integer pageNo;
	@ApiModelProperty(value="页大小",required=false)
	@Min(value = 1)
	private Integer pageSize;
	@ApiModelProperty(value="用户姓名",required=false)
	private String userName;
}
