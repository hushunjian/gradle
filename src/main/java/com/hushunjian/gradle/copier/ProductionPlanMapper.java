package com.hushunjian.gradle.copier;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.entity.ProductionPlanEntity;
import com.hushunjian.gradle.request.AddProductionPlanRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ProductionPlanMapper {
	ProductionPlanMapper INSTANCE = Mappers.getMapper(ProductionPlanMapper.class);

	ProductionPlanEntity asProductionPlanEntity(AddProductionPlanRequest bean);
}
