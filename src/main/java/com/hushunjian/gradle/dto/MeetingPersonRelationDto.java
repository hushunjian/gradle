package com.hushunjian.gradle.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class MeetingPersonRelationDto implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value="参与会议人员id",required=true)
    private String meetPersonId;
	
	@ApiModelProperty(value="参与会议人员名称",required=true)
    private String meetPersonName;
	
	@ApiModelProperty(value="参与会议人员角色",required=true)
	private String meetPersonRole;
	
	@ApiModelProperty(value="参与会议人员所属单位",required=true)
	private String meetPersonDepart;

	@ApiModelProperty(value="参与会议人员手机号",required=true)
    private String meetPhoneNumber;
	
	@ApiModelProperty(value="参与会议人员openId",required=true)
	private String meetPersonOpenId;
}
