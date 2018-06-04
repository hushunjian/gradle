package com.hushunjian.gradle.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.copier.SourceTargetMapper;
import com.hushunjian.gradle.dto.UserBasicInfoDto;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;
import com.hushunjian.gradle.searchConditionEntiy.GetAllUserByConditionEntity;
import com.hushunjian.gradle.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

//页面访问路径http://localhost:8080/swagger-ui.html
@RestController
@Api(value = "Sample", description = "范例相关接口",produces = MediaType.ALL_VALUE)
public class HelloController {
	
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value = "欢迎页", notes = "欢迎页信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/",method=RequestMethod.POST)
	@ResponseBody
	public String sayHello(){
		return "hello";
	}
	
	@ApiOperation(value = "欢迎页", notes = "欢迎页信息",produces = MediaType.ALL_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name",value = "用户名字",required =true,paramType = "query"),
            @ApiImplicitParam(name = "text",value = "内容",required =true,paramType = "query")
    })
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	@ResponseBody
    public Object helloRest(@RequestParam(value="name",required=true) String name,
    						@RequestParam(value="text",required=true) String text){
        System.out.println("hello restful!");
        HashMap<String,String> result = new HashMap<String,String>();
        result.put("name", name);
        result.put("text", text);
        result.put("regard", "regarding");
        return result;
    }
	
	@ApiOperation(value = "添加用户", notes = "添加用户",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/addUser",method=RequestMethod.POST)
	@ResponseBody
	public Operator addUser(@ModelAttribute User user){
		Operator operator = SourceTargetMapper.INSTANCE.asOperator(user);
		userService.addUser(user,operator);
		return operator;
	}
	
	@ApiOperation(value = "获取用户信息", notes = "根据用户id获取用户信息",produces = MediaType.ALL_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required =true,paramType = "query",dataType="Long")
    })
	@RequestMapping(value="/getUserById",method=RequestMethod.GET)
	@ResponseBody
    public User getUserById(@RequestParam(value="id",required=true) Long id){
        System.out.println("getUserById:"+id);
        return userService.getUserById(id);
    }
	
	@ApiOperation(value = "删除用户", notes = "根据用户id删除用户",produces = MediaType.ALL_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id",value = "用户id",required =true,paramType = "query",dataType="Long")
    })
	@RequestMapping(value="/deleteUserById",method=RequestMethod.GET)
	@ResponseBody
    public void deleteUserById(@RequestParam(value="id",required=true) Long id){
        System.out.println("deleteUserById:"+id);
        userService.deleteUserById(id);
    }
	
	@ApiOperation(value = "获取用户信息", notes = "获取所有用户信息",produces = MediaType.ALL_VALUE)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageIndex",value = "开始页",required =true,paramType = "query",dataType="int"),
            @ApiImplicitParam(name = "pageSize",value = "页大小",required =true,paramType = "query",dataType="int")
    })
	@RequestMapping(value="/getAllUserByPage",method=RequestMethod.GET)
	@ResponseBody
    public Object getAllUserByPage(@RequestParam(value="pageIndex",required=true) int pageIndex,
    						       @RequestParam(value="pageSize",required=true) int pageSize){
        System.out.println("getAllUserByPage:开始页:"+pageIndex+";页大小:"+pageSize);
        return userService.getAllUserByPage(pageIndex,pageSize);
    }
	
	
	@ApiOperation(value = "获取用户信息", notes = "根据查询条件获取用户信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/getAllUserByCondition",method=RequestMethod.GET)
	@ResponseBody
    public Object getAllUserByCondition(@ModelAttribute GetAllUserByConditionEntity getAllUserByConditionEntity){
        System.out.println("getAllUserByCondition:开始页:"+getAllUserByConditionEntity.getPageIndex()+";页大小:"+getAllUserByConditionEntity.getPageSize());
        return userService.getAllUserByCondition(getAllUserByConditionEntity);
    }
	
	@ApiOperation(value = "获取用户信息", notes = "根据用户姓名获取用户信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/getAllUserByUserName",method=RequestMethod.GET)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userName",value = "用户姓名",required =true,paramType = "query",dataType="String")
	})
	@ResponseBody
    public List<User> getAllUserByUserName(@RequestParam(value="userName",required=true) String userName){
        System.out.println("getAllUserByUserName:userName:"+userName);
        return userService.getAllUserByUserName(userName);
    }
	
	@ApiOperation(value = "获取用户信息", notes = "根据用户姓名模糊查询获取用户信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/getAllUserByUserNameLike",method=RequestMethod.GET)
	@ApiImplicitParams({
        @ApiImplicitParam(name = "userName",value = "用户姓名",required =true,paramType = "query",dataType="String")
	})
	@ResponseBody
    public List<User> getAllUserByUserNameLike(@RequestParam(value="userName",required=true) String userName){
        System.out.println("getAllUserByUserNameLike:"+userName);
        return userService.getAllUserByUserNameLike(userName);
    }
	
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息以及创建人信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/getAllUserBasicInfo",method=RequestMethod.GET)
	@ApiImplicitParams({
         @ApiImplicitParam(name = "pageIndex",value = "开始页",required =true,paramType = "query",dataType="int"),
         @ApiImplicitParam(name = "pageSize",value = "页大小",required =true,paramType = "query",dataType="int")
	 })
    public List<UserBasicInfoDto> getAllUserBasicInfo(@RequestParam(value="pageIndex",required=true) int pageIndex,
		       							  			  @RequestParam(value="pageSize",required=true) int pageSize){
		System.out.println("getAllUserBasicInfo:开始页:"+pageIndex+";页大小:"+pageSize);
        return userService.getAllUserBasicInfo(pageIndex,pageSize);
    }
	
	
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ResponseBody
	public User updateUser(@ModelAttribute User user){
		return userService.updateUser(user);
	}
	
	@ApiOperation(value = "获取用户信息", notes = "获取用户信息以及创建人信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/getAllUserBasicInfoJPA",method=RequestMethod.GET)
	@ApiImplicitParams({
         @ApiImplicitParam(name = "pageIndex",value = "开始页",required =true,paramType = "query",dataType="int"),
         @ApiImplicitParam(name = "pageSize",value = "页大小",required =true,paramType = "query",dataType="int")
	 })
    public List<UserBasicInfoDto> getAllUserBasicInfoJPA(@RequestParam(value="pageIndex",required=true) int pageIndex,
		       							  			  	 @RequestParam(value="pageSize",required=true) int pageSize){
		System.out.println("getAllUserBasicInfoJPA:开始页:"+pageIndex+";页大小:"+pageSize);
        return userService.getAllUserBasicInfoJPA(pageIndex,pageSize);
    }
	
	@ApiOperation(value = "修改用户信息", notes = "修改用户信息",produces = MediaType.ALL_VALUE)
	@RequestMapping(value="/updateUserBySelect",method=RequestMethod.POST)
	@ResponseBody
	public User updateUserBySelect(@RequestBody User user){
		return userService.updateUserBySelect(user);
	}
	
}
