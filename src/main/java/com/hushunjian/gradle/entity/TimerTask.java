package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@Entity
@Table(name="TIMER_TASK")
@ApiModel(value="会议设置定时提醒表")
public class TimerTask {
	
	@ApiModelProperty(value="主键id(新增时非必填,修改时必填)",required=false)
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;
	
	@ApiModelProperty(name="会议id",required=true)
	@Column(name="meet_id",columnDefinition="bigint(20) NOT NULL DEFAULT -1 COMMENT '会议id'")
	private Long meetId;
	
	@ApiModelProperty(name="会议首次提醒提前分钟数",required=true)
	@Column(name="first_remind_time",columnDefinition="int(5) NOT NULL DEFAULT 0 COMMENT '会议首次提醒提前分钟数'")
	private String firstRemindTime;

	@ApiModelProperty(name="会议提醒间隔时间分钟数",required=true)
	@Column(name="remind_interval_time",columnDefinition="int(5) NOT NULL DEFAULT 0 COMMENT '会议提醒间隔时间分钟数'")
	private String remindIntervalTime;

	@ApiModelProperty(name="会议提醒次数",required=true)
	@Column(name="repeat_count",columnDefinition="int(5) NOT NULL DEFAULT 0 COMMENT '会议提醒次数'")
	private String repeatCount;

}
