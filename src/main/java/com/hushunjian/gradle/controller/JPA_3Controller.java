package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.entity.JPAEntityC;
import com.hushunjian.gradle.service.JPA_3Service;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("JPA_3Controller")
@Api(value = "JPA_3Controller", description = "JPAXML测试接口")
@RequestMapping(value = "/JPA_3")
public class JPA_3Controller {

	@Autowired
	private JPA_3Service jpa_3Service;
	
	@GetMapping(value="/addJpa_3")
	@ApiOperation(value="addJpa_3")
	public JPAEntityC addJpa_3(@RequestParam("name")String name){
		return jpa_3Service.addJpa_3(name);
	}
	
	@GetMapping(value="/updateJpa_3")
	@ApiOperation(value="updateJpa_3")
	public JPAEntityC updateJpa_3(@RequestParam("name")String name,
			                      @RequestParam("id")Long id){
		return jpa_3Service.updateJpa_3(id,name);
	}
	
	@GetMapping(value="/removeJpa_3")
	@ApiOperation(value="removeJpa_3")
	public void removeJpa_3(@RequestParam("id")Long id){
		jpa_3Service.removeJpa_3(id);
	}
}
