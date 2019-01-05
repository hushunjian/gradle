package com.hushunjian.gradle.service;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
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

	private final static String taskName = "taskV2_name";

	private final static String taskId = "taskV2_id";

	@Autowired
	private ImportantTaskV2Repo importantTaskV2Repo;

	public void test() {
		/**
		 * 传入的查询条件
		 */
		Map<String, Map<String, Object>> criteria = new HashMap<>();
		criteria.put(id, conditionValueToMap("notEq", 1));
		criteria.put(importantTaskName, conditionValueToMap("eq", "构件组1"));
		criteria.put(startDate, conditionValueToMap("le", ZonedDateTime.now()));
		criteria.put(groupId, conditionValueToMap("eq", 1));
		criteria.put(taskId, conditionValueToMap("eq", 1));
		criteria.put(taskName, conditionValueToMap("eq", "构件组1"));
		criteria.put(id, conditionValueToMap("in",Arrays.asList(1L,2L)));
		/**
		 * 筛选符合实体的查询条件
		 */
		final Map<String, Map<String, Object>> result = new HashMap<>();
		result.putAll(filter(criteria, ImportantTaskV2Entity.class));
		
		/**
		 * 动态查询
		 */
		List<ImportantTaskV2Entity> findAll = importantTaskV2Repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCriteria(result, cb, root));
			return predicate;
		});
		
		Pageable pageable = new PageRequest(0,10);
		
		Page<ImportantTaskV2Entity> findAll2 = importantTaskV2Repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCriteria(result, cb, root));
			return predicate;
		},pageable);
		
		/**
		 * 输出结果
		 */
		findAll.forEach(importantTask -> {
			System.out.println("==========");
			System.out.println("id:" + importantTask.getId());
			System.out.println("importantTaskName:" + importantTask.getImportantTaskName());
			System.out.println("startDate:" + importantTask.getStartDate());
			System.out.println("==========");
		});
		
		List<ImportantTaskV2Entity> findAll1 = findAll(importantTaskV2Repo,result);
		/**
		 * 输出结果
		 */
		findAll1.forEach(importantTask -> {
			System.out.println("==========");
			System.out.println("id:" + importantTask.getId());
			System.out.println("importantTaskName:" + importantTask.getImportantTaskName());
			System.out.println("startDate:" + importantTask.getStartDate());
			System.out.println("==========");
		});
	}
	
	/**
	 * 封装查询
	 * 
	 * @param repo
	 * @param result
	 * @return
	 */
	private <T extends JpaSpecificationExecutor<?>> List findAll(T repo,Map<String, Map<String, Object>> result){
		  return repo.findAll((root, query, cb) -> {
				Predicate predicate = cb.conjunction();
				List<Expression<Boolean>> expressions = predicate.getExpressions();
				expressions.addAll(appendCriteria(result, cb, root));
				return predicate;
			});
		}
	

	/**
	 * 将查询条件和值组装成map
	 * @param condition
	 * @param value
	 * @return
	 */
	private Map<String, Object> conditionValueToMap(String condition, Object value) {
		Map<String, Object> map = new HashMap<>();
		map.put(condition, value);
		return map;
	}

	/**
	 * 拼接条件
	 * 
	 * @param criterias
	 * @param cb
	 * @param root
	 * @return
	 */
	private List<Expression<Boolean>> appendCriteria(Map<String, Map<String, Object>> criterias, CriteriaBuilder cb, Root<?> root) {
		List<Expression<Boolean>> expressions = new ArrayList<>();
		criterias.forEach((propertyName, values) -> {
			values.forEach((criteria, value) -> {
				if (value != null) {
					expressions.add(appendCriteriaValue(propertyName, criteria, value, cb, root));
				}
			});
		});
		return expressions;
	}

	/**
	 * 针对每一个属性拼接条件
	 * 
	 * @param propertyName
	 * @param criteria
	 * @param value
	 * @param cb
	 * @param root
	 * @return
	 */
	private Predicate appendCriteriaValue(String propertyName, String criteria, Object value, CriteriaBuilder cb, Root<?> root) {
		Predicate predicate = null;
		switch (criteria) {
		case "eq":
			if (propertyName.contains("_")) {
				String[] split = propertyName.split("_");
				Path<Object> path = root.get(split[0]);
				for (int i = 1; i < split.length; i++) {
					path = path.get(split[i]);
				}
				predicate = cb.equal(path, value);
			} else {
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
				predicate = cb.le(root.get(propertyName), (Number) value);
			} else if (value instanceof Date) {
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (Date) value);
			} else if (value instanceof ZonedDateTime) {
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (ZonedDateTime) value);
			} else if (value instanceof LocalDate) {
				predicate = cb.lessThanOrEqualTo(root.get(propertyName), (LocalDate) value);
			}
		case "in":
			In<Object> in = cb.in(root.get(propertyName));
			if (value instanceof List) {
				List<Object> objs = (List)value;
				objs.forEach(obj -> in.value(obj));
			}
			predicate = in;
			break;
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
	private Map<String, Map<String, Object>> filter(Map<String, Map<String, Object>> criteria, Class<?> clazz) {
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
					d(next.getKey(), clazz);
				} catch (Exception e) {
					iterator.remove();
				}
			} else {
				if (!fieldNames.contains(next.getKey())) {
					iterator.remove();
				}
			}
		}
		return criteria;
	}

	/**
	 * 针对外键进行判断
	 * 
	 * @param foreignKey
	 * @param clazz
	 * @throws NoSuchFieldException
	 */
	private void d(String foreignKey, Class<?> clazz) throws NoSuchFieldException {
		if (!foreignKey.contains("_")) {
			// 已经不包含下划线了说明是最后的条件属性,判断实体中是否存在该字段
			clazz.getDeclaredField(foreignKey);
			return;
		}
		String substring = foreignKey.substring(0, foreignKey.indexOf("_"));
		Field field = clazz.getDeclaredField(substring);
		Class<?> type = field.getType();
		foreignKey = foreignKey.substring(foreignKey.indexOf("_") + 1, foreignKey.length());
		d(foreignKey, type);
	}
}
