package com.hushunjian.gradle.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.copier.OperatorMapper;
import com.hushunjian.gradle.dto.OperatorDTO;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.repo.OperatorRepo;
import com.hushunjian.gradle.request.ExportOperatorRequest;

@Service
@Transactional
public class ExportService {
	
	@Autowired
	private OperatorRepo operatorRepo;

	public List<OperatorDTO> export(ExportOperatorRequest exportOperatorRequest) {
		List<Operator> operators = operatorRepo.findByOperatorNameLike("%"+exportOperatorRequest.getOperatorName()+"%");
		List<OperatorDTO> operatorDTOs = OperatorMapper.INSTANCE.asOperatorDTOs(operators);
		return operatorDTOs;
	}

}
