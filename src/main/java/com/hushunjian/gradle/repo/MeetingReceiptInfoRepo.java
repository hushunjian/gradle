package com.hushunjian.gradle.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hushunjian.gradle.entity.MeetingReceiptInfo;

public interface MeetingReceiptInfoRepo extends JpaRepository<MeetingReceiptInfo, Long> {

}
