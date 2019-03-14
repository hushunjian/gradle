package com.hushunjian.gradle.request;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import com.hushunjian.gradle.enumeration.DocumentTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddDocumentRequest {
	
	@ApiModelProperty(value = "名称", required = true)
	@NotBlank
	private String name;
	
	@ApiModelProperty(value = "文档类型[1:工程阶段;2:参建方;3:单位工程;4:专业;]", required = true)
	@NotNull
	private DocumentTypeEnum documentType;
}
