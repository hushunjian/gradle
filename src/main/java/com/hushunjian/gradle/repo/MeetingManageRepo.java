package com.hushunjian.gradle.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.hushunjian.gradle.entity.MeetingManage;

public interface MeetingManageRepo extends JpaRepository<MeetingManage, Long>,JpaSpecificationExecutor<MeetingManage>{
	@Query(value="SELECT t1 FROM MeetingManage t1 WHERE t1.meetStatus = 0 AND DATEDIFF(current_timestamp,t1.meetTime) BETWEEN 2 and 4")
	List<MeetingManage> getAllMeetWhenTimeOverTwoFour();

}
