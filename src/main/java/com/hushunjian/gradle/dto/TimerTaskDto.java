package com.hushunjian.gradle.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TimerTaskDto {
	
	@ApiModelProperty(value="会议首次提醒提前分钟数",required=false)
	private String firstRemindTime;

	@ApiModelProperty(value="会议提醒间隔时间分钟数",required=false)
	private String remindIntervalTime;

	@ApiModelProperty(value="会议提醒次数",required=false)
	private String repeatCount;

}
