package com.hushunjian.gradle.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryUserRequest {
	@ApiModelProperty(value="页码",required=false)
	private Integer pageNo;
	@ApiModelProperty(value="页大小",required=false)
	private Integer pageSize;
	@ApiModelProperty(value="用户姓名",required=false)
	private String userName;
}
