package com.hushunjian.gradle.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hushunjian.gradle.copier.ProductionPlanMapper;
import com.hushunjian.gradle.entity.ProductionPlanEntity;
import com.hushunjian.gradle.request.AddProductionPlanRequest;
import com.hushunjian.gradle.service.ProductionPlanService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController("ProductionPlanController")
@Api(value = "ProductionPlanController", description = "生产计划相关接口")
@RequestMapping(value = "/productionPlan")
@SuppressWarnings("all")
public class ProductionPlanController extends BaseController {
	
	@Autowired
	private ProductionPlanService productionPlanService;
	
	@ApiOperation(value = "添加生产计划")
	@PostMapping(value = "/addProductionPlan")
	public Object addProductionPlan(@RequestBody AddProductionPlanRequest addProductionPlanRequest) {
		ProductionPlanEntity productionPlan = ProductionPlanMapper.INSTANCE.asProductionPlanEntity(addProductionPlanRequest);
		Long productionPlanId = productionPlanService.addProductionPlan(productionPlan);
		return success(productionPlanId);
	}
	
	@ApiOperation(value = "获取已产生最大梁跨编号")
	@PostMapping(value = "/getMaxBeamSpanNum")
	public Object getMaxBeamSpanNum() {
		Long maxBeamSpanNum = productionPlanService.getMaxBeamSpanNum();
		return success(maxBeamSpanNum);
	}
	
}
