package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.entity.MeetingProjectRelation;

public interface MeetingProjectRelationRepo extends JpaRepository<MeetingProjectRelation, Long> {

	@Query(value="SELECT t1 FROM MeetingProjectRelation t1 WHERE t1.meetId=?1")
	List<MeetingProjectRelation> findAllProjectByMeetId(Long meetId);

}
