package com.hushunjian.gradle.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.dto.ExcelData;
import com.hushunjian.gradle.dto.OperatorDTO;
import com.hushunjian.gradle.request.ExportOperatorRequest;
import com.hushunjian.gradle.service.ExportService;
import com.hushunjian.gradle.util.ExportExcelUtils;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("ExportController")
@Api(value = "ExportController", description = "excel导出接口")
@RequestMapping(value = "/export")
public class ExportController extends BaseController {

	@Autowired
	private ExportService exportService;
	
	@ResponseBody
	@PostMapping(value="/export1")
	@ApiOperation(value="post请求导出excel",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object export1(HttpServletResponse response,@RequestBody ExportOperatorRequest exportOperatorRequest) throws IOException{
		List<OperatorDTO> operatorDTOs = exportService.export(exportOperatorRequest);
		if(operatorDTOs.size()>0){
			ExcelData data = new ExcelData();
        	List<Object> beans = new ArrayList<Object>();
        	for(OperatorDTO operatorDTO : operatorDTOs){
        		beans.add(operatorDTO);
        	}
        	data = ExportExcelUtils.setExcelData(beans);
        	ExportExcelUtils.exportExcel(response,"导出人员信息.xls",data);
		}else{
			return success("暂无数据");
		}
		return success();
	}

	
	@ResponseBody
	@GetMapping(value="/export2")
	@ApiOperation(value="post请求导出exce2",produces=MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public Object export2(HttpServletResponse response) throws IOException{
		ExportOperatorRequest exportOperatorRequest = new ExportOperatorRequest();
		List<OperatorDTO> operatorDTOs = exportService.export(exportOperatorRequest);
		if(operatorDTOs.size()>0){
			ExcelData data = new ExcelData();
        	List<Object> beans = new ArrayList<Object>();
        	for(OperatorDTO operatorDTO : operatorDTOs){
        		beans.add(operatorDTO);
        	}
        	data = ExportExcelUtils.setExcelData(beans);
        	ExportExcelUtils.exportExcel(response,"导出人员信息.xls",data);
		}else{
			return success("暂无数据");
		}
		return success();
	}
}
