package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.entity.TestDefault;
import com.hushunjian.gradle.service.TestDefaultService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("TestDefaultController")
@Api(value = "TestDefaultController", description = "会议相关接口",produces = MediaType.ALL_VALUE)
@RequestMapping(value = "/testDefault")
public class TestDefaultController {
	
	@Autowired
	private TestDefaultService testDefaultService;
	
	@ApiOperation(value = "添加测试", notes = "添加测试",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/addTestDefault",method=RequestMethod.POST)
	@ResponseBody
	public void addTestDefault(@RequestBody TestDefault testDefault){
		testDefaultService.addTestDefault(testDefault);
	}
	
	@ApiOperation(value = "删除测试", notes = "删除测试",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/deleteTestDefaultById",method=RequestMethod.POST)
	@ResponseBody
	public void deleteTestDefaultById(@RequestParam(value="id",required=true) Long id){
		testDefaultService.deleteTestDefaultById(id);
	}
}
