package com.hushunjian.gradle.service;

import java.time.ZonedDateTime;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;
import com.hushunjian.gradle.entity.TaskV2Entity;
import com.hushunjian.gradle.repo.ImportantTaskV2Repo;
import com.hushunjian.gradle.repo.TaskV2Repo;

@Service
@Transactional
public class ImportantTaskService {
	
	@Autowired
	private TaskV2Repo taskV2Repo;
	
	@Autowired
	private ImportantTaskV2Repo importantTaskV2Repo;

	/**
	 * 添加任务
	 * 
	 * @param taskName
	 * @return
	 */
	public TaskV2Entity addTaskV2(String taskName) {
		TaskV2Entity taskV2 = new TaskV2Entity();
		taskV2.setTaskName(taskName);
		taskV2Repo.save(taskV2);
		return taskV2;
	}

	/**
	 * 添加重点任务组
	 * 
	 * @param taskName
	 * @return
	 */
	public ImportantTaskV2Entity addImportantTaskGroup(String importantTaskGroupName) {
		ImportantTaskV2Entity importantTaskV2 = new ImportantTaskV2Entity();
		importantTaskV2.setImportantTaskName(importantTaskGroupName);
		importantTaskV2.setStartDate(ZonedDateTime.now());
		importantTaskV2Repo.save(importantTaskV2);
		return importantTaskV2;
	}
	
	/**
	 * 获取所有的任务列表
	 * 
	 * @return
	 */
	public List<TaskV2Entity> getAllTaskV2() {
		return taskV2Repo.findAll();
	}

	/**
	 * 获取所有的重点任务组数据
	 * 
	 * @return
	 */
	public List<ImportantTaskV2Entity> getAllImportantTaskV2Group() {
		List<ImportantTaskV2Entity> importantTaskV2s = importantTaskV2Repo.findByGroupIsNull();
		return importantTaskV2s;
	}

	/**
	 * 获取单个任务
	 * 
	 * @param taskId
	 * @return
	 */
	public TaskV2Entity findTaskV2ById(Long taskId) {
		return taskV2Repo.findOne(taskId);
	}

	/**
	 * 获取单个重点任务组数据
	 * 
	 * @param groupId
	 * @return
	 */
	public ImportantTaskV2Entity findImportantGroupTaskV2ById(Long groupId) {
		return importantTaskV2Repo.findOne(groupId);
	}

	/**
	 * 任务加入重点任务组
	 * 
	 * @param taskV2
	 * @param importantGroupTaskV2
	 */
	public void joinTaskImportantTaskGroup(TaskV2Entity taskV2, ImportantTaskV2Entity importantGroupTaskV2) {
		ImportantTaskV2Entity member = new ImportantTaskV2Entity();
		member.setGroup(importantGroupTaskV2);
		member.setImportantTaskName(taskV2.getTaskName());
		member.setTaskV2(taskV2);
		importantGroupTaskV2.getMembers().add(member);
		importantTaskV2Repo.save(importantGroupTaskV2);
	}

}
