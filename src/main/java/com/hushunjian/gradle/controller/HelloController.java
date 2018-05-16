package com.hushunjian.gradle.controller;

import java.util.HashMap;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiImplicitParam;
import com.wordnik.swagger.annotations.ApiImplicitParams;
import com.wordnik.swagger.annotations.ApiOperation;

//页面访问路径http://localhost:8080/swagger-ui.html
@RestController
@Api(value = "Sample", description = "范例相关接口",produces = MediaType.ALL_VALUE)
public class HelloController {
	
	@ApiOperation(value = "欢迎页", notes = "欢迎页信息",httpMethod = "GET",produces = MediaType.ALL_VALUE)
	@GetMapping("/")
	@ResponseBody
	public String sayHello(){
		return "hello";
	}
	
	@ApiOperation(value = "欢迎页", notes = "欢迎页信息",httpMethod = "GET",produces = MediaType.ALL_VALUE)
    @GetMapping("/hello")
    @ResponseBody
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名字",required =true,dataType ="String"),
            @ApiImplicitParam(name = "text",value = "内容",required =true,dataType ="String")
    })
    public Object helloRest(@RequestParam String name,
                            @RequestParam String text){
        System.out.println("hello restful!");
        HashMap<String,String> result = new HashMap<String,String>();
        result.put("name", name);
        result.put("text", text);
        result.put("regard", "regarding");
        return result;
    }
}
