package com.hushunjian.gradle.request;

import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ExportOperatorRequest {
	
	@ApiModelProperty(value = "操作者姓名")
	@NotEmpty
	private String operatorName;

}
