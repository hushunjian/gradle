package com.hushunjian.gradle.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;
import com.hushunjian.gradle.repo.ImportantTaskV2Repo;

@Service
@Transactional
public class QueryTestService {
	
	private final static String id = "id";
	
	private final static String importantTaskName = "importantTaskName";
	
	private final static String startDate = "startDate";
	
	private final static String groupId = "group_id";

	@Autowired
	private ImportantTaskV2Repo importantTaskV2Repo;

	public void test() {
		Map<String, Map<String, Object>> criteria = new HashMap<>();
/*		Map<String,Object> map1 = new HashMap<>();
		map1.put("notEq", 2);
		criteria.put(id, map1);*/
		Map<String,Object> map2 = new HashMap<>();
		map2.put("eq", "构件组1");
		criteria.put(importantTaskName, map2);
/*		Map<String,Object> map3 = new HashMap<>();
		map3.put("le", ZonedDateTime.now());
		criteria.put(startDate, map3);*/
		Map<String,Object> map4 = new HashMap<>();
		map4.put("eq", 1);
		criteria.put(groupId, map4);

		final Map<String, Map<String, Object>> result = new HashMap<>();
		result.putAll(a(criteria, ImportantTaskV2Entity.class));
		List<ImportantTaskV2Entity> findAll = importantTaskV2Repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(b(result,cb,root));
			return predicate;
		});
		findAll.forEach(importantTask ->{
			System.out.println("==========");
			System.out.println("id:"+importantTask.getId());
			System.out.println("importantTaskName:"+importantTask.getImportantTaskName());
			System.out.println("startDate:"+importantTask.getStartDate());
			System.out.println("==========");
		});
	}

	private List<Expression<Boolean>> b(Map<String, Map<String, Object>> criterias,CriteriaBuilder cb, Root<?> root) {
		List<Expression<Boolean>> expressions = new ArrayList<>();
		criterias.forEach((propertyName, values) -> {
			values.forEach((criteria, value) -> {
				if (value != null) {
					expressions.add(c(propertyName,criteria,value,cb,root));
				}
			});
		});
		return expressions;
	}

	private Predicate c(String propertyName, String criteria, Object value, CriteriaBuilder cb, Root<?> root) {
		Predicate predicate = null;
		switch (criteria) {
		case "eq":
			if (propertyName.contains("_")) {
				String[] split = propertyName.split("_");
				Path<Object> path = root.get(split[0]);
				for(int i = 1;i<split.length;i++){
					path = path.get(split[i]);
				}
				predicate = cb.equal(path, value);
			}else{
				predicate = cb.equal(root.get(propertyName), value);
			}
			break;
		case "notEq":
			predicate = cb.notEqual(root.get(propertyName), value);
			break;
		case "isNull":
			predicate = cb.isNull(root.get(propertyName));
			break;
		case "le":
			if (value instanceof Number) {
				predicate = cb.le(root.get(propertyName), (Number)value);
			}else if(value instanceof Date){
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (Date)value);
			}else if(value instanceof ZonedDateTime){
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (ZonedDateTime)value);
			}else if(value instanceof LocalDate){
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (LocalDate)value);
			}
		default:
			break;
		}
		return predicate;
	}

	/**
	 * 筛选出符合当前实体的查询结果
	 * 
	 * @param criteria
	 * @param clazz
	 * @return
	 */
	private Map<String, Map<String, Object>> a(Map<String, Map<String, Object>> criteria, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		// 筛选出符合属性的查询条件
		Set<Entry<String, Map<String, Object>>> entrySet = criteria.entrySet();
		Iterator<Entry<String, Map<String, Object>>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<String, Object>> next = iterator.next();
			if (next.getKey().contains("_")) {
				// 外键
				try {
					d(next.getKey(),clazz);
				} catch (Exception e) {
					iterator.remove();
				}
			}else{
				if (!fieldNames.contains(next.getKey())) {
					iterator.remove();
				}
			}
		}
		return criteria;
	}
	
	private void d(String foreignKey, Class<?> clazz) throws NoSuchFieldException{
		if (!foreignKey.contains("_")) {
			return;
		}
		String substring = foreignKey.substring(0, foreignKey.indexOf("_"));
		Field field = clazz.getDeclaredField(substring);
		Class<?> type = field.getType();
		foreignKey = foreignKey.substring(foreignKey.indexOf("_")+1,foreignKey.length());
		d(foreignKey,type);
	}
}
