package com.hushunjian.gradle.dto;

import java.util.List;

import com.hushunjian.gradle.entity.MeetingManage;
import com.hushunjian.gradle.entity.MeetingPersonRelation;
import com.hushunjian.gradle.entity.MeetingProjectRelation;

import lombok.Data;

@Data
public class MeetingDto {
    private MeetingManage meetingManage;
    private List<MeetingPersonRelation> meetingPersonRelations;
    private List<MeetingProjectRelation> meetingProjectRelations;
}
