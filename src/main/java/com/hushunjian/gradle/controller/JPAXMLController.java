package com.hushunjian.gradle.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;

@RestController("JPAXMLController")
@Api(value = "JPAXMLController", description = "JPAXML测试接口")
@RequestMapping(value = "/JPAXML")
public class JPAXMLController{
}
