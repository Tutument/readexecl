package com.readexcel.readexcel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

import javax.validation.constraints.Size;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

/**
 * 生成一些基础类
 */

public class Produce {
    public static final String name = "";

    @Autowired
    static TaskExecutor taskExecutor;

    public static final HashMap<String,String> map= new HashMap();

    public static void main(String[] args) {
        String strKey = "String,Integer,Long";
        String strVal = "id,status,money";
        setMap(strKey,strVal,map);

        final CountDownLatch latch = new CountDownLatch(2);

        taskExecutor.execute(()->{
            String pojo = getPojo(map);
            getFile(pojo);
            System.out.println(pojo);
            try {
                latch.countDown();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        taskExecutor.execute(()->{
            String service = getService();
            getFile(service);
            System.out.println(service);
            try {
                latch.countDown();
            }catch (Exception e){
                e.printStackTrace();
            }
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    public static void setMap(String strKey,String strVal,HashMap<String ,String > map){
        String[] splitName = strKey.split(",");
        String[] splitIndex = strVal.split(",");
        for(int i=0; i<splitName.length;i++){
            map.put(splitName[i],splitIndex[i]);
        }
    }
    public static String getPojo(HashMap<String,String> map){
        StringBuffer buffer = new StringBuffer();
        StringBuffer bufferGS = new StringBuffer();
        map.forEach((key,val)->{
            buffer.append("\t\tprivate ").append(key).append(" ").append(val).append(";\r\n");
            String sub = val.substring(0, 1);
            bufferGS.append("\t\tpublic void set").append(val.replaceFirst(sub,sub.toUpperCase()))
                    .append("(").append(key).append(" ").append(val).append(") {\r\n")
                    .append("\t\t\tthis.").append(val).append(" = ").append(val).append(";\r\n")
                    .append("\t\t}\r\n");
            bufferGS.append("\t\tpublic ").append(key).append(" get").append(val.replaceFirst(sub,sub.toUpperCase()))
                    .append("() {\r\n").append("\t\t\treturn ").append(val).append(";\r\n").append("\t\t}\r\n");
        });
        return buffer.toString()+bufferGS.toString();
    }

    public static String getService(){
        StringBuffer buffer = new StringBuffer();
        buffer.append("asdasdasdasdasdasd");
        return buffer.toString();
    }

    public static void getFile(String str){
        String path =  "D:\\\\jar" ;
        String content = str;
        //写入文件
        BufferedWriter bw = null;
        try {
            File file = null;
            file = new File(path+"\\\\"+"Esb.java");
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
            //System.out.println("Done");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                bw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
