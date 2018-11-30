package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * 任务表
 * 
 * @author hushunjian
 *
 */
@Setter
@Getter
@Table
@Entity(name = "my_task")
public class TaskEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "task",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<ProgressPlanTaskEntity> progressPlanTasks = new ArrayList<>();
	
	@OneToMany(mappedBy = "planTask",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
	private List<ImportantTaskEntity> importantTasks = new ArrayList<>();
}
