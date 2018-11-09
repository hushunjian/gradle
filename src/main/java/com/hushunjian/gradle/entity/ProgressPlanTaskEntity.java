package com.hushunjian.gradle.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 周期计划任务表
 * 
 * @author hushunjian
 *
 */
@Setter
@Getter
@Entity
@Table(name = "my_progress_plan_task")
public class ProgressPlanTaskEntity implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="task_id")
	private TaskEntity task;
	
	@ManyToOne
	@JoinColumn(name="progress_plan_id")
	private ProgressPlanEntity progressPlan;
	
	@Column(name = "name")
	private String name;
}
