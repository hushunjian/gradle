package com.hushunjian.gradle.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * 周期计划表
 * 
 * @author hushunjian
 *
 */
@Data
@Table
@Entity(name = "my_progress_plan")
public class ProgressPlanEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "progressPlan",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ProgressPlanTaskEntity> progressPlanTasks = new ArrayList<ProgressPlanTaskEntity>();
}
