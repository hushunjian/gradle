package com.hushunjian.gradle.util;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.CriteriaBuilder.In;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.hushunjian.gradle.entity.ImportantTaskV2Entity;
import com.hushunjian.gradle.enumeration.CriteriaEnum;

@SuppressWarnings("all")
public class DynamicUtil {

	/**
	 * findAll
	 * 
	 * @param repo
	 * @param conditions
	 * @return
	 */
	public static <T extends JpaSpecificationExecutor<?>> List findAll(T repo, Map<String, Map<CriteriaEnum, Object>> allConditon, Class clazz) {
		// 筛选查询条件
		Map<String, Map<CriteriaEnum, Object>> conditions = filterConditions(allConditon, clazz);
		// 查询
		return repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCondition(conditions, cb, root));
			return predicate;
		});
	}

	/**
	 * findAll
	 * 
	 * @param repo
	 * @param conditions
	 * @return
	 */
	public static <T extends JpaSpecificationExecutor<?>> Page findAll(T repo, Map<String, Map<CriteriaEnum, Object>> allConditon, Class clazz, Pageable pageable) {
		// 筛选查询条件
		Map<String, Map<CriteriaEnum, Object>> conditions = filterConditions(allConditon, clazz);
		// 查询
		return repo.findAll((root, query, cb) -> {
			Predicate predicate = cb.conjunction();
			List<Expression<Boolean>> expressions = predicate.getExpressions();
			expressions.addAll(appendCondition(conditions, cb, root));
			return predicate;
		}, pageable);
	}
	
	/**
	 * 筛选符合条件的查询条件
	 * @param allConditon
	 * @param clazz
	 * @return
	 */
	private static Map<String, Map<CriteriaEnum, Object>> filterConditions(Map<String, Map<CriteriaEnum, Object>> allConditon, Class clazz){
		Map<String, Map<CriteriaEnum, Object>> conditions = new HashMap<>();
		conditions.putAll(filterCondition(allConditon, clazz));
		return conditions;
	}
	
	/**
	 * 筛选出符合当前实体的查询结果
	 * 
	 * @param conditions
	 * @param clazz
	 * @return
	 */
	private static Map<String, Map<CriteriaEnum, Object>> filterCondition(Map<String, Map<CriteriaEnum, Object>> conditions, Class<?> clazz) {
		Field[] fields = clazz.getDeclaredFields();
		List<String> fieldNames = new ArrayList<>();
		for (Field field : fields) {
			fieldNames.add(field.getName());
		}
		// 筛选出符合属性的查询条件
		Set<Entry<String, Map<CriteriaEnum, Object>>> entrySet = conditions.entrySet();
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
		return conditions;
	}
	
	/**
	 * 针对外键进行判断
	 * 
	 * @param foreignKey
	 * @param clazz
	 * @throws NoSuchFieldException
	 */
	private static void filterForeignKey(String foreignKey, Class<?> clazz) throws NoSuchFieldException {
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
	
	/**
	 * 拼接条件
	 * 
	 * @param conditions
	 * @param cb
	 * @param root
	 * @return
	 */
	private static List<Expression<Boolean>> appendCondition(Map<String, Map<CriteriaEnum, Object>> conditions, CriteriaBuilder cb, Root<?> root) {
		List<Expression<Boolean>> expressions = new ArrayList<>();
		conditions.forEach((propertyName, values) -> {
			values.forEach((condition, value) -> {
				if (value != null) {
					expressions.add(appendExpression(propertyName, condition, value, cb, root));
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
	private static Predicate appendExpression(String propertyName, CriteriaEnum criteria, Object value, CriteriaBuilder cb, Root<?> root) {
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
		case ge:
			if (value instanceof Number) {
				predicate = cb.ge((Path<Number>) path, (Number) value);
			} else if (value instanceof Date) {
				predicate = cb.greaterThanOrEqualTo((Path<Date>) path, (Date) value);
			} else if (value instanceof ZonedDateTime) {
				predicate = cb.greaterThanOrEqualTo((Path<ZonedDateTime>) path, (ZonedDateTime) value);
			} else if (value instanceof LocalDate) {
				predicate = cb.greaterThanOrEqualTo((Path<LocalDate>) path, (LocalDate) value);
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
	 * 获取条件
	 * 
	 * @param propertyName
	 * @param root
	 * @return
	 */
	private static Path<Object> appendPath(String propertyName, Root<?> root) {
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
}
