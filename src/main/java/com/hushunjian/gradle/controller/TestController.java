package com.hushunjian.gradle.controller;

import java.util.List;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
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

import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.enumeration.YesOrNoEnum;
import com.hushunjian.gradle.request.TestListEmptyRequest;
import com.hushunjian.gradle.request.TestListInRequest;
import com.hushunjian.gradle.service.TestService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("TestController")
@Api(value = "TestController", description = "测试接口")
@RequestMapping(value = "/test")
@SuppressWarnings("all")
@Validated 
public class TestController extends BaseController{
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
    public String validString1(@RequestParam(value = "str", defaultValue = "") @Size(min = 1, max = 3, message = "名称长度必须小于3") String vStr,
    		@RequestParam(value = "str1", defaultValue = "") @Size(min = 1, max = 4) String vStr1){
        return vStr + vStr1;
    }
	
	@ResponseBody
    @RequestMapping(value = "/validString2", method = RequestMethod.GET)
    public String validString2(@RequestParam(value = "str") @Size(min = 1) String vStr){
        return vStr;
    }
	
	@ResponseBody
	@PostMapping(value = "/stringToYesOrNoEnum")
	public YesOrNoEnum stringToYesOrNoEnum(@RequestBody YesOrNoEnum yesOrNoEnum){
		return yesOrNoEnum;
	}
	
	@ApiOperation(value = "testSave1",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/testSave1")
	public Long testSave1(@RequestParam Long num){
		return testService.testSave1(num);
	}
	
	@ApiOperation(value = "testSave2",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/testSave2")
	public Long testSave2(@RequestParam Long num){
		return testService.testSave2(num);
	}
	
	@ApiOperation(value = "testSave3",produces = MediaType.ALL_VALUE)
	@GetMapping(value="/testSave3")
	public Long testSave3(@RequestParam Long num){
		return testService.testSave3(num);
	}
	
	@ApiOperation(value = "testIdInByArray")
	@GetMapping(value = "testIdInByArray")
	public Integer testIdInByArray(){
		return testService.testIdInByArray();
	}
	
	@ApiOperation(value = "testIdInByList")
	@GetMapping(value = "testIdInByList")
	public Integer testIdInByList(){
		return testService.testIdInByList();
	}
	
	@ResponseBody
	@PostMapping(value = "/testSaveAndEdit")
	public void testSaveAndEdit(@Validated @RequestBody StringToIntegerDTO stringToIntegerDTO){
		testService.testSaveAndEdit(stringToIntegerDTO);
	}
	
	@ApiOperation(value = "findDTO")
	@GetMapping(value = "findDTO")
	public List<StringToIntegerDTO> findDTO(){
		return testService.findDTO();
	}
	
	@ResponseBody
	@PostMapping(value = "/testDelete")
	public void testDelete(@RequestBody List<Long> ids){
		testService.testDelete(ids);
	}
	
	@ResponseBody
	@PostMapping(value = "/testListIn")
	public Object testListIn(@Validated @RequestBody List<TestListInRequest> request){
		List<StringToIntegerDTO> stringToIntegerDTOs = testService.testListIn(request);
		return success(stringToIntegerDTOs);
	}
	@ResponseBody
	@PostMapping(value = "/testEmptyList")
	public Object testEmptyList(@Validated @RequestBody TestListEmptyRequest request){
		return success();
	}
	
	@ResponseBody
	@PostMapping(value = "/testContain")
	public Object testContain(@RequestParam String number){
		List<StringToIntegerDTO> stringToIntegerDTOs = testService.testContain(number);
		return success(stringToIntegerDTOs);
	}
	
	@ResponseBody
	@PostMapping(value = "/testNotContain")
	public Object testNotContain(@RequestParam String number){
		List<StringToIntegerDTO> stringToIntegerDTOs = testService.testNotContain(number);
		return success(stringToIntegerDTOs);
	}
	
	@ResponseBody
	@PostMapping(value = "/findAll")
	public Object findAll(){
		List<StringToIntegerDTO> stringToIntegerDTOs = testService.findAll();
		return success(stringToIntegerDTOs);
	}
	
	@ResponseBody
	@ApiOperation(value = "测试get请求参数不可为空")
	@GetMapping(value = "testGetNotBlank")
	public Object testGetNotBlank(@NotBlank @RequestParam String str){
		return success();
	}
	
	@ResponseBody
	@ApiOperation(value = "测试post请求,不使用@Validated")
	@PostMapping(value = "/testPostNotValid")
	public Object testPostNotValid(@RequestBody TestListEmptyRequest testListEmptyRequest){
		return success();
	}
	
	
	@ResponseBody
	@ApiOperation(value = "测试JPA的count")
	@GetMapping(value="/testCount")
	public Object testCount(@RequestParam String number){
		Long count = testService.testCount(number);
		return success(count>0);
	}
}
