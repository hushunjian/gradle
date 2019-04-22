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
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.hushunjian.gradle.annotation.DateFormat;
import com.hushunjian.gradle.annotation.ExcelTitle;
import com.hushunjian.gradle.annotation.ImportColumn;
import com.hushunjian.gradle.annotation.ImportSheet;
import com.hushunjian.gradle.annotation.MergedRegion;
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
        return filePath != null && (isExcel2003(filePath) || isExcel2007(filePath));
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
        dateCellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/erectionTime/yy"));
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

    public static ResponseEntity<byte[]> exportExcel(ExcelData data) throws IOException {
        //1.创建Excel文档
        HSSFWorkbook workbook = ExcelUtil.genHSSFWorkBook(data.getName());
        //设置文件来源信息
        ExcelUtil.genInformationProperties(workbook);
        HttpHeaders headers;
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
        ByteArrayOutputStream baos;
        //创建Excel表单
        String sheetName = data.getName();
        if (null == sheetName) {
            sheetName = "Sheet1";
        }
        HSSFSheet sheet = workbook.createSheet(sheetName.substring(0, sheetName.indexOf(".")));
        writeExcel(workbook, sheet, data);
        baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.close();
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    private static void writeExcel(HSSFWorkbook workbook, HSSFSheet sheet, ExcelData data) {
        writeTitlesToExcel(workbook, sheet, data.getTitles());
        writeRowsToExcel(workbook, sheet, data.getRows());
        mergedExcelColumn(sheet, data.getMergedColumns());
    }

    private static void mergedExcelColumn(HSSFSheet sheet, List<Integer> mergedColumns) {
        String firstCellValue = sheet.getRow(0).getCell(0).getStringCellValue();
        int index = 0;
        int lastRowNum = sheet.getLastRowNum();
        Iterator<Row> iterator = sheet.iterator();
        while (iterator.hasNext()){
            Row row = iterator.next();
            Integer rowNum = row.getRowNum();
            String cellValue = row.getCell(0).getStringCellValue();
            if (rowNum != 0 && firstCellValue.equals(cellValue) && rowNum != lastRowNum){
                continue;
            }
            if (rowNum - index > 1){
                if (rowNum == lastRowNum){
                    rowNum = rowNum + 1;
                }
                for (Integer column : mergedColumns){
                    CellRangeAddress callRangeAddress = new CellRangeAddress(index, rowNum - 1, column, column);
                    sheet.addMergedRegion(callRangeAddress);
                }
            }
            index = rowNum;
            firstCellValue = cellValue;
        }
    }

    private static void writeTitlesToExcel(HSSFWorkbook workbook, HSSFSheet sheet, List<String> titles) {
        int colIndex = 0;
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        //定义列的宽度
        HSSFRow headerRow = sheet.createRow(0);
        for (String field : titles) {
            //5.设置表头
            HSSFCell cell = headerRow.createCell(colIndex);
            cell.setCellValue(field);
            cell.setCellStyle(headerStyle);
            //定义列的宽度
            sheet.setColumnWidth(colIndex, 20 * 256);
            colIndex++;
        }
    }

    private static void writeRowsToExcel(HSSFWorkbook workbook, HSSFSheet sheet, List<List<Object>> rows) {
        //设置数据单元格样式
        HSSFCellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setBorderBottom(BorderStyle.THIN);
        rowStyle.setBorderLeft(BorderStyle.THIN);
        rowStyle.setBorderRight(BorderStyle.THIN);
        rowStyle.setBorderTop(BorderStyle.THIN);
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        for (int i = 0; i < rows.size(); i++) {
            HSSFRow dataRow = sheet.createRow(i + 1);
            List<Object> cellData = rows.get(i);
            for (int j = 0; j < cellData.size(); j++) {
                HSSFCell cell = dataRow.createCell(j);
                cell.setCellStyle(rowStyle);
                if (StringUtils.isEmpty(cellData.get(j))) {
                    continue;
                }
                cell.setCellValue(cellData.get(j).toString());
            }
        }
    }

    public static ExcelData setExcelData(List<Object> beans, String filename) {
        ExcelData excelData = new ExcelData();
        excelData.setName(filename);
        List<String> titles = getTitle(beans.get(0));
        List<Integer> columns = getMergedColumn(beans.get(0));
        excelData.setTitles(titles);
        List<List<Object>> rows = new ArrayList<>();
        for (Object bean : beans) {
            List<Object> row = getFiledValues(bean);
            rows.add(row);
        }
        excelData.setMergedColumns(columns);
        excelData.setRows(rows);
        return excelData;
    }

    private static List<String> getTitle(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<String> titles = new ArrayList<>();
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

    private static List<Integer> getMergedColumn(Object o){
        Field[] fields = o.getClass().getDeclaredFields();
        List<Integer> mergedColumnIndex = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof MergedRegion){
                    MergedRegion mergedRegion = (MergedRegion) annotation;
                    if (mergedRegion.value()){
                        mergedColumnIndex.add(mergedRegion.column());
                    }
                }
            }
        }
        return mergedColumnIndex;
    }

    private static List<Object> getFiledValues(Object o) {
        List<String> fieldNames = getFiledName(o);
        List<Object> values = new ArrayList<>();
        for (String fieldName : fieldNames) {
            values.add(getFieldValueByName(fieldName, o));
        }
        return values;
    }

    private static List<String> getFiledName(Object o) {
        List<String> fieldNames = new ArrayList<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                if (annotation instanceof ExcelTitle) {
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
            Field field = o.getClass().getDeclaredField(fieldName);
            String pattern = "yyyy-MM-dd HH:mm:ss";
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof DateFormat) {
                    pattern = ((DateFormat) annotation).value();
                }
            }
            if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(pattern);
                value = sdf.format((Date) value);
            } else if (value instanceof ZonedDateTime) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                value = ((ZonedDateTime) value).format(dtf);
            } else if (value instanceof LocalDateTime) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(pattern);
                value = dtf.format((LocalDateTime) value);
            }
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    public static Map<Class, List<Object>> importExcel(MultipartFile file, List<Class> tClass) {
        if (file == null || file.getSize() == 0) {
            logger.info("导入excel时文件为空或文件长度为0");
            return null;
        }
        try {
            Workbook workbook = ExcelUtil.genWorkBook(file);
            return readExcel(workbook, tClass);
        } catch (Exception e) {
            logger.info("读取excel文件异常");
            return null;
        }
    }

    private static Map<Class, List<Object>> readExcel(Workbook workbook, List<Class> tClass) throws Exception {
        int numberOfSheets = workbook.getNumberOfSheets();
        if (numberOfSheets == 0) {
            logger.info("excel文件标签页个数为0");
            return null;
        }
        Map<Class, List<Object>> sheetMap = new HashMap<>();
        for(Class targetClass : tClass){
            // 获取需要读取的sheet页
            ImportSheet importSheet = (ImportSheet)targetClass.getAnnotation(ImportSheet.class);
            int sheetIndex = 0;
            if (importSheet != null){
                sheetIndex = importSheet.sheet();
            }
            Sheet sheet = workbook.getSheetAt(sheetIndex);
            List<CellRangeAddress> combineCell = getCombineCell(sheet);
            List<Object> targets = new ArrayList<>();
            for (int j = 0; j < sheet.getPhysicalNumberOfRows(); j++) {
                Object t = targetClass.newInstance();
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;
                }
                Field[] fields = targetClass.getDeclaredFields();
                for (int k = 0; k < fields.length; k++) {
                    ImportColumn importAnnotation = fields[k].getAnnotation(ImportColumn.class);
                    if (importAnnotation == null) {
                        continue;
                    }
                    int column = importAnnotation.column();
                    Cell cell = row.getCell(column);
                    CellRangeAddress cellRangeAddress = isCombineCell(combineCell, cell);
                    if (cellRangeAddress != null){
                        cell = getMergedRegionCell(sheet, cellRangeAddress);
                    }
                    Object fieldValue = getCellValue(cell);
                    setFieldValue(fields[k], fieldValue, t);
                }
                targets.add(t);
            }
            sheetMap.put(targetClass, targets);
        }
        return sheetMap;
    }

    /**
     * 获取所有的合并单元格列
     *
     * @param sheet
     * @return
     */
    public static List<CellRangeAddress> getCombineCell(Sheet sheet){
        List<CellRangeAddress> cellRangeAddresses = new ArrayList<>();
        int numMergedRegions = sheet.getNumMergedRegions();
        for (int i = 0; i < numMergedRegions; i++){
            cellRangeAddresses.add(sheet.getMergedRegion(i));
        }
        return cellRangeAddresses;
    }

    /**
     * 获取合并单元格
     *
     * @param sheet
     * @param cellRangeAddress
     * @return
     */
    public static Cell getMergedRegionCell(Sheet sheet, CellRangeAddress cellRangeAddress){
        Cell cell = null;
        Row row = sheet.getRow(cellRangeAddress.getFirstRow());
        if (row != null){
            cell = row.getCell(cellRangeAddress.getFirstColumn());
        }
        return cell;
    }

    /**
     * 判断是否是合并单元格
     *
     * @param cellRangeAddresses
     * @param cell
     * @return
     */
    public static CellRangeAddress isCombineCell(List<CellRangeAddress> cellRangeAddresses, Cell cell){
        CellRangeAddress cellRangeAddress = null;
        if (cell == null){
            return null;
        }
        for (CellRangeAddress ca : cellRangeAddresses){
            if (cell.getColumnIndex() >= ca.getFirstColumn() && cell.getColumnIndex() <= ca.getLastColumn()){
                if (cell.getRowIndex() >= ca.getFirstRow() && cell.getRowIndex() <= ca.getLastRow()){
                    cellRangeAddress = ca;
                }
            }
        }
        return cellRangeAddress;
    }

    private static void setFieldValue(Field field, Object fieldValue, Object object) throws IllegalAccessException {
        if (field != null && fieldValue != null) {
            field.setAccessible(true);
            //获取字段类型
            String fieldType = field.getGenericType().toString();
            if (String.class.toString().equals(fieldType)) {
                fieldValue = fieldValue.toString();
            } else if (Long.class.toString().equals(fieldType)) {
                try {
                    fieldValue = Math.round(Double.valueOf(fieldValue.toString()));
                } catch (NumberFormatException e) {
                    fieldValue = null;
                }
            } else if (Integer.class.toString().equals(fieldType)) {
                try {
                    fieldValue = (int) Math.round(Double.valueOf(fieldValue.toString()));
                } catch (NumberFormatException e) {
                    fieldValue = null;
                }
            } else if (BigDecimal.class.toString().equals(fieldType)) {
                try {
                    fieldValue = BigDecimal.valueOf(Double.valueOf(fieldValue.toString()));
                } catch (NumberFormatException e) {
                    fieldValue = null;
                }
            } else if (Float.class.toString().equals(fieldType)) {
                try {
                    fieldValue = Float.valueOf(fieldValue.toString());
                } catch (NumberFormatException e) {
                    fieldValue = null;
                }
            } else if (Double.class.toString().equals(fieldType)) {
                try {
                    fieldValue = Double.valueOf(fieldValue.toString());
                } catch (NumberFormatException e) {
                    fieldValue = null;
                }
            } else if (ZonedDateTime.class.toString().equals(fieldType)) {
                try {
                    Date date = (Date) fieldValue;
                    fieldValue = ZonedDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
                } catch (ClassCastException e) {
                    fieldValue = null;
                }
            } else if (Date.class.toString().equals(fieldType)) {
                try {
                    fieldValue = new SimpleDateFormat("yyyy/MM/dd").parse(fieldValue.toString());
                } catch (ParseException e) {
                    fieldValue = null;
                }
            } else {
                fieldValue = null;
            }
            field.set(object, fieldValue);
        }
    }

    /**
     * 转换单元格类型
     *
     * @param cell
     * @return
     */
    private static Object getCellValue(Cell cell) {
        Object cellValue = "";
        if (null != cell) {
            CellType cellType = cell.getCellTypeEnum();
            switch (cellType) {
                // 字符串
                case STRING:
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                // Boolean
                case BOOLEAN:
                    cellValue = cell.getBooleanCellValue();
                    break;
                // 公式
                case FORMULA:
                    cellValue = cell.getCellFormula();
                    break;
                // 数字
                case NUMERIC:
                    if (HSSFDateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue();
                    } else {
                        cellValue = cell.getNumericCellValue();
                    }
                    break;
                // 空值
                case BLANK:
                    cellValue = "";
                    break;
                // 故障
                case ERROR:
                    cellValue = "";
                    break;
                default:
                    cellValue = "";
                    break;
            }
        }
        return cellValue;
    }
}
