package com.hushunjian.gradle.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import lombok.Data;

@Data
@Table(name = "pf_important_task_v2_table")
@Entity
@DynamicInsert
@DynamicUpdate
@SelectBeforeUpdate
public class ImportantTaskV2Entity {

	/**
	 * 主键id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/**
	 * 重点任务组名/组中成员名称
	 */
	@Column(name = "important_task_name", nullable = false)
	private String importantTaskName;
	
	/**
	 * 重点任务组中成员关联的任务
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "task_id")
	private TaskV2Entity taskV2;
	
	/**
	 * 组成员所属的重点任务组id
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "group_id")
	private ImportantTaskV2Entity group;
	
	/**
	 * 重点任务组中的成员
	 */
	@OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
	private List<ImportantTaskV2Entity> members = new ArrayList<>();
}
