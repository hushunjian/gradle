package com.hushunjian.gradle.request;

import java.time.ZonedDateTime;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddProductionPlanRequest {
	@ApiModelProperty(value="计划开始时间",required=true)
	private ZonedDateTime planStartTime;

	@ApiModelProperty(value="计划结束时间",required=true)
	private ZonedDateTime planEndTime;
	
	@ApiModelProperty(value="计划所属月份",required=true)
	private String planBelongMonth;
	
	@ApiModelProperty(value="生产计划编号",required=true)
	private String planNumber;
}
