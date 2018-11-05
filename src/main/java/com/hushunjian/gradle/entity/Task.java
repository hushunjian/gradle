package com.hushunjian.gradle.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;

@Data
@Table(name = "task")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class Task {
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id; 
	
	@Version
	@Column(name="version",columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;

	@Column(name="task_name",columnDefinition="varchar(255) NOT NULL DEFAULT '' COMMENT '任务名称'")
	private String taskName;
	
	@Column(name = "plan_start_date", columnDefinition = "datetime(6) DEFAULT NULL COMMENT '计划开始时间'")
	private ZonedDateTime planStartDate;

	@Column(name = "plan_end_date", columnDefinition = "datetime(6) DEFAULT NULL COMMENT '计划结束时间'")
	private ZonedDateTime planEndDate;
}
