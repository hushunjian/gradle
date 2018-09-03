package com.hushunjian.gradle.copier;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.OperatorDTO;
import com.hushunjian.gradle.dto.OperatorDTOExcelStyle;
import com.hushunjian.gradle.entity.Operator;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE,nullValueCheckStrategy=NullValueCheckStrategy.ALWAYS)
public interface OperatorMapper {
    OperatorMapper INSTANCE = Mappers.getMapper(OperatorMapper.class);
    
    OperatorDTO asOperatorDTO(Operator bean);
    
    List<OperatorDTO> asOperatorDTOs(List<Operator> beans);
    
    OperatorDTOExcelStyle asOperatorDTOExcelStyle(OperatorDTO bean);
    
    List<OperatorDTOExcelStyle> asOperatorDTOExcelStyles(List<OperatorDTO> beans);
}
