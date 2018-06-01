package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.entity.MeetingPersonRelation;

public interface MeetingPersonRelationRepo extends JpaRepository<MeetingPersonRelation, Long> {

	@Query(value="")
	List<Long> getAllMeetingIdByPersonId(String longInPersonId, Pageable pageable);

	
	@Query(value="SELECT t1 FROM MeetingPersonRelation t1 WHERE t1.meetId=?1")
	List<MeetingPersonRelation> findAllPersonByMeetId(Long meetId);

}
