package com.hushunjian.gradle.copier;

import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.OperatorDTO;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;

@Mapper(nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface TestMapper2 {
    TestMapper2 INSTANCE = Mappers.getMapper(TestMapper2.class);
    @Mapping(source="user.userName",target="operatorName")
    @Mapping(source="user.updatedTime",target="updatedTime")
    @Mapping(source="user.updatedBy",target="updatedBy")
    @Mapping(source="user.createdBy",target="createdBy")
    @Mapping(source="user.status",target="status")
    @Mapping(source="user.id",target="id")
    @Mapping(source="user.age",target="age")
    @Mapping(source="user.createdTime",target="createdTime")
    @Mapping(target="num",ignore = true)
    OperatorDTO asOperator(User user,Operator operator);
    
    @Mapping(target="operatorName",ignore = true)
    @Mapping(target="num",ignore = true)
    OperatorDTO asOperator(User user,@Context TestContext testContext,int a);
   
    @Mapping(target="operatorName",ignore = true)
    @Mapping(target="num",ignore = true)
    OperatorDTO asOperator(User user);
    
    List<OperatorDTO> asOperators(List<User> users);
}
