package com.hushunjian.gradle.controller;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;
import com.hushunjian.gradle.entity.TaskV2Entity;
import com.hushunjian.gradle.request.UpdateImportantTaskRequest;
import com.hushunjian.gradle.service.ImportantTaskService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("ImportantTaskController")
@RequestMapping(value = "/importantTask")
@Api(value = "ImportantTaskController", description = "重点任务相关接口",produces = MediaType.ALL_VALUE)
public class ImportantTaskController extends BaseController {

	@Autowired
	private ImportantTaskService importantTaskService;
	
	@ApiOperation(value = "添加任务接口")
	@GetMapping(value = "addTaskV2")
	public Object addTaskV2(@RequestParam String taskName){
		TaskV2Entity taskV2 = importantTaskService.addTaskV2(taskName);
		return success(taskV2);
	}
	
	@ApiOperation(value = "添加重点任务组接口")
	@GetMapping(value = "addImportantTaskGroup")
	public Object addImportantTaskGroup(@RequestParam String importantTaskGroupName){
		ImportantTaskV2Entity importantTaskV2 = importantTaskService.addImportantTaskGroup(importantTaskGroupName);
		return success(importantTaskV2);
	}
	
	@ApiOperation(value = "获取所有的任务")
	@GetMapping(value = "getAllTaskV2")
	public Object getAllTaskV2(){
		List<TaskV2Entity> taskV2s = importantTaskService.getAllTaskV2();
		List<Long> ids = taskV2s.stream().map(TaskV2Entity::getId).collect(Collectors.toList());
		return success(ids);
	}
	
	@ApiOperation(value = "获取所有的重点任务组")
	@GetMapping(value = "getAllImportantTaskV2Group")
	public Object getAllImportantTaskV2Group(){
		List<ImportantTaskV2Entity> importantTaskV2s = importantTaskService.getAllImportantTaskV2Group();
		return success(importantTaskV2s);
	}

	@ApiOperation(value = "任务加入重点任务组")
	@GetMapping(value = "joinTaskImportantTaskGroup")
	public Object joinTaskImportantTaskGroup(@RequestParam Long groupId,
											 @RequestParam Long taskId){
		TaskV2Entity taskV2 = importantTaskService.findTaskV2ById(taskId);
		if (taskV2 == null) {
			return failure("任务数据不存在!");
		}
		ImportantTaskV2Entity importantGroupTaskV2 = importantTaskService.findImportantGroupTaskV2ById(groupId);
		if (importantGroupTaskV2 == null) {
			return failure("重点任务组数据不存在!");
		}
		importantTaskService.joinTaskImportantTaskGroup(taskV2,importantGroupTaskV2);
		return success(importantGroupTaskV2);
	}
	
	@ApiOperation(value = "编辑重点任务")
	@PostMapping(value = "updateImportantTask")
	public Object updateImportantTask(@Validated @RequestBody UpdateImportantTaskRequest updateImportantTaskRequest){
		ImportantTaskV2Entity importantGroupTask = importantTaskService.findImportantGroupTaskV2ById(updateImportantTaskRequest.getId());
		if (importantGroupTask == null) {
			return failure("重点任务不存在,请确认参数");
		}
		importantGroupTask.setImportantTaskName(updateImportantTaskRequest.getImportantTaskName());
		return success("编辑重点任务成功");
	}
}
