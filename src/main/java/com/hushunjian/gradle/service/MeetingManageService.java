package com.hushunjian.gradle.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.MeetingManageMapper;
import com.hushunjian.gradle.dto.MeetingDto;
import com.hushunjian.gradle.entity.MeetingManage;
import com.hushunjian.gradle.entity.MeetingPersonRelation;
import com.hushunjian.gradle.entity.MeetingProjectRelation;
import com.hushunjian.gradle.entity.MeetingReceiptInfo;
import com.hushunjian.gradle.entity.TimerTask;
import com.hushunjian.gradle.repo.MeetingManageRepo;
import com.hushunjian.gradle.repo.MeetingPersonRelationRepo;
import com.hushunjian.gradle.repo.MeetingProjectRelationRepo;
import com.hushunjian.gradle.repo.MeetingReceiptInfoRepo;
import com.hushunjian.gradle.repo.TimerTaskRepo;
import com.hushunjian.gradle.request.AddMeetingRequest;
import com.hushunjian.gradle.request.QueryAllMeetingRequest;

@Service
@Transactional
public class MeetingManageService {

	@Autowired
	private MeetingManageRepo meetingManageRepo;
	@Autowired
	private TimerTaskRepo timerTaskRepo;
	@Autowired
	private MeetingPersonRelationRepo meetingPersonRelationRepo;
	@Autowired
	private MeetingProjectRelationRepo meetingProjectRelationRepo;
	@Autowired
	private MeetingReceiptInfoRepo meetingReceiptInfoRepo;
	@PersistenceContext
    EntityManager entityManager;

	public void addMeeting(AddMeetingRequest addMeetingRequest) {
		//保存会议信息
		MeetingManage meetingManage = MeetingManageMapper.INSTANCE.asMeetingManage(addMeetingRequest);
		meetingManageRepo.save(meetingManage);
		//保存参与会议的人员信息
		List<MeetingPersonRelation> meetingPersonRelations=MeetingManageMapper.INSTANCE.asMeetingPersonRelations(addMeetingRequest.getMeetingPersonRelationDtos());
		for(MeetingPersonRelation meetingPersonRelation : meetingPersonRelations){
			meetingPersonRelation.setMeetId(meetingManage.getMeetId());
			meetingPersonRelationRepo.save(meetingPersonRelation);
			//保存会议回执消息
			MeetingReceiptInfo meetingReceiptInfo = new  MeetingReceiptInfo();
			meetingReceiptInfo.setMeetingPersonRelationId(meetingPersonRelation.getId());
			meetingReceiptInfoRepo.save(meetingReceiptInfo);
		}
		//保存会议关联的项目信息
		List<MeetingProjectRelation> meetingProjectRelationDtos=MeetingManageMapper.INSTANCE.asMeetingProjectRelations(addMeetingRequest.getMeetingProjectRelationDtos());
		for(MeetingProjectRelation meetingProjectRelation : meetingProjectRelationDtos){
			meetingProjectRelation.setMeetId(meetingManage.getMeetId());
			meetingProjectRelationRepo.save(meetingProjectRelation);
		}
		//保存会议的提示时间信息
		if(addMeetingRequest.getTimerTaskDto()!=null){
			TimerTask timerTask = MeetingManageMapper.INSTANCE.asTimerTask(addMeetingRequest.getTimerTaskDto());
			timerTask.setMeetId(meetingManage.getMeetId());
			timerTaskRepo.save(timerTask);
		}
	}

	public List<MeetingDto> queryMeet(QueryAllMeetingRequest queryAllMeetingRequest) {
		List<MeetingDto> meetingDtos = new ArrayList<MeetingDto>();
		
		List<Long> personRelationMeetIds = meetingPersonRelationRepo.findByMeetPersonId(queryAllMeetingRequest.getLongInPersonId());
		//根据relationProjectIds,personRelationMeetIds查询出meetId
		List<Long> projectRelationMeetIds = meetingProjectRelationRepo.findByMeetProjectIdsAndMeetIds(queryAllMeetingRequest.getRelationProjectIds(),personRelationMeetIds);
		queryAllMeeting(queryAllMeetingRequest,projectRelationMeetIds);
		
		
		
/*		//设置分页
		Pageable pageable=new PageRequest(queryAllMeetingRequest.getPageNo()-1,queryAllMeetingRequest.getPageSize());
		//根据longInPersonId查询出所有参加的会议
		List<Long> meetingIds = meetingPersonRelationRepo.getAllMeetingIdByPersonId(queryAllMeetingRequest.getLongInPersonId(),pageable);
		//根据meetingIds查出所有需要的信息
		for(Long meetId : meetingIds){
			MeetingDto meetingDto = new MeetingDto();
			//查询出会议信息
			MeetingManage meetingManage = meetingManageRepo.findOne(meetId);
			meetingDto.setMeetingManage(meetingManage);
			//查询出参与会议人员信息
			List<MeetingPersonRelation> meetingPersonRelations = meetingPersonRelationRepo.findAllPersonByMeetId(meetId);
			meetingDto.setMeetingPersonRelations(meetingPersonRelations);
			//查询出会议关联的项目信息
			List<MeetingProjectRelation> meetingProjectRelations = meetingProjectRelationRepo.findAllProjectByMeetId(meetId);
			meetingDto.setMeetingProjectRelations(meetingProjectRelations);
			meetingDtos.add(meetingDto);
		}*/
		return meetingDtos;
	}
	
	private Page<MeetingManage> queryAllMeeting(QueryAllMeetingRequest queryAllMeetingRequest,List<Long> ids){
		//分页,排序
		Sort sort = new Sort(Direction.DESC, "meetId");
		Pageable pageable = new PageRequest(queryAllMeetingRequest.getPageNo()-1,queryAllMeetingRequest.getPageSize(),sort);
		Page<MeetingManage> meetingManagePage = meetingManageRepo.findAll(new Specification<MeetingManage>() {
			@Override
			public Predicate toPredicate(Root<MeetingManage> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				Predicate predicate = cb.conjunction();
	            List<Expression<Boolean>> expressions = predicate.getExpressions();
	            if(StringUtils.isNotBlank(queryAllMeetingRequest.getLongInPersonId())){
	            	expressions.add(cb.equal(root.<String>get("meetCreatePersonId"), queryAllMeetingRequest.getLongInPersonId()));
	            }
	            In<Long> in = cb.in(root.<Long>get("meetId"));
	            for(Long id : ids){
	            	in.value(id);
	            }
	            expressions.add(in);
				return predicate;
			}
		}, pageable);
		return meetingManagePage;
	}

	public List<MeetingManage> getAllMeetWhenTimeOverTwoFour() {
		return meetingManageRepo.getAllMeetWhenTimeOverTwoFour();
	}
	
}
