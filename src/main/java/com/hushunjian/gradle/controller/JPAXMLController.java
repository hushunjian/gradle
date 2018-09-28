package com.hushunjian.gradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.entity.JPAEntityA;
import com.hushunjian.gradle.service.JPAXMLService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("JPAXMLController")
@Api(value = "JPAXMLController", description = "JPAXML测试接口")
@RequestMapping(value = "/JPAXML")
public class JPAXMLController{
	
	@Autowired
	private JPAXMLService jPAXMLService;
	
	@GetMapping(value="/test1")
	@ApiOperation(value="JPAXML-test1")
	public List<JPAEntityA> test1(@RequestParam("userName")String userName){
		return jPAXMLService.test(userName);
	}
	
	@GetMapping(value="/test2")
	@ApiOperation(value="JPAXML-test2")
	public List<String> test2(@RequestParam("userName")String userName){
		return jPAXMLService.test2(userName);
	}
	
}
