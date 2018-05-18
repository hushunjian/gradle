package org.mapstruct.example;

import com.hushunjian.gradle.compile.SourceTargetMapper;
import com.hushunjian.gradle.entity.Operator;
import com.hushunjian.gradle.entity.User;

public class Test {
	public static void main(String[] args) {
		User user = new User();
		user.setUserName("张三");
		Operator operator = SourceTargetMapper.INSTANCE.asOperator(user);
		System.out.println(operator.getOperatorName());
	}
}
