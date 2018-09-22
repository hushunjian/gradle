package com.hushunjian.test.mpx2mpp;

import java.io.IOException;
import java.util.Locale;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpx.MPXWriter;
import net.sf.mpxj.reader.UniversalProjectReader;

public class Mpp2Mpx {
	public static void main(String[] args) throws MPXJException, IOException {
		/*UniversalProjectReader reader = new UniversalProjectReader();
		ProjectFile mpx = reader.read("D:\\Users\\hushunjian\\Desktop\\aa.xml");
		MPXWriter mpxWriter = new MPXWriter();
		Locale locale = Locale.CHINESE;
		mpxWriter.setLocale(locale);
		mpxWriter.write(mpx, "D:\\Users\\hushunjian\\Desktop\\aa.mpx");*/
		createMPX("D:\\Users\\hushunjian\\Desktop\\aa.mpx");
	}
	
	public static void createMPX(String fileName) throws IOException{
		ProjectFile project = new ProjectFile();
		Task task1 = project.addTask();
		task1.setName("任务1");
		task1.setUniqueID(1); 
		task1.setID(1); 
		MPXWriter writer = new MPXWriter(); 
		//设置中文
		writer.setLocale(Locale.CHINESE); 
		writer.write(project,fileName); 
	}
	
}
