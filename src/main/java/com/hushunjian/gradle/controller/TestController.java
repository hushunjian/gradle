package com.hushunjian.gradle.controller;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.enumeration.YesOrNoEnum;
import com.hushunjian.gradle.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("TestController")
@Api(value = "TestController", description = "测试接口")
@RequestMapping(value = "/test")
@SuppressWarnings("all")
@Validated
public class TestController{
	@Autowired
	private TestService testService;
	
	@ApiOperation(value = "测试return", notes = "测试return",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/tesetReturn",method=RequestMethod.GET)
	@ResponseBody
    public void tesetReturn(@RequestParam(value="num",required=true) Integer num){
		testService.tesetReturn(num);
    }
	
	@ApiOperation(value = "测试String-Integer",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/test")
	@ResponseBody
	public void test(){
		testService.test();
	}
	
	@ApiOperation(value = "测试save",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/testSave")
	@ResponseBody
	public void testSave(){
		testService.testSave();
	}
	
	@ResponseBody
    @RequestMapping(value = "/validString1", method = RequestMethod.GET)
    public String validString1(@RequestParam(value = "str", defaultValue = "") @Size(min = 1, max = 3) String vStr){
        return vStr;
    }
	
	@ResponseBody
    @RequestMapping(value = "/validString2", method = RequestMethod.GET)
    public String validString2(@RequestParam(value = "str") @Size(min = 1) String vStr){
        return vStr;
    }
	
	@PostMapping(value = "/stringToYesOrNoEnum")
	public YesOrNoEnum stringToYesOrNoEnum(@RequestBody YesOrNoEnum yesOrNoEnum){
		return yesOrNoEnum;
	}
	
}
