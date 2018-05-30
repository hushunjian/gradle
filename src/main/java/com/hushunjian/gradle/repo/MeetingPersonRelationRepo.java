package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.MeetingPersonRelation;

public interface MeetingPersonRelationRepo extends JpaRepository<MeetingPersonRelation, Long> {

}
