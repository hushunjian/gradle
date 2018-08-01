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


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

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
public class ExcelUtil {
    private static String EXCEL_2003_FILE_TYPE = ".xls";
    private static String EXCEL_2007_FILE_TYPE = ".xlsx";
    private static Logger logger = LoggerFactory.getLogger(ExcelUtil.class);

    /**
     * 是否是2003的excel
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    /**
     * 是否是2007的excel
     *
     * @param filePath
     * @return
     */
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    /**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     */
    public static boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            return false;
        }
        return true;
    }

    public static String getFileType(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            logger.error("fileName is empty. ", fileName);
            throw new RuntimeException("fileName is empty.  " + fileName);
        }
        return fileName.substring(fileName.lastIndexOf("."), fileName.length());
    }

    /**
     * 添加文件来源信息 for xls
     *
     * @param workbook
     * @param values
     */
    public static void genInformationProperties(HSSFWorkbook workbook, String... values) {
        //2.创建文档摘要
        workbook.createInformationProperties();
        //3.获取文档信息，并配置
        DocumentSummaryInformation dsi = workbook.getDocumentSummaryInformation();
        //3.1文档类别
        dsi.setCategory(values.length > 0 ? values[0] : "资源信息");
        //3.2设置文档管理员
        dsi.setManager(values.length > 1 ? values[1] : "admin");
        //3.3设置组织机构
        dsi.setCompany(values.length > 2 ? values[2] : "华规软件(上海)有限公司");
        //4.获取摘要信息并配置
        SummaryInformation si = workbook.getSummaryInformation();
        //4.1设置文档主题
        si.setSubject(values.length > 3 ? values[3] : "资源信息表");
        //4.2.设置文档标题
        si.setTitle(values.length > 4 ? values[4] : "资源信息");
        //4.3 设置文档作者
        si.setAuthor(values.length > 5 ? values[5] : "华规软件(上海)有限公司");
        //4.4设置文档备注
        si.setComments(values.length > 6 ? values[6] : "无");
        //创建日期显示格式
        HSSFCellStyle dateCellStyle = workbook.createCellStyle();
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
    }

    /**
     * 创建xls文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static HSSFWorkbook genHSSFWorkBook(String fileName) throws IOException {
        HSSFWorkbook workbook = null;
        //得到文件类型：xls
        String fileType = getFileType(fileName);
        if (StringUtils.isEmpty(fileType)) {
            logger.error("don't has xls type,fileName = ", fileName);
            throw new RuntimeException("don't has xls type,fileName = " + fileName);
        }
        if (EXCEL_2003_FILE_TYPE.equals(fileType.trim().toLowerCase())) {
            //创建2003 Excel
            workbook = new HSSFWorkbook();
        }
        return workbook;
    }

    /**
     * 创建xlsx文件
     *
     * @param fileName
     * @return
     * @throws IOException
     */
    public static XSSFWorkbook genXSSFWorkBook(String fileName) throws IOException {
        XSSFWorkbook workbook = null;
        //得到文件类型：xlsx
        String fileType = getFileType(fileName);
        if (StringUtils.isEmpty(fileType)) {
            logger.error("don't has xlsx type,fileName = ", fileName);
            throw new RuntimeException("don't xlsx type,fileName = " + fileName);
        }
        if (EXCEL_2007_FILE_TYPE.equals(fileType.trim().toLowerCase())) {
            //创建2007 Excel
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }

    /**
     * for reader excel file
     *
     * @param file
     * @return
     * @throws IOException
     */
    public static Workbook genWorkBook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        //得到文件类型：xls或xlsx
        String fileType = getFileType(file.getOriginalFilename());
        if (!validateExcel(file.getOriginalFilename())) {
            logger.error("don't has xls or xlsx type,fileName = ", file.getOriginalFilename());
            throw new RuntimeException("don't  xls or xlsx type,fileName = " + file.getOriginalFilename());
        }
        if (isExcel2003(file.getOriginalFilename())) {
            //创建2003 Excel
            workbook = new HSSFWorkbook(file.getInputStream());
        } else if (isExcel2007(file.getOriginalFilename())) {
            //创建2007 Excel
            workbook = new XSSFWorkbook(file.getInputStream());
        }
        return workbook;
    }

    public static Boolean fileExists(File file) {
        if (file.exists()) {
            return true;
        }
        return false;
    }

    public static Boolean fileNotExists(File file) {
        return !fileExists(file);
    }

    public static ResponseEntity<byte[]> exportExcel(ExcelData data) throws IOException {
        //1.创建Excel文档
        HSSFWorkbook workbook = ExcelUtil.genHSSFWorkBook(data.getName());
        //设置文件来源信息
        ExcelUtil.genInformationProperties(workbook);
        HttpHeaders headers = null;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(data.getName(), "utf-8"));
        return fillData(workbook, data, headers);
    }

    /**
     * 将数据填充进来
     *
     * @param workbook
     * @param data
     */
    public static ResponseEntity<byte[]> fillData(HSSFWorkbook workbook, ExcelData data, HttpHeaders headers) throws IOException {
        ByteArrayOutputStream baos = null;
        //创建Excel表单
        String sheetName = data.getName();
        if (null == sheetName) {
            sheetName = "Sheet1";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName.substring(0,sheetName.indexOf(".")));
        writeExcel(workbook, sheet, data);
        baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();
        return new ResponseEntity<byte[]>(baos.toByteArray(), headers, HttpStatus.OK);
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
            fieldNames.add(field.getName());
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
