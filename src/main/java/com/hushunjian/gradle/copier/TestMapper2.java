package com.hushunjian.gradle.copier;

import java.util.List;
import java.util.Map;

import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;

import com.hushunjian.gradle.dto.JPAEntity3DTO;
import com.hushunjian.gradle.dto.JPAEntityDTO;
import com.hushunjian.gradle.dto.OperatorDTO;
import com.hushunjian.gradle.dto.StringToIntegerDTO;
import com.hushunjian.gradle.entity.JPAEntityB;
import com.hushunjian.gradle.entity.JPAEntityC;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.TestStringToInteger;
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
    
    List<OperatorDTO> asOperators(Page<User> users);
    
    List<StringToIntegerDTO> asStringToIntegerDTO(List<TestStringToInteger> beans);
    
    TestStringToInteger asTestStringToInteger(StringToIntegerDTO bean);
    
    @Mappings({
    	@Mapping(target = "course", constant = "Chinese")
    })
    JPAEntityB toJPAEntityB(JPAEntityDTO bean);
    
    JPAEntity3DTO toJPAEntity3DTO(JPAEntityC bean);
    
    @Mappings({
    	@Mapping(target = "id", ignore = true),
    	@Mapping(target = "version", ignore = true)
    })
    void copyValue(JPAEntity3DTO dto, @MappingTarget JPAEntityC entity);
    
    
    List<OperatorDTO> asOperator(List<User> beans, @Context TestContext testContext);
    
}
