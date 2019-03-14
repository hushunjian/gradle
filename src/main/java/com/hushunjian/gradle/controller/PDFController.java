package com.hushunjian.gradle.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.util.PDFUtil;
import com.lowagie.text.DocumentException;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("PDFController")
@Api(value = "PDFController", description = "PDF测试接口")
@RequestMapping(value = "/pdf")
public class PDFController {
	
	@PostMapping(value="/createPDF")
	@ApiOperation(value="createPDF")
	public void createPDF(@RequestBody @Validated String html) throws IOException, DocumentException{
		OutputStream out = new FileOutputStream("E:\\test.pdf");
		PDFUtil.createPDF(out, html);
	}
}
