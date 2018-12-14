package com.hushunjian.gradle.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateImportantTaskRequest {
	
	@ApiModelProperty(value = "主键id")
	@NotNull
	private Long id;
	
	@ApiModelProperty(value = "重点任务名")
	@Size(max = 200,message = "重点任务名长度必须在0-200")
	private String importantTaskName;
}
