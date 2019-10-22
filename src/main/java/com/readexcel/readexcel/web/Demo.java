package com.readexcel.readexcel.web;

import com.readexcel.readexcel.service.AsyncService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.concurrent.CountDownLatch;

@Controller
public class Demo {
    @Autowired
    ReadExcel readExcel;

    @GetMapping("/index")
    public String index(){
        return "index";
    }
    @RequestMapping("/downloadFile")
    @ResponseBody
    private String downloadFile(HttpServletResponse response, HttpServletRequest request){

        String jkh = request.getParameter("jkh");
        String jkm = request.getParameter("jkm");
        try {
            readExcel.readExcel(jkh, jkm, "ESB对外接口文档1.2.3.xlsx", "D:\\\\jar");
        }catch (Exception e){
            return "输入错误";
        }
        String downloadFilePath = "D:\\\\jar\\\\EsbInterface"+jkh+".java";//被下载的文件在服务器中的路径,
        String fileName = "EsbInterface"+jkh+".java";//被下载文件的名称

        File file = new File(downloadFilePath);
       //File file1 = new File("D:\\\\java");
        if(file.exists()){ //判断文件父目录是否存在
            response.setContentType("application/force-download");
            response.setHeader("Content-Length", file.length()+"");
            response.setHeader("Content-Disposition", "attachment;fileName=" + fileName);
            //out.clear();
           // out = pageContext.pushBody();

            byte[] buffer = new byte[1024];
            FileInputStream fis = null; //文件输入流
            BufferedInputStream bis = null;
            FileOutputStream fos = null;
            BufferedOutputStream bos = null;
            OutputStream os = null; //输出流
            try {
                os = response.getOutputStream();
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
               // fos = new FileOutputStream(file1);
               // bos = new BufferedOutputStream(fos);
                //int i = bis.read(buffer);
                int i ;
                while((i = bis.read(buffer)) != -1){
                    os.write(buffer,0,i);
                    //i = bis.read(buffer);
                   // for(int j=0;j<buffer.length;j++)
                       // System.out.print(""+(char)buffer[j]);
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("file download:  " + fileName);
            try {
                bis.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //若这个return不是null，则必须在方法上加@ResponseBody这个注解，让他返回的 "下载失败" 为json数据，不占getOutputStream()的流
        // 返回值要注意（不是null，也不在方法上加@ResponseBody这个注解），要不然就出现下面这句错误！
        //java+getOutputStream() has already been called for this response
        return "下载失败";
    }

    @RequestMapping("/upload")
    public String go(){
        return "upload";
    }

    @ResponseBody
    @RequestMapping(value = "/uploadFile" , method = RequestMethod.POST)
    public String getFile( HttpServletRequest request,@RequestParam("file") MultipartFile mfile) {
        String prixff = mfile.getOriginalFilename();

        try {

            File file = new File("D:\\\\jar\\\\heihei.mp4");
            if (!file.exists()) {
                try {
                    file.createNewFile();
                } catch (IOException e) {
                   e.printStackTrace();
                }
            }
            FileOutputStream fos = null;
            InputStream fis = null;
            try {
                fos = new FileOutputStream(file);
                byte[] buff = new byte[1024];
                int length = 0;
                fis = mfile.getInputStream();
                while ((length = fis.read(buff)) > 0) {
                    fos.write(buff, 0, length);
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                       e.printStackTrace();
                    }
                }
                if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {
                       e.printStackTrace();
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return "成功";
    }


    /**
     * 测试线程池 ThreadPoolTaskExecutor
     */
    //private static ChildThreadExceptionHandler exceptionHandler=new ChildThreadExceptionHandler();;
    @Autowired
    TaskExecutor taskExecutor;
    @RequestMapping("doit")
    @ResponseBody
    public String myThread(){
        ArrayList<String> strings = new ArrayList<>();
        ArrayList<Object> objects = new ArrayList<>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        strings.add("4");
        System.out.println("开始");
        //让主线程等待
        final CountDownLatch latch = new CountDownLatch(strings.size());
        for (String str : strings){
            taskExecutor.execute(new Runnable() {
                @Override
                public void run() {
                   // Thread.currentThread().setUncaughtExceptionHandler(exceptionHandler);
                    System.out.println(Thread.currentThread().getName()+"run---");
                    say(Thread.currentThread().getName(),str);
                    objects.add(str);
                    //int i = 1/0;
                    try {
                        latch.countDown();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      /*  while (objects.size()!=strings.size()){
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }*/
        System.out.println("结束");
        return "完成";
    }

    /*public static class ChildThreadExceptionHandler implements Thread.UncaughtExceptionHandler {
        public void uncaughtException(Thread t, Throwable e) {
            System.out.println(String.format("handle exception in child thread. %s", e));
        }
    }*/

    public void say(String string,String str){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("hi--"+string+" : "+str);
    }



    /**
     * 测试线程池 使用Async
     */
    @Autowired
    AsyncService asyncService;
    @ResponseBody
    @GetMapping("/async")
    public String myAsyncTest(){
        System.out.println("开始");
        for(int i=0;i<20;i++){
            asyncService.testNoRespNoParamAsync();
        }

        System.out.println("结束");
        return "完成";

    }



}
