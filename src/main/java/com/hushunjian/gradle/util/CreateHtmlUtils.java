package com.hushunjian.gradle.util;
import java.io.FileInputStream;
import java.io.FileOutputStream;
public class CreateHtmlUtils {
	public static void main(String[] args) {
        String filePath = "E:\\test.html";
        String imagePath ="E:\\QQ截图20190314165404.png";
        String disrPath = "E:\\";
        String fileName = "testA";
        MakeHtml(filePath,imagePath,disrPath,fileName);
    }
    /**
     * @Title: MakeHtml 
     * @Description: 创建html
     * @param    filePath 设定模板文件
     * @param    imagePath 需要显示图片的路径
     * @param    disrPath  生成html的存放路径
     * @param    fileName  生成html名字 
     * @return void    返回类型 
     * @throws
     */
    public static void MakeHtml(String filePath,String imagePath,String disrPath,String fileName ){
        try {
            String title = "<image src="+'"'+imagePath+'"'+"/>";
            System.out.print(filePath);
            String templateContent = "";
            FileInputStream fileinputstream = new FileInputStream(filePath);// 读取模板文件
            int lenght = fileinputstream.available();
            byte bytes[] = new byte[lenght];
            fileinputstream.read(bytes);
            fileinputstream.close();
            templateContent = new String(bytes);
            System.out.print(templateContent);
            templateContent = templateContent.replaceAll("###title###", title);
            System.out.print(templateContent);
            
            String fileame = fileName + ".html";
            fileame = disrPath+"/" + fileame;// 生成的html文件保存路径。
            FileOutputStream fileoutputstream = new FileOutputStream(fileame);// 建立文件输出流
            System.out.print("文件输出路径:");
            System.out.print(fileame);
            byte tag_bytes[] = templateContent.getBytes();
            fileoutputstream.write(tag_bytes);
            fileoutputstream.close();
        } catch (Exception e) {
            System.out.print(e.toString());
        }
    }
}
