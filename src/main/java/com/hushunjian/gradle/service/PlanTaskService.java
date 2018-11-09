package com.hushunjian.gradle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.ProgressPlanEntity;
import com.hushunjian.gradle.entity.ProgressPlanTaskEntity;
import com.hushunjian.gradle.entity.TaskEntity;
import com.hushunjian.gradle.repo.ProgressPlanRepo;
import com.hushunjian.gradle.repo.ProgressPlanTaskRepo;
import com.hushunjian.gradle.repo.TaskRepo;

@Service
public class PlanTaskService {
	
	@Autowired
	private TaskRepo taskRepo;
	@Autowired
	private ProgressPlanRepo progressPlanRepo;
	@Autowired
	private ProgressPlanTaskRepo progressPlanTaskRepo;

	public void taskSave(String name) {
		TaskEntity task = new TaskEntity();
		task.setName(name);
		taskRepo.save(task);
	}

	public void taskSaveProgressPlanTask(Long id,Long progressPlanId, String name) {
		TaskEntity task = taskRepo.findOne(id);
		ProgressPlanEntity progressPlan = progressPlanRepo.findOne(progressPlanId);
		
		ProgressPlanTaskEntity progressPlanTask = new ProgressPlanTaskEntity();
		progressPlanTask.setName(name);
		progressPlanTask.setTask(task);
		progressPlanTask.setProgressPlan(progressPlan);
		
		task.getProgressPlanTasks().add(progressPlanTask);
		progressPlan.getProgressPlanTasks().add(progressPlanTask);
		
		progressPlanTaskRepo.save(progressPlanTask);
		taskRepo.save(task);
		progressPlanRepo.save(progressPlan);
	}

	public void progressPlanSave(String name) {
		ProgressPlanEntity progressPlan = new ProgressPlanEntity();
		progressPlan.setName(name);
		progressPlanRepo.save(progressPlan);
	}

	public TaskEntity findById(Long id) {
		TaskEntity task = taskRepo.findOne(id);
		return task;
	}

}
