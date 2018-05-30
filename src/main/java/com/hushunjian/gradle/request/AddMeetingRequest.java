package com.hushunjian.gradle.request;

import java.util.List;

import com.hushunjian.gradle.dto.MeetingPersonRelationDto;
import com.hushunjian.gradle.dto.MeetingProjectRelationDto;
import com.hushunjian.gradle.dto.TimerTaskDto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddMeetingRequest {
	
	@ApiModelProperty(value="会议创建者id",required=true)
	private String meetCreatePersonId;
	
	@ApiModelProperty(value="会议创建者名称",required=true)
	private String meetCreatePersonName;
	
	@ApiModelProperty(value="会议主题",required=true)
    private String meetMain;
	
	@ApiModelProperty(value="会议时间",required=true)
    private String meetTime;
    
	@ApiModelProperty(value="会议地点",required=true)
    private String meetArea;
	
	@ApiModelProperty(value="会议议程",required=true)
    private String meetAgenda;
    
	@ApiModelProperty(value="会议概况",required=true)
    private String meetGeneral;
    
	@ApiModelProperty(value="会议纪要",required=true)
    private String meetSummary;
    
	@ApiModelProperty(value="会议图片通过附件上传 不能实现放大",required=true)
    private String meetImg;
    
	@ApiModelProperty(value="通过微信APi处理的，能实现放大",required=true)
    private String meetImg1;
    
	@ApiModelProperty(value="会议附件地址",required=true)
    private String meetAdjunctAddress;
    
	@ApiModelProperty(value="会议状态(0:未召开 1:已结束)",required=true)
    private String meetStatus;
	
	@ApiModelProperty(value="设置会议提醒",required=false)
	private TimerTaskDto timerTaskDto;
	
	@ApiModelProperty(value="参与会议的人员信息",required=true)
	private List<MeetingPersonRelationDto> meetingPersonRelationDtos;
	
	@ApiModelProperty(value="会议关联项目",required=true)
	private List<MeetingProjectRelationDto> meetingProjectRelationDtos;
}
