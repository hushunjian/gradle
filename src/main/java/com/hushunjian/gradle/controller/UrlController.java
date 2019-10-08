package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.service.JacksonService;
import com.hushunjian.gradle.service.UrlService;

import io.swagger.annotations.Api;

@RestController("UrlController")
@Api(value = "UrlController", description = "远程http")
@RequestMapping(value = "/url")
public class UrlController extends BaseController {
	
	@Autowired
	private UrlService urlService;
	@Autowired
	private JacksonService jacksonService;
	
	@GetMapping(value = "test")
	public Object testHttpUrl(){
		urlService.test();
		System.out.println("=======================");
		jacksonService.test();
		return success();
	}
}
