package com.hushunjian.gradle.util;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hpsf.SummaryInformation;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.sl.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Table.Cell;
import com.hushunjian.gradle.annotation.ExcelTitle;
import com.hushunjian.gradle.dto.ExcelData;
import com.microsoft.schemas.office.visio.x2012.main.CellType;

import lombok.extern.slf4j.Slf4j;

/**
 * 〈功能简述〉<br>
 * 〈判断Excel文件的版本〉
 *
 * @author shenqicheng
 * @create 2018/6/5
 * @since 1.0.0
 */
@Slf4j
public class ExcelUtil1 {
/*    private static Logger logger = LoggerFactory.getLogger(ExcelUtil1.class);

    private ExcelUtil1() {
        //
    }


    *//**
     * 是否是2003的excel
     *
     * @param filePath
     * @return
     *//*
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    *//**
     * 是否是2007的excel
     *
     * @param filePath
     * @return
     *//*
    public static boolean isExcel2007(String filePath) {
        log.info("验证filePath:[{}]", filePath);
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }

    *//**
     * 验证EXCEL文件
     *
     * @param filePath
     * @return
     *//*
    public static boolean validateExcel(String filePath) {
        return filePath != null && (isExcel2003(filePath) || isExcel2007(filePath));
    }

    public static String getFileType(String fileName) {
        if (StringUtils.isEmpty(fileName)) {
            logger.error("fileName is empty. {}", fileName);
            //throw new BusinessException("fileName is empty.  " + fileName);
        }
        return fileName.substring(fileName.lastIndexOf('.'), fileName.length());
    }

    *//**
     * 添加文件来源信息 for xls
     *
     * @param workbook
     * @param values
     *//*
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

    *//**
     * 创建xls文件
     *
     * @param fileName
     * @return
     * @throws IOException
     *//*
    public static HSSFWorkbook genHSSFWorkBook(String fileName) throws IOException {
        HSSFWorkbook workbook = null;
        String excel2003FileType = ".xls";
        //得到文件类型：xls
        String fileType = getFileType(fileName);
        if (StringUtils.isEmpty(fileType)) {
            logger.error("don't has xls type,fileName = {}", fileName);
            //throw new BusinessException("don't has xls type,fileName = " + fileName);
        }

        if (excel2003FileType.equalsIgnoreCase(fileType.trim())) {
            //创建2003 Excel
            workbook = new HSSFWorkbook();
        }
        return workbook;
    }

    *//**
     * 创建xlsx文件
     *
     * @param fileName
     * @return
     * @throws IOException
     *//*
    public static XSSFWorkbook genXSSFWorkBook(String fileName) throws IOException {
        XSSFWorkbook workbook = null;
        String excel2007FileType = ".xlsx";
        //得到文件类型：xlsx
        String fileType = getFileType(fileName);
        if (StringUtils.isEmpty(fileType)) {
            logger.error("don't has xlsx type,fileName = {}", fileName);
            //throw new BusinessException("don't xlsx type,fileName = " + fileName);
        }

        if (excel2007FileType.equalsIgnoreCase(fileType.trim())) {
            //创建2007 Excel
            workbook = new XSSFWorkbook();
        }
        return workbook;
    }


    *//**
     * 创建xlsx文件
     *
     * @param fileName
     * @return
     * @throws IOException
     *//*
    public static SXSSFWorkbook genSXSSFWorkbook(String fileName) throws IOException {
        SXSSFWorkbook workbook = null;
        String excel2007FileType = ".xlsx";
        //得到文件类型：xlsx
        String fileType = getFileType(fileName);
        if (StringUtils.isEmpty(fileType)) {
            logger.error("don't has xlsx type,fileName = {}", fileName);
            //throw new BusinessException("don't xlsx type,fileName = " + fileName);
        }

        if (excel2007FileType.equalsIgnoreCase(fileType.trim())) {
            //创建2007 Excel
            workbook = new SXSSFWorkbook(5000);
        }
        return workbook;
    }

    *//**
     * for reader excel file
     *
     * @param file
     * @return
     * @throws IOException
     *//*
    public static Workbook genWorkBook(MultipartFile file) throws IOException {
        Workbook workbook = null;
        //得到文件类型：xls或xlsx
        if (!validateExcel(file.getOriginalFilename())) {
            logger.error("don't has xls or xlsx type,fileName = {}", file.getOriginalFilename());
            //throw new BusinessException("don't  xls or xlsx type,fileName = " + file.getOriginalFilename());
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
        return file.exists();
    }

    public static Boolean fileNotExists(File file) {
        return !fileExists(file);
    }

    public static ResponseEntity<byte[]> exportExcel(List<ExcelData> datas, String fileName) throws IOException {
        //1.创建Excel文档
        SXSSFWorkbook workbook = ExcelUtil1.genSXSSFWorkbook(fileName);
        //设置文件来源信息
        //ExcelUtil.genInformationProperties(workbook); 2007需要换一种
        HttpHeaders headers;
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
        return fillData(workbook, datas, headers);
    }

    *//**
     * 将数据填充进来
     *
     * @param workbook
     * @param datas
     *//*
    public static ResponseEntity<byte[]> fillData(SXSSFWorkbook workbook, List<ExcelData> datas, HttpHeaders headers) throws IOException {
        ByteArrayOutputStream baos;
        for (ExcelData data : datas) {
            //创建Excel表单
            String sheetName = data.getName();
            SXSSFSheet sheet = workbook.createSheet(sheetName);
            writeExcel(workbook, sheet, data);
        }
        baos = new ByteArrayOutputStream();
        workbook.write(baos);
        workbook.dispose();
        workbook.close();
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }

    private static void writeExcel(SXSSFWorkbook workbook, SXSSFSheet sheet, ExcelData data) {
        writeTitlesToExcel(workbook, sheet, data.getTitles());
        writeRowsToExcel(workbook, sheet, data.getRows());
    }

    private static void writeTitlesToExcel(SXSSFWorkbook workbook, SXSSFSheet sheet, List<ExcelBaseDTO> titles) {
        int colIndex = 0;
        //创建标题的显示样式
        CellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(IndexedColors.YELLOW.index);
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setBorderBottom(BorderStyle.THIN);
        headerStyle.setBorderLeft(BorderStyle.THIN);
        headerStyle.setBorderRight(BorderStyle.THIN);
        headerStyle.setBorderTop(BorderStyle.THIN);
        headerStyle.setAlignment(HorizontalAlignment.CENTER);
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headerStyle.setWrapText(true);
        //定义列的宽度
        SXSSFRow headerRow = sheet.createRow(0);
        headerRow.setHeightInPoints(20);
        for (ExcelBaseDTO field : titles) {
            //设置合并单元格
            addCellRange(sheet, field.getMergeRely());
            //定义列的宽度
            sheet.setColumnWidth(colIndex, (int) (field.getWidth() * 256));
            //5.设置表头
            SXSSFCell cell = headerRow.createCell(colIndex);
            cell.setCellValue(field.getKey());
            cell.setCellStyle(headerStyle);
            colIndex++;
        }
    }


    *//**
     * 单元格合并操作
     *
     * @param sheet
     * @param arr   起始行, 终止行, 起始列, 终止列
     *//*
    public static void addCellRange(SXSSFSheet sheet, int[] arr) {
        if (arr != null && arr.length != 0) {
            CellRangeAddress cra = new CellRangeAddress(arr[0], arr[1], arr[2], arr[3]);
            sheet.addMergedRegion(cra);
        }
    }

    private static void writeRowsToExcel(SXSSFWorkbook workbook, SXSSFSheet sheet, List<List<Object>> rows) {
        //设置数据单元格样式
        CellStyle rowStyle = workbook.createCellStyle();
        rowStyle.setBorderBottom(BorderStyle.THIN);
        rowStyle.setBorderLeft(BorderStyle.THIN);
        rowStyle.setBorderRight(BorderStyle.THIN);
        rowStyle.setBorderTop(BorderStyle.THIN);
        rowStyle.setFillForegroundColor(IndexedColors.WHITE.index);
        rowStyle.setAlignment(HorizontalAlignment.CENTER);
        rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        rowStyle.setWrapText(true);
        for (int i = 0; i < rows.size(); i++) {
            SXSSFRow dataRow = sheet.createRow(i + 1);
            List<Object> cellData = rows.get(i);
            for (int j = 0; j < cellData.size(); j++) {
                SXSSFCell cell = dataRow.createCell(j);
                cell.setCellStyle(rowStyle);
                if (cellData.get(j) == null) {
                    continue;
                }
                cell.setCellValue(cellData.get(j).toString());
            }
        }
    }

    public static ExcelData setExcelData(List<Object> beans, String filename) {
        ExcelData excelData = new ExcelData();
        excelData.setName(filename);
        List<ExcelBaseDTO> titles = getTitle(beans.get(0));
        excelData.setTitles(titles);
        List<List<Object>> rows = new ArrayList<>();
        for (Object bean : beans) {
            List<Object> row = getFiledValues(bean);
            long rowCount = row.stream().filter(Objects::nonNull).count();
            if (rowCount != 0) {
                rows.add(row);
            }
        }
        excelData.setRows(rows);
        return excelData;
    }

    private static List<ExcelBaseDTO> getTitle(Object o) {
        Field[] fields = o.getClass().getDeclaredFields();
        List<ExcelBaseDTO> titles = new ArrayList<>();
        for (Field field : fields) {
            field.setAccessible(true);
            Annotation[] annotations = field.getDeclaredAnnotations();
            for (Annotation annotation : annotations) {
                if (annotation instanceof ExcelTitle) {
                    ExcelTitle title = (ExcelTitle) annotation;
                    ExcelBaseDTO dto = new ExcelBaseDTO();
                    dto.setKey(title.value());
                    dto.setMergeRely(title.mergeRely());
                    dto.setHeight(title.height());
                    dto.setWidth(title.width());
                    dto.setFormat(title.format());
                    dto.setDatabaseFormat(title.databaseFormat());
                    titles.add(dto);
                }
            }
        }
        return titles;
    }

    private static List<Object> getFiledValues(Object o) {
        List<ExcelBaseDTO> fieldNames = getFiledName(o);
        List<Object> values = new ArrayList<>();
        for (int i = 0; i < fieldNames.size(); i++) {
            values.add(getFieldValueByName(fieldNames.get(i), o));
        }
        return values;
    }

    private static List<ExcelBaseDTO> getFiledName(Object o) {
        List<ExcelBaseDTO> fieldNames = new ArrayList<>();
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getDeclaredAnnotations();
            for (Annotation annotation : fieldAnnotations) {
                if (annotation instanceof ExcelTitle) {
                    ExcelTitle title = (ExcelTitle) annotation;
                    ExcelBaseDTO dto = new ExcelBaseDTO();
                    dto.setKey(field.getName());
                    dto.setMergeRely(title.mergeRely());
                    dto.setHeight(title.height());
                    dto.setWidth(title.width());
                    dto.setFormat(title.format());
                    dto.setDatabaseFormat(title.databaseFormat());
                    fieldNames.add(dto);
                }
            }
        }
        return fieldNames;
    }

    private static Object getFieldValueByName(ExcelBaseDTO dto, Object o) {
        try {
            String firstLetter = dto.getKey().substring(0, 1).toUpperCase();
            String getter = "get" + firstLetter + dto.getKey().substring(1);
            Method method = o.getClass().getMethod(getter);
            Object value = method.invoke(o);
            if (dto.isFormat()) {
                return dateFormatValue(value, dto);
            }
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    private static Object dateFormatValue(Object value, ExcelBaseDTO dto) {
        try {
            if (value instanceof String && StringUtils.isNotEmpty(value.toString())) {
                SimpleDateFormat format = new SimpleDateFormat(dto.getDatabaseFormat());
                value = format.parse(value.toString());
            } else if (value instanceof Date) {
                SimpleDateFormat sdf = new SimpleDateFormat(dto.getDatabaseFormat());
                value = sdf.format((Date) value);
            } else if (value instanceof ZonedDateTime) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dto.getDatabaseFormat());
                value = ((ZonedDateTime) value).format(dtf);
            } else if (value instanceof LocalDateTime) {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern(dto.getDatabaseFormat());
                value = dtf.format((LocalDateTime) value);
            }
            return value;
        } catch (Exception e) {
            return "";
        }
    }

    *//**
     * 模板导出
     *
     * @param datas 转换后 data
     * @return 返回Excel
     *//*
    public static ResponseEntity<byte[]> templateExport(List<ExcelData> datas, String fileName, String filePath) {
        if (isExcel2007(filePath)) {
            try {
                //excel模板路径
                Resource resource = new ClassPathResource(filePath);
                //1.创建Excel文档
                // 读取excel模板
                try (Workbook wb = WorkbookFactory.create(resource.getInputStream())) {
                    HttpHeaders headers = new HttpHeaders();
                    headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                    headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName, "utf-8"));
                    return templateData(wb, datas, headers);
                }
            } catch (Exception e) {
                log.error("导出excel出现异常:", e);
                return null;
            }
        } else {
            log.error("验证filePath失败");
            return null;
        }
    }

    *//**
     * 将数据填充进来
     *
     * @param templateWork
     * @param datas
     * @param headers
     *//*
    public static ResponseEntity<byte[]> templateData(Workbook templateWork, List<ExcelData> datas, HttpHeaders headers) throws IOException {
        Sheet templateSheet = templateWork.getSheetAt(0);
        ByteArrayOutputStream baos;
        try (SXSSFWorkbook workbook = new SXSSFWorkbook()) {
            int index = 1;
            for (ExcelData data : datas) {
                //创建Excel表单
                String sheetName = data.getName() + "-" + index;
                SXSSFSheet sheet = workbook.createSheet(sheetName);
                copySheet(workbook, templateSheet, sheet, true);
                templateWriteExcel(workbook, sheet, data);
                index++;
            }
            baos = new ByteArrayOutputStream();
            workbook.write(baos);
            workbook.dispose();
        }
        return new ResponseEntity<>(baos.toByteArray(), headers, HttpStatus.OK);
    }


    *//**
     * 设置头样式或填充数据
     *
     * @param workbook
     * @param sheet
     * @param data
     *//*
    private static void templateWriteExcel(SXSSFWorkbook workbook, SXSSFSheet sheet, ExcelData data) {
        templateWriteRowsToExcel(workbook, sheet, data.getRows());
    }

    *//**
     * 填充数据 设置样式
     *
     * @param workbook
     * @param sheet
     * @param rows
     *//*
    private static void templateWriteRowsToExcel(SXSSFWorkbook workbook, SXSSFSheet sheet, List<List<Object>> rows) {
        for (int i = 0; i < rows.size(); i++) {
            SXSSFRow dataRow = sheet.createRow(i + 2);
            List<Object> cellData = rows.get(i);
            for (int j = 0; j < cellData.size(); j++) {
                //设置数据单元格样式
                CellStyle rowStyle = workbook.createCellStyle();
                rowStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                rowStyle.setBorderBottom(BorderStyle.THIN);
                rowStyle.setBorderLeft(BorderStyle.THIN);
                rowStyle.setBorderRight(BorderStyle.THIN);
                rowStyle.setBorderTop(BorderStyle.THIN);
                rowStyle.setFillForegroundColor(IndexedColors.WHITE.index);
                rowStyle.setAlignment(HorizontalAlignment.CENTER);
                rowStyle.setVerticalAlignment(VerticalAlignment.CENTER);
                rowStyle.setWrapText(true);
                SXSSFCell cell = dataRow.createCell(j);
                cell.setCellStyle(rowStyle);
                if (cellData.get(j) == null) {
                    continue;
                }
                String cellValue;
                if (cellData.get(j) instanceof ExcelCellDTO) {
                    ExcelCellDTO excelCellDTO = (ExcelCellDTO) cellData.get(j);
                    cellValue = excelCellDTO.getValue();
                    rowStyle.setFillForegroundColor(excelCellDTO.getColor().index);
                    rowStyle.setAlignment(excelCellDTO.getHorizontalAlignment());
                    rowStyle.setVerticalAlignment(excelCellDTO.getVerticalAlignment());
                    cell.setCellStyle(rowStyle);
                } else {
                    cellValue = cellData.get(j).toString();
                }
                cell.setCellValue(cellValue);
            }
        }
    }

    *//**
     * Sheet复制
     *
     * @param fromSheet
     * @param toSheet
     * @param copyValueFlag
     *//*
    public static void copySheet(Workbook wb, Sheet fromSheet, Sheet toSheet, boolean copyValueFlag) {
        //合并区域处理
        mergerRegion(fromSheet, toSheet);
        int index = 0;
        for (Iterator<Row> rowIt = fromSheet.rowIterator(); rowIt.hasNext(); ) {
            Row tmpRow = rowIt.next();
            Row newRow = toSheet.createRow(tmpRow.getRowNum());
            CellStyle style = tmpRow.getRowStyle();
            if (style != null) {
                newRow.setRowStyle(tmpRow.getRowStyle());
            }
            newRow.setHeight(tmpRow.getHeight());
            //针对第一行设置行宽
            if (index == 0) {
                int first = tmpRow.getFirstCellNum();
                int last = tmpRow.getLastCellNum();
                for (int i = first; i < last; i++) {
                    int w = fromSheet.getColumnWidth(i);
                    toSheet.setColumnWidth(i, w + 1);
                }
                toSheet.setDefaultColumnWidth(fromSheet.getDefaultColumnWidth());
            }
            //行复制
            copyRow(wb, tmpRow, newRow, copyValueFlag);
            index++;
        }
    }

    *//**
     * 行复制功能
     *
     * @param fromRow
     * @param toRow
     *//*
    static void copyRow(Workbook wb, Row fromRow, Row toRow, boolean copyValueFlag) {
        for (Iterator<Cell> cellIt = fromRow.cellIterator(); cellIt.hasNext(); ) {
            Cell tmpCell = cellIt.next();
            Cell newCell = toRow.createCell(tmpCell.getColumnIndex());
            copyCell(wb, tmpCell, newCell, copyValueFlag);
        }
    }

    *//**
     * 复制原有sheet的合并单元格到新创建的sheet
     *
     * @param toSheet   新创建sheet
     * @param fromSheet 原有的sheet
     *//*
    static void mergerRegion(Sheet fromSheet, Sheet toSheet) {
        int sheetMergerCount = fromSheet.getNumMergedRegions();
        for (int i = 0; i < sheetMergerCount; i++) {
            CellRangeAddress cra = fromSheet.getMergedRegion(i);
            toSheet.addMergedRegion(cra);
        }
    }

    *//**
     * 复制单元格
     *
     * @param sourceCell
     * @param targetCell
     * @param copyValueFlag true则连同cell的内容一起复制
     *//*
    public static void copyCell(Workbook wb, Cell sourceCell, Cell targetCell, boolean copyValueFlag) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.cloneStyleFrom(sourceCell.getCellStyle());
        // 样式
        targetCell.setCellStyle(cellStyle);
        CellType sourceCellType = sourceCell.getCellTypeEnum();
        targetCell.setCellType(sourceCellType);
        // 数据处理
        if (copyValueFlag) {
            switch (sourceCellType) {
                case STRING:
                    targetCell.setCellValue(sourceCell.getRichStringCellValue());
                    break;
                case BOOLEAN:
                    targetCell.setCellValue(sourceCell.getBooleanCellValue());
                    break;
                case FORMULA:
                    targetCell.setCellValue(sourceCell.getCellFormula());
                    break;
                case NUMERIC:
                    targetCell.setCellValue(sourceCell.getNumericCellValue());
                    break;
                default:
                    break;
            }
        }

    }

    *//**
     * 数据转换存储
     *
     * @param beans list数据
     * @return ExcelData
     *//*
    public static ExcelData setTemplateExcelData(List<Object> beans, String sheetName) {
        ExcelData excelData = new ExcelData();
        excelData.setName(sheetName);
        List<List<Object>> rows = new ArrayList<>();
        for (Object bean : beans) {
            List<Object> row = getFiledValues(bean);
            rows.add(row);
        }
        excelData.setRows(rows);
        return excelData;
    }

    *//**
     * Excel 导入
     *
     * @param file 文件
     * @param t    实体
     *//*
    public static <T> Map<String, List<T>> importExcel(MultipartFile file, Class<T> t) throws Exception {
        if (file == null) {
            throw new BusinessException("未知错误！");
        }
        if (file.getSize() == 0) {
            throw new BusinessException("文件不能为空！");

        }
        InputStream is = null;
        try {
            Workbook wb = ExcelUtil1.genWorkBook(file);
            return readExcelData(wb, t);
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        throw new BusinessException("导入出错！请检查数据格式！");
    }

    *//**
     * @param wb 工作簿
     * @return
     *//*
    public static <T> Map<String, List<T>> readExcelData(Workbook wb, Class<T> t) throws Exception {
        Map<String, List<T>> map = new HashMap<>();
        //获取sheet页个数
        int numberOfSheets = wb.getNumberOfSheets();
        if (numberOfSheets == 0) {
            throw new BusinessException("文件不能为空！");
        }
        //获取每个Sheet表
        for (int i = 0; i < numberOfSheets; i++) {
            //获取sheetName
            List<T> list = new ArrayList<>();
            String sheetName = wb.getSheetName(i);
            Sheet sheet = wb.getSheetAt(i);
            //获取行
            int physicalNumberOfRows = sheet.getPhysicalNumberOfRows();
            for (int j = 0; j < physicalNumberOfRows; j++) {
                T newInstance = t.newInstance();
                // 成员变量的值
                if (j == 0) {
                    continue;//标题行
                }
                Row row = sheet.getRow(j);
                if (row == null) {
                    continue;//没数据
                }
                //获取单元格
                Field[] fields = newInstance.getClass().getDeclaredFields();
                for (int k = 0; k < row.getPhysicalNumberOfCells(); k++) {
                    Cell cell = row.getCell(k);
                    String fieldName = fields[k].getName();
                    Object object = getCellValue(cell);
                    setFieldValueByName(fieldName, object.toString(), newInstance);

                }
                list.add(newInstance);
            }
            map.put(sheetName, list);
        }
        return map;
    }

    *//**
     * 转换单元格类型
     *
     * @param cell
     * @return
     *//*
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
                    cellValue = cell.getNumericCellValue();
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

    *//**
     * @param fieldName  字段名
     * @param fieldValue 字段值
     * @param o          对象
     * @MethodName : setFieldValueByName
     * @Description : 根据字段名给对象的字段赋值
     *//*
    private static void setFieldValueByName(String fieldName, String fieldValue, Object o) throws Exception {
        Field field = getFieldByName(fieldName, o.getClass());
        if (field != null) {
            field.setAccessible(true);
            //获取字段类型
            Class<?> fieldType = field.getType();
            //根据字段类型给字段赋值
            if (String.class == fieldType) {
                field.set(o, fieldValue);
            } else if ((Integer.TYPE == fieldType)
                    || (Integer.class == fieldType)) {
                field.set(o, Double.valueOf(change(fieldValue)).intValue());
            } else if ((Long.TYPE == fieldType)
                    || (Long.class == fieldType)) {
                field.set(o, Long.valueOf(fieldValue));
            } else if ((Float.TYPE == fieldType)
                    || (Float.class == fieldType)) {
                field.set(o, Float.valueOf(fieldValue));
            } else if ((Short.TYPE == fieldType)
                    || (Short.class == fieldType)) {
                field.set(o, Short.valueOf(fieldValue));
            } else if ((Double.TYPE == fieldType)
                    || (Double.class == fieldType)) {
                field.set(o, Double.valueOf(change(fieldValue)));
            } else if (Character.TYPE == fieldType) {
                if ((fieldValue != null) && (fieldValue.length() > 0)) {
                    field.set(o, Character.valueOf(fieldValue.charAt(0)));
                }
            } else if (Date.class == fieldType) {
                field.set(o, new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(fieldValue));
            } else {
                field.set(o, fieldValue);
            }
        } else {
            throw new BusinessException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
    }

    public static String change(String fieldValue) {
        if (StringUtils.isEmpty(fieldValue)) {
            return "0";
        }
        return fieldValue;
    }

    *//**
     * @param fieldName 字段名
     * @param clazz     包含该字段的类
     * @return 字段
     * @MethodName : getFieldByName
     * @Description : 根据字段名获取字段
     *//*
    private static Field getFieldByName(String fieldName, Class<?> clazz) {
        //拿到本类的所有字段
        Field[] selfFields = clazz.getDeclaredFields();

        //如果本类中存在该字段，则返回
        for (Field field : selfFields) {
            if (field.getName().equals(fieldName)) {
                return field;
            }
        }
        //否则，查看父类中是否存在此字段，如果有则返回
        Class<?> superClazz = clazz.getSuperclass();
        if (superClazz != null && superClazz != Object.class) {
            return getFieldByName(fieldName, superClazz);
        }
        //如果本类和父类都没有，则返回空
        return null;
    }


    *//**
     * @param fieldName 字段名
     * @param o         对象
     * @return 字段值
     * @MethodName : getFieldValueByName
     * @Description : 根据字段名获取字段值
     *//*
    private static Object getFieldValueByName(String fieldName, Object o) throws Exception {

        Object value = null;
        Field field = getFieldByName(fieldName, o.getClass());

        if (field != null) {
            field.setAccessible(true);
            value = field.get(o);
        } else {
            throw new BusinessException(o.getClass().getSimpleName() + "类不存在字段名 " + fieldName);
        }
        return value;
    }*/
}
