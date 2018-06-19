package com.hushunjian.gradle.service;

import org.junit.runner.RunWith;

import com.hushunjian.gradle.repo.MeetingManageRepo;
import com.hushunjian.gradle.repo.MeetingPersonRelationRepo;
import com.hushunjian.gradle.repo.MeetingProjectRelationRepo;
import com.hushunjian.gradle.repo.MeetingReceiptInfoRepo;
import com.hushunjian.gradle.repo.TimerTaskRepo;

import mockit.Expectations;
import mockit.Injectable;
import mockit.Tested;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
public class MeetingManageServiceTest {

	@Injectable
	private MeetingManageRepo meetingManageRepo;
	@Injectable
	private TimerTaskRepo timerTaskRepo;
	@Injectable
	private MeetingPersonRelationRepo meetingPersonRelationRepo;
	@Injectable
	private MeetingProjectRelationRepo meetingProjectRelationRepo;
	@Injectable
	private MeetingReceiptInfoRepo meetingReceiptInfoRepo;
	
	@Tested
	MeetingManageService meetingManageService = new MeetingManageService();
	
	
	public void addMeeting() throws Exception{
		new Expectations(){
			
		};
		
	}
	
	
}
