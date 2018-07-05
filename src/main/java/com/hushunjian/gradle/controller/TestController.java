package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("TestController")
@Api(value = "TestController", description = "测试接口")
@RequestMapping(value = "/test")
@SuppressWarnings("all")
public class TestController{
	@Autowired
	private TestService testService;
	
	@ApiOperation(value = "测试return", notes = "测试return",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/tesetReturn",method=RequestMethod.GET)
	@ResponseBody
    public void tesetReturn(@RequestParam(value="num",required=true) Integer num){
		testService.tesetReturn(num);
    }
}
