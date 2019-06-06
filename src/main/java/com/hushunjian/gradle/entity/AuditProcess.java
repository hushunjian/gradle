package com.hushunjian.gradle.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.hushunjian.gradle.enumeration.AuditProcessStatusEnum;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;

import lombok.Data;

/**
 * 审核流程实体
 * 
 * @author hushunjian
 */
@Data
@Entity
@Table(name = "PF_AUDIT_PROCESS_T")
@DynamicInsert
@DynamicUpdate
public class AuditProcess {
	
	@Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "id")
    private String id; 
	
	@Version
	@Column(name="version", columnDefinition="bigint(20) NOT NULL DEFAULT 0 COMMENT '版本'")
	private Long version;
	
    @Column(name = "name", columnDefinition = "varchar(255) not null default '' comment '审核流程名称'")
    private String name;
    
    @Column(name = "project_id", columnDefinition = "varchar(255) not null default '' comment '单位工程id'")
    private String projectId;
    
    @Column(name = "user_id", columnDefinition = "varchar(255) not null default '' comment '用户id'")
    private String userId;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "audit_process_status", columnDefinition = "varchar(255) not null default 'UNFINISHED' comment '审核流程状态(FINISHED:已完成;UNFINISHED:未完成)'")
    private AuditProcessStatusEnum auditProcessStatus;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "audit_process_type", columnDefinition = "varchar(255) not null default 'ADD' comment '审核流程类型(ADD:新建计划流程;UPDATE:调整计划流程;DELETE:删除计划流程)'")
    private AuditProcessTypeEnum auditProcessType;
    
    @Column(name = "order_num", columnDefinition = "int not null default 0 comment '审核流程编号'")
    private Integer orderNum;
}

