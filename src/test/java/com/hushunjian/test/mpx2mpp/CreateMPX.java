package com.hushunjian.test.mpx2mpp;

import java.io.File;
import java.util.List;

import net.sf.mpxj.MPXJException;
import net.sf.mpxj.ProjectFile;
import net.sf.mpxj.Resource;
import net.sf.mpxj.ResourceAssignment;
import net.sf.mpxj.Task;
import net.sf.mpxj.mpp.MPPReader;  
public class CreateMPX {
	/** 
     * @param args 
     * @throws MPXJException 
     */  
    public static void main(String[] args) throws MPXJException {  
        // TODO Auto-generated method stub  
        File file = new File("D:\\Users\\hushunjian\\Desktop\\新建模板1234376900763049741.mpp");  
        MPPReader mppRead = new MPPReader();  
        ProjectFile pf = mppRead.read(file);  
        List tasks = pf.getTasks();  
        CreateMPX mt = new CreateMPX();  
        System.out.println("总任务数: " + tasks.size());  
        System.out.println("ID|任务名|资源|开始时间|结束时间|完成百分比");  
        for (int i = 1; i < tasks.size(); i++) {  
            System.out.println(((Task) tasks.get(i)).getUniqueID() + "|"  
                    + ((Task) tasks.get(i)).getName() + "|"  
                    + mt.getResource((Task) tasks.get(i)) + "|"  
                    + ((Task) tasks.get(i)).getStart() + "|"  
                    + ((Task) tasks.get(i)).getFinish() + "|"  
                    + ((Task) tasks.get(i)).getPercentageComplete());  
        }  
    }  
  
    public String getResource(Task task) {  
        StringBuffer buf = new StringBuffer();  
        List assignments = task.getResourceAssignments();  
        for (int i = 0; i < assignments.size(); i++) {  
            ResourceAssignment assignment = (ResourceAssignment) assignments.get(i);  
            Resource resource = assignment.getResource();  
            if (resource != null) {  
                buf.append(resource.getName()).append(" ");  
            }  
        }  
        return buf.toString();  
    }  
}
