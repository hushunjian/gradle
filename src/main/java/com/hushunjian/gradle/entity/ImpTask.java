package com.hushunjian.gradle.entity;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;

@Data
@Table(name = "imp_task_table")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class ImpTask {
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	/**
	 * 任务名称
	 */
	@Column(name = "name", nullable = false)
	private String name;
	
	/**
	 * 开始时间
	 */
    @Column(name = "start_date", columnDefinition = "datetime comment '开始时间'")
    private ZonedDateTime startDate;
    
	/**
	 * 开始时间
	 */
    @Column(name = "end_date", columnDefinition = "datetime comment '完成时间'")
    private ZonedDateTime endDate;
    
    /**
     * 父id
     */
    @Column(name="parent_id", columnDefinition="bigint(20) COMMENT '父id'")
    private Long parentId;
    
    /**
     * 任务id
     */
    @Column(name="task_id", columnDefinition="bigint(20) COMMENT '任务id'")
    private Long taskId;
    
    /**
     * 备注
     */
    @Column(name = "remark", columnDefinition = "varchar(255) not null default '' comment '备注'")
    private String remark;
}
