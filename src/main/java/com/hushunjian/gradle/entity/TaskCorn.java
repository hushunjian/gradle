package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.annotations.Table;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
@BatchSize(size = 50)
@Table(appliesTo = "task_corn",comment="定时任务执行配置")
public class TaskCorn implements Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id  
	@Column(name = "id",columnDefinition="bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键自增id'")
	@GeneratedValue
    private Long id;  
	
	@Column(name="task_name",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '任务名称'")
	private String taskName;
	
	@Column(name="task_corn",columnDefinition="varchar(50) NOT NULL DEFAULT '0' COMMENT '任务执行时间'")
	private String taskCorn;
}
