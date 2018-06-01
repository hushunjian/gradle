package com.hushunjian.gradle.request;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryAllMeetingRequest {
	@ApiModelProperty(value="会议主键",required=false)
	private long meetId;
	@ApiModelProperty(value="会议创建者id",required=false)    
	private String meetCreatePersonId;
	@ApiModelProperty(value="会议主题",required=false)
	private String meetMain;
	@ApiModelProperty(value="会议时间",required=false)
	private String meetTime;
	@ApiModelProperty(value="会议地点",required=false)
	private String meetArea;
	@ApiModelProperty(value="会议状态",required=false)
	private String meetStatus;
	@ApiModelProperty(value="登录者Id",required=false)
	private String longInPersonId;
	
	@ApiModelProperty(value="登录者参与的所有会议Id",required=false)
	private List<Long> meetIds;//登录者参与的所有会议Id
	
	@ApiModelProperty(value="会议关联项目id",required=false)
	private List<String> relationProjectId;

	
	@ApiModelProperty(value="页码",required=false)
	private Integer pageNo;
	@ApiModelProperty(value="页大小",required=false)
	private Integer pageSize;
}
