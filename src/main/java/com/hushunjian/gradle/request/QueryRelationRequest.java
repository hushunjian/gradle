package com.hushunjian.gradle.request;

import org.hibernate.validator.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryRelationRequest {
	@ApiModelProperty(value = "父级")
	private String parent;
	
	@ApiModelProperty(value = "所选中的文档id")
	@NotBlank
	private String chosenDocId;
	
	@ApiModelProperty(value = "线路id")
	@NotBlank
	private String parentProjectId;
	
	@ApiModelProperty(value = "类型")
	@NotBlank
	private String type;
	
	@ApiModelProperty(value = "模板")
	@NotBlank
	private String template;
	
	@ApiModelProperty(value = "第一层级")
	@NotBlank
	private String firstLevel;
}
