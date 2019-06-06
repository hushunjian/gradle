package com.hushunjian.gradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.dto.AuditProcessDTO;
import com.hushunjian.gradle.enumeration.AuditProcessTypeEnum;
import com.hushunjian.gradle.service.AuditProcessService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("AuditProcessController")
@Api(value = "AuditProcessController", description = "审核流程相关")
@RequestMapping(value = "/auditProcess")
public class AuditProcessController extends BaseController {
	
	@Autowired
	private AuditProcessService auditProcessService;
	
	@ResponseBody
	@GetMapping(value="/findByProjectIdAndProcessType")
	@ApiOperation(value="根据单位工程和审核流程获取审核流程数据")
	public Object findByProjectIdAndProcessType(@RequestParam String projectId,
												@RequestParam AuditProcessTypeEnum auditProcessType){
		List<AuditProcessDTO> auditProcessDTOs = auditProcessService.findByProjectIdAndProcessType(projectId, auditProcessType);
		return success(auditProcessDTOs);
	}
}
