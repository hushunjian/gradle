package com.hushunjian.gradle.dto;

import com.hushunjian.gradle.enumeration.DocumentTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DocumentDTO {
	
	@ApiModelProperty(value = "主键id")
	private String id;
	
	@ApiModelProperty(value = "version")
	private Long version;
	
	@ApiModelProperty(value = "name")
	private String name;
	
	@ApiModelProperty(value = "类型")
	private DocumentTypeEnum documentType;
	
	private Boolean test;
}
