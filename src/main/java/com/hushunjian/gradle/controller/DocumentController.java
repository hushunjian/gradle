package com.hushunjian.gradle.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.dto.DocumentDTO;
import com.hushunjian.gradle.enumeration.DocumentTypeEnum;
import com.hushunjian.gradle.request.AddDocumentRequest;
import com.hushunjian.gradle.request.AddRelationRequest;
import com.hushunjian.gradle.request.QueryRelationRequest;
import com.hushunjian.gradle.service.DocumentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("DocumentController")
@Api(value = "DocumentController", description = "文档管理")
@RequestMapping(value = "/document")
public class DocumentController extends BaseController {
	
	@Autowired
	private DocumentService documentService;
	
	@ResponseBody
	@PostMapping(value="/addDocument")
	@ApiOperation(value="添加文档数据")
	public Object addDocument(@Validated @RequestBody AddDocumentRequest addDocumentRequest){
		documentService.addDocument(addDocumentRequest);
		return success();
	}
	
	@ResponseBody
	@GetMapping(value="/findByType")
	@ApiOperation(value="获取文档信息")
	public Object findByType(@RequestParam DocumentTypeEnum documentType){
		List<DocumentDTO> documentDTOs = documentService.findByType(documentType);
		return success(documentDTOs);
	}
	
	@ResponseBody
	@PostMapping(value="/addRelation")
	@ApiOperation(value="添加文档之间关系")
	public Object addRelation(@Validated @RequestBody AddRelationRequest addRelationRequest){
		documentService.addRelation(addRelationRequest);
		return success();
	}
	
	@ResponseBody
	@PostMapping(value="/findRelation")
	@ApiOperation(value="查询文档关系")
	public Object findRelation(@Validated @RequestBody QueryRelationRequest queryRelationRequest){
		List<DocumentDTO> documentDTOs = documentService.findRelation(queryRelationRequest);
		return success(documentDTOs);
	}
}
