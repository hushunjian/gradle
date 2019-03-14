package com.hushunjian.gradle.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.xhtmlrenderer.pdf.ITextFontResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.lowagie.text.DocumentException;
/**
 * 导出PDF文件转换工具
 */
public class PDFUtil{
	

    /**
     * 生成 PDF 文件
     * @param out 输出流
     * @param html HTML字符串
     * @throws IOException IO异常
     * @throws DocumentException Document异常
     */
    public static void createPDF(OutputStream out, String html) throws IOException, DocumentException {
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(html);
        // 解决中文支持问题
        ITextFontResolver fontResolver = renderer.getFontResolver();
        fontResolver.addFont("pdf/font/fangsong.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        fontResolver.addFont("pdf/font/PingFangSC.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
        renderer.layout();
        renderer.createPDF(out);
    }
    
    public static void main(String[] args) throws Exception {
        // 模板文件路径
        String modelPath = "E:\\test.pdf";
        // 生成的文件路径
        String resultFielPath = "E:\\testA.pdf";
        // 书签名[域名]
        String fieldName = "img";
		// 图片路径
		String imagePath = "E:\\QQ截图20190314114944.png";
        // 读取模板文件
        InputStream input = new FileInputStream(new File(modelPath));
        PdfReader reader = new PdfReader(input);
        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(resultFielPath));
        // 提取pdf中的表单
        AcroFields form = stamper.getAcroFields();
       
        // 此种方式写入文本数据
        // k为模板域名称,v为数据,每个域对应一个k,根据k传输数据.
        form.setField("date", "中文");
        /**
         * 各种form.setField().................
         * 
         * */
        // 字体设置
        form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
        
        // 此种方式写入图片数据.
        // 通过域名获取所在页和坐标，左下角为起点
        int pageNo = form.getFieldPositions(fieldName).get(0).page;
        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
        float x = signRect.getLeft();
        float y = signRect.getBottom();
 
        // 读取图片
        Image image = Image.getInstance(imagePath);
        // 获取操作的页面
        PdfContentByte under = stamper.getOverContent(pageNo);
        // 根据域的大小缩放图片
        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
        // 添加图片
        image.setAbsolutePosition(x, y);
        under.addImage(image);
        stamper.close();
        reader.close();
    }

}