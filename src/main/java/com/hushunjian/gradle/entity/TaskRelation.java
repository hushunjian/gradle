package com.hushunjian.gradle.entity;

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
@Table(name = "task_relation")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class TaskRelation {
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id; 
	
	@Version
	@Column(name="version",columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;
	
	@Column(name = "task_id",columnDefinition="bigint(20) COMMENT '任务id'")
	private Long taskId;

	@Column(name = "pre_task_id",columnDefinition="bigint(20) COMMENT '前置任务id'")
	private Long preTaskId;
}
