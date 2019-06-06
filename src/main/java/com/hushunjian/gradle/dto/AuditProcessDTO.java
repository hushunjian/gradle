package com.hushunjian.gradle.dto;

import com.hushunjian.gradle.enumeration.AuditProcessStatusEnum;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuditProcessDTO {
	
	@ApiModelProperty(value = "id")
    private String id; 
	
	@ApiModelProperty(value = "version")
	private Long version;
	
	@ApiModelProperty(value = "审核流程名称")
    private String name;
    
	@ApiModelProperty(value = "单位工程id")
    private String projectId;
    
	@ApiModelProperty(value = "用户id")
    private String userId;
    
	@ApiModelProperty(value = "审核流程状态(FINISHED:已完成;UNFINISHED:未完成)")
    private AuditProcessStatusEnum auditProcessStatus;
    
	@ApiModelProperty(value = "审核流程类型(ADD:新建计划流程;UPDATE:调整计划流程;DELETE:删除计划流程)")
    private AuditProcessTypeEnum auditProcessType;
	
	@ApiModelProperty(value = "审核流程编号")
    private Integer orderNum;
}
