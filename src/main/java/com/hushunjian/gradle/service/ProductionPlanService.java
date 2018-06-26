package com.hushunjian.gradle.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hushunjian.gradle.entity.ProductionPlanEntity;
import com.hushunjian.gradle.repo.ProductionPlanDetailRepo;
import com.hushunjian.gradle.repo.ProductionPlanRepo;


@Service
@Transactional
public class ProductionPlanService{

	@Autowired
	private ProductionPlanRepo productionPlanRepo;
	@Autowired
	private ProductionPlanDetailRepo productionPlanDetailRepo;
	
	public Long addProductionPlan(ProductionPlanEntity productionPlan) {
		ProductionPlanEntity productionPlanEntity = productionPlanRepo.save(productionPlan);
		return productionPlanEntity.getId();
	}

	public Long getMaxBeamSpanNum() {
		Long maxBeamSpanNum = productionPlanDetailRepo.getMaxBeamSpanNum();
		if(maxBeamSpanNum==null){
			maxBeamSpanNum = 0L;
		}
		return maxBeamSpanNum;
	}

}
