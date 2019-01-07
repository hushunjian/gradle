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
import com.hushunjian.gradle.enumeration.CriteriaEnum;
import com.hushunjian.gradle.repo.ImportantTaskV2Repo;
import com.hushunjian.gradle.repo.TaskV2Repo;
import com.hushunjian.gradle.util.DynamicUtil;

@Service
@Transactional
@SuppressWarnings("all")
public class QueryTestService {

	private final static String id = "id";

	private final static String importantTaskName = "importantTaskName";

	private final static String startDate = "startDate";

	private final static String groupId = "group.id";

	private final static String taskName = "taskV2.name";
	
	private final static String taskStartDate = "taskV2.startDate";

	private final static String taskId = "taskV2.id";

	@Autowired
	private ImportantTaskV2Repo importantTaskV2Repo;
	
	public void test() {
		/**
		 * 传入的查询条件
		 */
		Map<String, Map<CriteriaEnum, Object>> criteria = new HashMap<>();
//		criteria.put(id, conditionValueToMap(CriteriaEnum.eq, 1));
//		criteria.put(importantTaskName, conditionValueToMap(CriteriaEnum.notEq, "构件组1"));
//		criteria.put(startDate, conditionValueToMap(CriteriaEnum.le, ZonedDateTime.now()));
//		criteria.put(groupId, conditionValueToMap(CriteriaEnum.eq, 1));
//		criteria.put(taskId, conditionValueToMap(CriteriaEnum.eq, 1));
//		criteria.put(taskName, conditionValueToMap(CriteriaEnum.eq, "构件组1"));
//		criteria.put(id, conditionValueToMap(CriteriaEnum.in, Arrays.asList(1L, 2L)));
		criteria.put(taskStartDate, conditionValueToMap(CriteriaEnum.le, ZonedDateTime.now()));
		/**
		 * 筛选符合实体的查询条件
		 */
		final Map<String, Map<CriteriaEnum, Object>> result = new HashMap<>();
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

		Pageable pageable = new PageRequest(0, 10);

		Page<ImportantTaskV2Entity> findAll2 = importantTaskV2Repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCriteria(result, cb, root));
			return predicate;
		}, pageable);

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

		List<ImportantTaskV2Entity> findAll1 = findAll(importantTaskV2Repo, result);
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
	private <T extends JpaSpecificationExecutor<?>> List findAll(T repo, Map<String, Map<CriteriaEnum, Object>> result) {
		return repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCriteria(result, cb, root));
			return predicate;
		});
	}

	/**
	 * 将查询条件和值组装成map
	 * 
	 * @param condition
	 * @param value
	 * @return
	 */
	private Map<CriteriaEnum, Object> conditionValueToMap(CriteriaEnum condition, Object value) {
		Map<CriteriaEnum, Object> map = new HashMap<>();
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
	private List<Expression<Boolean>> appendCriteria(Map<String, Map<CriteriaEnum, Object>> criterias, CriteriaBuilder cb, Root<?> root) {
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
	 * 获取条件
	 * 
	 * @param propertyName
	 * @param root
	 * @return
	 */
	private Path<Object> appendPath(String propertyName, Root<?> root) {
		Path<Object> path = null;
		if (propertyName.contains(".")) {
			String[] split = propertyName.split("\\.");
			path = root.get(split[0]);
			for (int i = 1; i < split.length; i++) {
				path = path.get(split[i]);
			}
		} else {
			path = root.get(propertyName);
		}
		return path;
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
	private Predicate appendCriteriaValue(String propertyName, CriteriaEnum criteria, Object value, CriteriaBuilder cb, Root<?> root) {
		Predicate predicate = null;
		Path<?> path = appendPath(propertyName, root);
		if (path == null) {
			return predicate;
		}
		switch (criteria) {
		case eq:
			predicate = cb.equal(path, value);
			break;
		case notEq:
			predicate = cb.notEqual(path, value);
			break;
		case isNull:
			predicate = cb.isNull(path);
			break;
		case le:
			if (value instanceof Number) {
				predicate = cb.le((Path<Number>) path, (Number) value);
			} else if (value instanceof Date) {
				predicate = cb.lessThanOrEqualTo((Path<Date>) path, (Date) value);
			} else if (value instanceof ZonedDateTime) {
				predicate = cb.lessThanOrEqualTo((Path<ZonedDateTime>) path, (ZonedDateTime) value);
			} else if (value instanceof LocalDate) {
				predicate = cb.lessThanOrEqualTo((Path<LocalDate>) path, (LocalDate) value);
			}
			break;
		case in:
			In<Object> in = cb.in(path);
			if (value instanceof List) {
				List<Object> objs = (List) value;
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
	private Map<String, Map<CriteriaEnum, Object>> filter(Map<String, Map<CriteriaEnum, Object>> criteria, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		// 筛选出符合属性的查询条件
		Set<Entry<String, Map<CriteriaEnum, Object>>> entrySet = criteria.entrySet();
		Iterator<Entry<String, Map<CriteriaEnum, Object>>> iterator = entrySet.iterator();
		while (iterator.hasNext()) {
			Entry<String, Map<CriteriaEnum, Object>> next = iterator.next();
			if (next.getKey().contains(".")) {
				// 外键
				try {
					filterForeignKey(next.getKey(), clazz);
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
	private void filterForeignKey(String foreignKey, Class<?> clazz) throws NoSuchFieldException {
		if (!foreignKey.contains(".")) {
			// 已经不包含"."说明是最后的条件属性,判断实体中是否存在该字段
			clazz.getDeclaredField(foreignKey);
			return;
		}
		String fieldName = foreignKey.substring(0, foreignKey.indexOf("."));
		Field field = clazz.getDeclaredField(fieldName);
		Class<?> type = field.getType();
		foreignKey = foreignKey.substring(foreignKey.indexOf(".") + 1, foreignKey.length());
		filterForeignKey(foreignKey, type);
	}

	public void test1() {
		Map<String, Map<CriteriaEnum, Object>> allConditon = new HashMap<>();
		allConditon.put(taskStartDate, conditionValueToMap(CriteriaEnum.ge, ZonedDateTime.now()));
		
		List<ImportantTaskV2Entity> importantTasks = DynamicUtil.findAll(importantTaskV2Repo, allConditon, ImportantTaskV2Entity.class);
		importantTasks.forEach(importantTask -> {
			System.out.println("==========");
			System.out.println("id:" + importantTask.getId());
			System.out.println("importantTaskName:" + importantTask.getImportantTaskName());
			System.out.println("startDate:" + importantTask.getStartDate());
			System.out.println("==========");
		});
	}

	public List<Long> test2(Integer pageNo, Integer pageSize) {
		Map<String, Map<CriteriaEnum, Object>> conditons = new HashMap<>();
		conditons.put(id, conditionValueToMap(CriteriaEnum.notEq, 1));
//		conditons.put(taskStartDate, conditionValueToMap(CriteriaEnum.le, ZonedDateTime.now()));
//		conditons.put(taskId, conditionValueToMap(CriteriaEnum.in, 1));
		
		if (pageNo <= 0) {
			pageNo = 1;
		}
		if (pageSize <= 0) {
			pageSize = 10;
		}
		Pageable pageable = new PageRequest(pageNo - 1, pageSize);
		Page<ImportantTaskV2Entity> importantTasks = DynamicUtil.findAll(importantTaskV2Repo, conditons, ImportantTaskV2Entity.class, pageable);
		List<Long> ids = new ArrayList<>();
		importantTasks.forEach(importantTask -> {
			ids.add(importantTask.getId());
			System.out.println("==========");
			System.out.println("id:" + importantTask.getId());
			System.out.println("importantTaskName:" + importantTask.getImportantTaskName());
			System.out.println("startDate:" + importantTask.getStartDate());
			System.out.println("==========");
		});
		return ids;
	}
}
