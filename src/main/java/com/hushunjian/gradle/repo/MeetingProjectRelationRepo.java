package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.MeetingProjectRelation;

public interface MeetingProjectRelationRepo extends JpaRepository<MeetingProjectRelation, Long> {

}
