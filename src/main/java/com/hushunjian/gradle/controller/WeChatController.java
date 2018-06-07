package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.service.WeChatService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("WeChatController")
@Api(value = "WeChatController", description = "微信公众号相关接口",produces = MediaType.ALL_VALUE)
@RequestMapping(value = "/weChat")
public class WeChatController extends BaseController {

	@Autowired
	private WeChatService weChatService;
	
	@PostMapping(value="/testWeChatProperties")
	@ApiOperation(value="测试微信相关的配置是否有效")
	public Object testWeChatProperties(){
		weChatService.testWeChatProperties();
		return success();
	}
	
	
}
