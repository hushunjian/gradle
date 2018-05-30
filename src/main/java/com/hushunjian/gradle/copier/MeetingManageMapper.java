package com.hushunjian.gradle.copier;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import com.hushunjian.gradle.dto.MeetingPersonRelationDto;
import com.hushunjian.gradle.dto.MeetingProjectRelationDto;
import com.hushunjian.gradle.dto.TimerTaskDto;
import com.hushunjian.gradle.entity.MeetingManage;
import com.hushunjian.gradle.entity.MeetingPersonRelation;
import com.hushunjian.gradle.entity.MeetingProjectRelation;
import com.hushunjian.gradle.entity.TimerTask;
import com.hushunjian.gradle.request.AddMeetingRequest;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MeetingManageMapper {
    MeetingManageMapper INSTANCE = Mappers.getMapper(MeetingManageMapper.class);
    
    MeetingManage asMeetingManage(AddMeetingRequest bean);
    
    TimerTask asTimerTask(TimerTaskDto bean);
    
    MeetingPersonRelation asMeetingPersonRelation(MeetingPersonRelationDto bean);
    
    List<MeetingPersonRelation> asMeetingPersonRelations(List<MeetingPersonRelationDto> beans);
    
    MeetingProjectRelation asMeetingProjectRelation(MeetingProjectRelationDto bean);
    
    List<MeetingProjectRelation> asMeetingProjectRelations(List<MeetingProjectRelationDto> beans);
}
