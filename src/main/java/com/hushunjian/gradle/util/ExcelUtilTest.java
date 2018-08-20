/**
 * Copyright (C), 2015-2018, 华规软件（上海）有限公司
 * FileName: ExcelUtil
 * Author:   shenqicheng
 * Date:     2018/6/5 16:11
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 谌启成          2018/6/5 16:11      1.0.0              无
 */
package com.hushunjian.gradle.util;


import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.springframework.util.StringUtils;

import com.hushunjian.gradle.annotation.ExcelTitle;
import com.hushunjian.gradle.dto.ExcelData;

/**
 * 〈功能简述〉<br>
 * 〈判断Excel文件的版本〉
 *
 * @author shenqicheng
 * @create 2018/6/5
 * @since 1.0.0
 */
public class ExcelUtilTest {


    public static void  exportExcel(HttpServletResponse response, ExcelData data) throws IOException {
        //1.创建Excel文档
        HSSFWorkbook workbook = new HSSFWorkbook();
        //设置文件来源信息
        response.setHeader("content-Type", "application/octet-stream");
        response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(data.getName(), "utf-8"));
        fillData(response.getOutputStream(), workbook, data);
    }

    /**
     * 将数据填充进来
     *
     * @param workbook
     * @param data
     */
    public static void fillData(OutputStream out, HSSFWorkbook workbook, ExcelData data) throws IOException {
        //创建Excel表单
        String sheetName = data.getName();
        if (null == sheetName) {
            sheetName = "Sheet1";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName.substring(0,sheetName.indexOf(".")));
        writeExcel(workbook, sheet, data);	
        workbook.write(out);
        workbook.close();
    }

    private static void writeExcel(HSSFWorkbook workbook, HSSFSheet sheet, ExcelData data) {
        writeTitlesToExcel(workbook, sheet, data.getTitles());
        writeRowsToExcel(sheet, data.getRows());
    }

    private static void writeTitlesToExcel(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles) {
        int colIndex = 0;
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        //定义列的宽度
        HSSFRow headerRow = sheet.createRow(0);
        for (String field : titles) {
            //5.设置表头
            HSSFCell cell = headerRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(headerStyle);
            //定义列的宽度
            sheet.setColumnWidth(colIndex, 15 * 256);
            colIndex++;
        }
    }

    private static void writeRowsToExcel(HSSFSheet sheet, List<List<Object>> rows) {
        for (int i = 0; i < rows.size(); i++) {
            HSSFRow dataRow = sheet.createRow(i + 1);
            List<Object> cellData = rows.get(i);
            for (int j = 0; j < cellData.size(); j++) {
                if(StringUtils.isEmpty(cellData.get(j))){
                    continue;
                }
                dataRow.createCell(j).setCellValue(cellData.get(j).toString());
            }
        }
    }

    public static ExcelData setExcelData(List<Object> beans,String filename) {
        ExcelData excelData = new ExcelData();
        excelData.setName(filename);
        List<String> titles = getTitle(beans.get(0));
        excelData.setTitles(titles);
        List<List<Object>> rows = new ArrayList<List<Object>>();
        for (Object bean : beans) {
            List<Object> row = getFiledValues(bean);
            rows.add(row);
        }
        excelData.setRows(rows);
        return excelData;
    }

    private static List<String> getTitle(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> titles = new ArrayList<String>();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ExcelTitle) {
                    ExcelTitle title = (ExcelTitle) annotation;
                    titles.add(title.value());
                }
            }
        }
        return titles;
    }

    private static List<Object> getFiledValues(Object o) {
        List<String> fieldNames = getFiledName(o);
        List<Object> values = new ArrayList<Object>();
        for (int i = 0; i < fieldNames.size(); i++) {
            values.add(getFieldValueByName(fieldNames.get(i), o));
        }
        return values;
    }

    private static List<String> getFiledName(Object o) {
        List<String> fieldNames = new ArrayList<String>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
        	Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for(Annotation annotation : fieldAnnotations){
                if(annotation instanceof ExcelTitle){
                    fieldNames.add(field.getName());
                }
            }
        }
        return fieldNames;
    }

    private static Object getFieldValueByName(String fieldName, Object o) {
        try {
            String firstLetter = fieldName.substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + fieldName.substring(1);
            Method method = o.getClass().getMethod(getter, new Class[]{});
            Object value = method.invoke(o, new Object[]{});
            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                value = sdf.format((Date) value);
            }
            return value;
        } catch (Exception e) {
            return "";
        }
    }
}
