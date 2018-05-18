package com.hushunjian.gradle.copier;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;

@Mapper
public interface SourceTargetMapper {
    SourceTargetMapper INSTANCE = Mappers.getMapper(SourceTargetMapper.class);
    
    @Mapping(source="userName",target="operatorName")
    Operator asOperator(User user);
}
