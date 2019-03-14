package com.hushunjian.gradle.request;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddRelationRequest {
	
	@ApiModelProperty(value = "第一层级")
	@NotBlank
	private String firstLevel;
	
	@ApiModelProperty(value = "parent")
	private String parent;

	@ApiModelProperty(value = "线路id")
	@NotBlank
	private String parentProjectId;
	
	@ApiModelProperty(value = "模板")
	@NotBlank
	private String template;
	
	@ApiModelProperty(value = "类型")
	@NotBlank
	private String type;
	
	@ApiModelProperty(value = "所选中的文档id")
	@NotBlank
	private String chosenDocId;
	
	@ApiModelProperty(value = "所选中的文档下级id")
	@NotEmpty
	private List<String> subordinateDocIds;
}
