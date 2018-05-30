package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="MEETING_MANAGE")
@ApiModel(value="会议表")
@DynamicInsert
public class MeetingManage implements Serializable {
	private static final long serialVersionUID = 1L;
	
	
	@ApiModelProperty(value="会议主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "meet_id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long meetId;
	
	@ApiModelProperty(value="会议创建者id",required=true)
	@Column(name="meet_create_person_id",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议创建者id'")
	private String meetCreatePersonId;
	
	@ApiModelProperty(value="会议创建者名称",required=true)
	@Column(name="meet_create_person_name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议创建者id'")
	private String meetCreatePersonName;
	
	@ApiModelProperty(value="会议主题",required=true)
	@Column(name="meet_main",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议主题'")
    private String meetMain;
	
	@ApiModelProperty(value="会议时间",required=true)
	@Column(name="meet_time",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议时间'")
    private String meetTime;
    
	@ApiModelProperty(value="会议地点",required=true)
	@Column(name="meet_area",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议地点'")
    private String meetArea;
    
	@ApiModelProperty(value="会议议程",required=true)
	@Column(name="meet_agenda",columnDefinition="longtext NOT NULL COMMENT '会议议程'")
    private String meetAgenda;
    
	@ApiModelProperty(value="会议概况",required=true)
	@Column(name="meet_general",columnDefinition="longtext NOT NULL  COMMENT '会议概况'")
    private String meetGeneral;
    
	@ApiModelProperty(value="会议纪要",required=true)
	@Column(name="meet_summary",columnDefinition="longtext NOT NULL  COMMENT '会议纪要'")
    private String meetSummary;
    
	@ApiModelProperty(value="会议图片通过附件上传 不能实现放大",required=true)
	@Column(name="meet_img",columnDefinition="longtext NOT NULL COMMENT '会议图片通过附件上传 不能实现放大'")
    private String meetImg;
    
	@ApiModelProperty(value="通过微信APi处理的，能实现放大",required=true)
	@Column(name="meet_img1",columnDefinition="longtext NOT NULL  COMMENT '通过微信APi处理的，能实现放大'")
    private String meetImg1;
    
	@ApiModelProperty(value="会议附件地址",required=true)
	@Column(name="meet_adjunct_address",columnDefinition="longtext NOT NULL  COMMENT '会议附件地址'")
    private String meetAdjunctAddress;
    
	@ApiModelProperty(value="会议状态(0:未召开 1:已结束)",required=true)
	@Column(name="meet_status",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '会议状态(0:未召开 1:已结束)'")
    private String meetStatus;
}
