package com.hushunjian.gradle.entity;

import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;

@Data
@Table(name = "pf_task_v2_table")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class TaskV2Entity {
	
	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 任务名称
	 */
	@Column(name = "task_name", nullable = false)
	private String taskName;
	
	/**
	 * 开始时间
	 */
    @Column(name = "start_date", columnDefinition = "datetime comment '开始时间'")
    private ZonedDateTime startDate;
	
	/**
	 * 对应的重点任务数据
	 */
	@OneToMany(mappedBy = "taskV2")
	private List<ImportantTaskV2Entity> importantTaskV2s = new ArrayList<>();
}
