package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.service.PlanTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("PlanTaskController")
@Api(value = "PlanTaskController", description = "任务测试接口")
@RequestMapping(value = "/PlanTaskController")
public class PlanTaskController {

	@Autowired
	private PlanTaskService planTaskService;
	
	@ApiOperation(value = "测试保存任务",produces = MediaType.ALL_VALUE)
	@GetMapping(value = "taskSave")
	@ResponseBody
	public void taskSave(String name){
		planTaskService.taskSave(name);
	}
	
	@ApiOperation(value = "测试保存周期计划",produces = MediaType.ALL_VALUE)
	@GetMapping(value = "progressPlanSave")
	@ResponseBody
	public void progressPlanSave(String name){
		planTaskService.progressPlanSave(name);
	}
	
	@ApiOperation(value = "测试关联周期计划",produces = MediaType.ALL_VALUE)
	@GetMapping(value = "taskSaveProgressPlanTask")
	@ResponseBody
	public void taskSaveProgressPlanTask(Long taskId,Long progressPlanId, String name){
		planTaskService.taskSaveProgressPlanTask(taskId,progressPlanId,name);
	}
	
	@ApiOperation(value = "根据任务主键任务",produces = MediaType.ALL_VALUE)
	@GetMapping(value = "findById")
	@ResponseBody
	public String findById(Long id){
		planTaskService.findById(id);
		return null;
	}
}
