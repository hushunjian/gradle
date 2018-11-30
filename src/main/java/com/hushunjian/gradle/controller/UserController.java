package com.hushunjian.gradle.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.entity.User;
import com.hushunjian.gradle.request.User1Request;
import com.hushunjian.gradle.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("UserController")
@Api(value = "UserController", description = "用户接口")
@RequestMapping(value = "/user")
@SuppressWarnings("all")
public class UserController{
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "根据名字模糊查询用户信息", notes = "根据名字模糊查询用户信息",produces = MediaType.ALL_VALUE)
	@PostMapping(value="/getAllUserTest")
	public List<User> getAllUserTest(@Validated @RequestBody User1Request user1Request){
		return userService.getAllUserTest(user1Request);
	}
	
	@ApiOperation(value = "测试BigDecimal数字")
	@GetMapping(value="/testNumber")
	public BigDecimal testNumber(@RequestParam(value="userName",required=true) String userName){
		return userService.testNumber(userName);
	}
	
	@ApiOperation(value = "根据名字模糊查询用户信息", notes = "根据名字模糊查询用户信息",produces = MediaType.ALL_VALUE)
	@PostMapping(value="/test")
	public List<User> test(@Validated @RequestBody User1Request user1Request){
		return userService.getAllUserTest(user1Request);
	}
	
	@ApiOperation(value = "根据名字模糊查询用户信息(startingWith)", notes = "根据名字模糊查询用户信息",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/startingWith")
	public List<User> StartingWith(){
		return userService.startingWith();
	}
	
	@ApiOperation(value = "根据名字模糊查询用户信息(startsWith)", notes = "根据名字模糊查询用户信息",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/startsWith")
	public List<User> StartsWith(){
		return userService.startsWith();
	}
}
