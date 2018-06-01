package com.hushunjian.gradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.dto.MeetingDto;
import com.hushunjian.gradle.request.AddMeetingRequest;
import com.hushunjian.gradle.request.QueryAllMeetingRequest;
import com.hushunjian.gradle.service.MeetingManageService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("MeetingManageController")
@Api(value = "MeetingManageController", description = "会议相关接口",produces = MediaType.ALL_VALUE)
@RequestMapping(value = "/meetingManage")
public class MeetingManageController extends BaseController {
	
	@Autowired
	private MeetingManageService meetingManageService;
	
	@PostMapping(value="/addMeeting",name="添加会议")
	@ApiOperation(value="添加会议",notes="添加会议")
	public Object addMeeting(@RequestBody AddMeetingRequest addMeetingRequest){
		meetingManageService.addMeeting(addMeetingRequest);
		return success();
	}
	
	@PostMapping(value="/queryMeet",name="查询会议")
	@ApiOperation(value="查询会议",notes="查询会议")
	public Object queryMeet(@RequestBody QueryAllMeetingRequest queryAllMeetingRequest){
		List<MeetingDto> meetingDtos = meetingManageService.queryMeet(queryAllMeetingRequest);
		return success(meetingDtos);
	}
	
}
