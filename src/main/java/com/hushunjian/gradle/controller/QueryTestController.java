package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.service.QueryTestService;

import io.swagger.annotations.Api;

@RestController("QueryTestController")
@RequestMapping(value = "/query")
@Api(value = "QueryTestController", description = "JPA动态查询相关测试",produces = MediaType.ALL_VALUE)
public class QueryTestController extends BaseController  {

	@Autowired
	private QueryTestService queryTestService;
	
	public Object test(){
		return success();
	}
}
