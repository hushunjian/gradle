package com.hushunjian.gradle.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MeetingProjectRelationDto implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value="关联项目id",required=true)
	private String meetProjectId;
	
	@ApiModelProperty(value="关联项目名称",required=true)
	private String meetProjectName;
}
