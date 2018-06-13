package com.hushunjian.gradle.entity;
import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@Table(name="PF_MEETING_MANAGE_T")
@ApiModel(value="会议表")
@DynamicInsert
@DynamicUpdate
@EqualsAndHashCode(callSuper=false)
public class MeetingManageEntity {
	
	@ApiModelProperty(value="会议主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "meet_id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long meetId;
	
	@Column(name="meet_create_person_id",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议创建者id'")
	private String meetCreatePersonId;
	
	@Column(name="meet_create_person_open_id",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议创建者openId'")
	private String meetCreatePersonOpenId;
	
	@Column(name="meet_create_person_name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议创建者id'")
	private String meetCreatePersonName;
	
	@Column(name="meet_main",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议主题'")
    private String meetMain;
	
	@Column(name="meet_time",columnDefinition="timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '会议时间'")
    private ZonedDateTime meetTime;
    
	@Column(name="meet_area",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '会议地点'")
    private String meetArea;
    
	@Column(name="meet_agenda",columnDefinition="longtext NOT NULL COMMENT '会议议程'")
    private String meetAgenda;
    
	@Column(name="meet_general",columnDefinition="longtext NOT NULL  COMMENT '会议概况'")
    private String meetGeneral;
    
	@Column(name="meet_summary",columnDefinition="longtext NOT NULL  COMMENT '会议纪要'")
    private String meetSummary;
    
	@Column(name="meet_img",columnDefinition="longtext NOT NULL COMMENT '会议图片通过附件上传 不能实现放大'")
    private String meetImg;
    
	@Column(name="meet_img1",columnDefinition="longtext NOT NULL  COMMENT '通过微信APi处理的，能实现放大'")
    private String meetImg1;
    
	@Column(name="meet_adjunct_address",columnDefinition="longtext NOT NULL  COMMENT '会议附件地址'")
    private String meetAdjunctAddress;
    
	@Column(name="meet_status",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '会议状态(0:未召开 1:已结束)'")
    private Integer meetStatus;
	
	@Column(name="timer_task_status",columnDefinition="int(2) NOT NULL DEFAULT 0 COMMENT '是否开启会议提示(0:否;1:是)'")
    private Integer timerTaskStatus;

}
