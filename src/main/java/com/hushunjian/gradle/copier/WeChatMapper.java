package com.hushunjian.gradle.copier;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.entity.AccessToken;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface WeChatMapper {
    WeChatMapper INSTANCE = Mappers.getMapper(WeChatMapper.class);
    
    void copyProperties(AccessToken source,@MappingTarget AccessToken target);
}
