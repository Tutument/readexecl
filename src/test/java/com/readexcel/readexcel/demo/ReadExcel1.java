package com.readexcel.readexcel.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;


public class ReadExcel1 {

    public static void main(String[] args) {
        /*String arg0 = args[0];
        String arg1 = args[1];*/

       // readExcel(arg0,arg1);

        readExcel(args[0],args[1]);
    }

    public static void readExcel(String jiekouhao,String jiekouming,String ...request){
        String excelPath = "D:\\jar\\ESB对外接口文档1.2.1.xlsx";

        String className = "RequestBody"+jiekouhao;
        for(String str : request){
            className = str;
        }
        File excel = new File(excelPath);
        if (excel.isFile() && excel.exists()){
            String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
           // System.out.println(split.length);
            Workbook wb;
            //根据文件后缀（xls/xlsx）进行判断
            Sheet sheet = null;
            try{
            if ( "xls".equals(split[3])){
                FileInputStream fis = new FileInputStream(excel);   //文件流对象
                wb = new HSSFWorkbook(fis);
                 sheet = wb.getSheetAt(0);
            }else if ("xlsx".equals(split[3])){
                wb = new XSSFWorkbook(excel);
               // sheet = wb.getSheetAt(0);
                sheet = wb.getSheet(jiekouming);
            }else {
                System.out.println("文件类型错误!");
                return;
            }}catch (Exception e){
                e.printStackTrace();
            }

            //开始解析
            //读取sheet 0

            int firstRowIndex = sheet.getFirstRowNum()+3;   //前三行是列名，所以不读
            int lastRowIndex = sheet.getLastRowNum();
           // System.out.println("firstRowIndex: "+firstRowIndex);
            //System.out.println("lastRowIndex: "+lastRowIndex);
            //控制RequestBody
            boolean b1 = false;
            //控制RequestBody中的list
            boolean b2 = false;
            //控制ResponseBody
            boolean b3 = false;
            //控制ResponseBody的list
            boolean b4 = false;
            //控制RequestBody中的before,after
            boolean baf = false;

            //装载RequestBody
            StringBuffer sb1 = new StringBuffer();
            //装载RequestBody的list
            StringBuffer sb2 = new StringBuffer();
            //装载ResponseBody
            StringBuffer sb3 = new StringBuffer();
            //装载ResponseBody的list
            StringBuffer sb4 = new StringBuffer();
            //装载RequestBody的get，set
            StringBuffer sb5 = new StringBuffer();
            //装载RequestBody的list的get，set
            StringBuffer sb6 = new StringBuffer();
            //装载ResponseBody的get，set
            StringBuffer sb7 = new StringBuffer();
            //装载ResponseBody的list的get，set
            StringBuffer sb8 = new StringBuffer();
            //装载befor，after
            StringBuffer sbaf = new StringBuffer();
            //装载befor，after的get，set
            StringBuffer sbaf1 = new StringBuffer();

            String listName = "";
            String listNameUpper = "";
            for(int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
               // System.out.println("rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();


                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列

                        Cell cell = row.getCell(cIndex);

                        if(cIndex == 2){
                            if(cell.toString().equals("messageRequestBody")){
                                b1=true;
                            }
                            if(cell.toString().equals("messageResponseHead")){
                                b1=false;
                            }
                            if("messageResponseBody".equals(cell.toString())){
                                b3 = true;
                            }

                        }
                        if(cIndex==3){
                            if("secAccList".equals(cell.toString())||"bankList".equals(cell.toString())||"secList".equals(cell.toString())){
                                b1=false;
                                b2 = true;
                                listName = cell.toString();
                                int i = listName.codePointAt(0);
                                char j = (char)i;
                                char c = Character.toUpperCase(j);
                                listNameUpper= listName.replaceFirst(j+"", c+"");
                            }
                            if("before".equals(cell.toString())){
                                b1 = false;
                                baf= true;
                            }
                            if("after".equals(cell.toString())){
                                baf= false;
                            }
                            if("N/A".equals(cell.toString())){
                                b2 = false;
                            }
                            if("itemList".equals(cell.toString())||"return".equals(cell.toString())){
                                b4 = true;
                                b3= false;
                            }

                        }

                      /*  if(cIndex == 1){
                            if("".equals(cell.toString())){
                                b4 = false;
                                b1 = false;
                                b2 = false;
                                b3 = false;
                            }
                        }
                        if(cIndex == 6){
                            if("".equals(cell.toString())) {
                                b4 = false;
                                b2 = false;
                            }
                        }*/

                        //控制RequestBody
                        if (b1) {
                            if(cIndex==6) {
                                String str = cell.toString();
                                if (str.length() > 0) {
                                    sb1.append("public String " + cell.toString() + ";");
                                    int i = str.codePointAt(0);
                                    char j = (char) i;
                                    char c = Character.toUpperCase(j);
                                    String s1 = str.replaceFirst(j + "", c + "");
                                    sb5.append("public String get" + s1 + "() {\n" +
                                            "\t\t\treturn " + cell.toString() + ";\n" +
                                            "\t\t}\n" +
                                            "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                            "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                            "\t\t}\r\n");
                                }
                            }
                            if(cIndex==7) {
                                if(cell.toString().length()>0) {
                                    sb1.append("//" + cell.toString());
                                    sb1.append("\r\n");
                                }
                            }

                        }
                        //控制RequestBody中的list
                        if(b2){
                            if(cIndex==6){
                                String str = cell.toString();
                                if(str.length()>0){
                                    sb2.append("public String "+cell.toString()+";");
                                    int i = str.codePointAt(0);
                                    char j = (char)i;
                                    char c = Character.toUpperCase(j);
                                    String s1 = str.replaceFirst(j+"", c+"");
                                    sb6.append("public String get"+s1+"() {\n" +
                                            "\t\t\treturn "+cell.toString()+";\n" +
                                            "\t\t}\n" +
                                            "\t\tpublic void set"+s1+"(String "+cell.toString()+") {\n" +
                                            "\t\t\tthis."+cell.toString()+" = "+cell.toString()+";\n" +
                                            "\t\t}\r\n");
                                }
                            }
                            if(cIndex==7) {
                                if(cell.toString().length()>0) {
                                    sb2.append("//" + cell.toString());
                                    sb2.append("\r\n");
                                }
                            }
                        }
                        //控制RequestBody中的before,after
                        if(baf){
                            if(cIndex==6){
                                String str = cell.toString();
                                if(str.length()>0){
                                    sbaf.append("public String "+cell.toString()+";");
                                    int i = str.codePointAt(0);
                                    char j = (char)i;
                                    char c = Character.toUpperCase(j);
                                    String s1 = str.replaceFirst(j+"", c+"");
                                    sbaf1.append("public String get"+s1+"() {\n" +
                                            "\t\t\treturn "+cell.toString()+";\n" +
                                            "\t\t}\n" +
                                            "\t\tpublic void set"+s1+"(String "+cell.toString()+") {\n" +
                                            "\t\t\tthis."+cell.toString()+" = "+cell.toString()+";\n" +
                                            "\t\t}\r\n");
                                }
                            }
                            if(cIndex==7) {
                                if(cell!=null&&cell.toString().length()>0) {
                                    sbaf.append("//" + cell.toString());
                                    sbaf.append("\r\n");
                                }
                            }
                        }
                        //控制ResponseBody
                         if(b3){
                             if(cIndex==6){
                                 String str = cell.toString();
                                 if(str.length()>0){
                                     sb3.append("public String "+cell.toString()+";");
                                     int i = str.codePointAt(0);
                                     char j = (char)i;
                                     char c = Character.toUpperCase(j);
                                     String s1 = str.replaceFirst(j+"", c+"");
                                     sb7.append("public String get"+s1+"() {\n" +
                                             "\t\t\treturn "+cell.toString()+";\n" +
                                             "\t\t}\n" +
                                             "\t\tpublic void set"+s1+"(String "+cell.toString()+") {\n" +
                                             "\t\t\tthis."+cell.toString()+" = "+cell.toString()+";\n" +
                                             "\t\t}\r\n");
                                 }
                             }
                             if(cIndex==7) {
                                 if(cell!=null&&cell.toString().length()>0) {
                                     sb3.append("//" + cell.toString());
                                     sb3.append("\r\n");
                                 }
                             }
                         }
                        //控制ResponseBody的list
                        if(b4){
                            if(cIndex==6){
                                String str = cell.toString();
                                if(str.length()>0){
                                    sb4.append("public String "+cell.toString()+";");
                                    int i = str.codePointAt(0);
                                    char j = (char)i;
                                    char c = Character.toUpperCase(j);
                                    String s1 = str.replaceFirst(j+"", c+"");
                                    sb8.append("public String get"+s1+"() {\n" +
                                            "\t\t\treturn "+cell.toString()+";\n" +
                                            "\t\t}\n" +
                                            "\t\tpublic void set"+s1+"(String "+cell.toString()+") {\n" +
                                            "\t\t\tthis."+cell.toString()+" = "+cell.toString()+";\n" +
                                            "\t\t}\r\n");
                                }

                            }
                            if(cIndex==7) {
                                if(cell!=null&&cell.toString().length()>0) {
                                    sb4.append("//" + cell.toString());
                                    sb4.append("\r\n");
                                }
                            }
                        }

                    }
                }
            }
            /*System.out.println(sb1.toString());
            System.out.println("===========");
            System.out.println(sb2.toString());
            System.out.println("===========");
            System.out.println(sb3.toString());
            System.out.println("===========");
            System.out.println(sb4.toString());*/

            //拼接类
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("public class EsbInterface"+jiekouhao+" extends EsbSupport {\n" +
                    "\n" +
                    "\tpublic EsbInterface"+jiekouhao+"(String emp, String optDept, String macAdd,\n" +
                    "                              String reqSN) {\n" +
                    "\t\tsuper(emp, optDept, macAdd, reqSN);\n" +
                    "\t}\n" +
                    "\n" +
                    "\t@Override\n" +
                    "\tprotected void initRequestHead(MessageRequestHead requestHead) {\n" +
                    "\t\trequestHead.setInterfaceCode("+jiekouhao+");\n" +
                    "\t}\n" +
                    "\n" +
                    "\t@Override\n" +
                    "\tprotected MessageRequestBody initRequestBody() {\n" +
                    "\t\treturn new RequestBody();\n" +
                    "\t}\n" +
                    "\n" +
                    "\t@Override\n" +
                    "\tprotected ResponseVO initResponseVO() {\n" +
                    "\t\treturn new Response();\n" +
                    "\t}\n");
            stringBuffer.append("//请求体\r\n");
            if(sb1.length()!=0){
                stringBuffer.append("public static class RequestBody extends MessageRequestBody{\r\n");
                stringBuffer.append(sb1.toString());
                stringBuffer.append(sb5.toString());
                if(sb2.length()!=0){
                    stringBuffer.append("public List<"+className+"> "+listName+";\r\n");
                    stringBuffer.append("public List<"+className+"> get"+listNameUpper+"() {\n" +
                            "\t\t\treturn "+listName+";\n" +
                            "\t\t}\n" +
                            "\t\tpublic void set"+listNameUpper+"(List<"+className+"> "+listName+") {\n" +
                            "\t\t\tthis."+listName+" = "+listName+";\n" +
                            "\t\t}\r\n");
                }
                if(sbaf.length()!=0){
                    stringBuffer.append("public RequestBody"+jiekouhao+" before = new RequestBody"+jiekouhao+"();\n" +
                            "        public RequestBody"+jiekouhao+" after  = new RequestBody"+jiekouhao+"();\r\n");
                    stringBuffer.append("        public RequestBody"+jiekouhao+" getBefore() {\n" +
                            "            return before;\n" +
                            "        }\n" +
                            "\n" +
                            "        public void setBefore(RequestBody"+jiekouhao+" before) {\n" +
                            "            this.before = before;\n" +
                            "        }\n" +
                            "\n" +
                            "        public RequestBody"+jiekouhao+" getAfter() {\n" +
                            "            return after;\n" +
                            "        }\n" +
                            "\n" +
                            "        public void setAfter(RequestBody"+jiekouhao+" after) {\n" +
                            "            this.after = after;\n" +
                            "        }");
                }
                stringBuffer.append("}\r\n");
            }
            stringBuffer.append("public static class Response extends ResponseVO {\r\n");
            stringBuffer.append("@Override\r\n");
            stringBuffer.append("protected MessageResponseHead initResponseHead() {\n" +
                    "\t\t\treturn null;\n" +
                    "\t\t}\r\n");
            stringBuffer.append("@Override\n" +
                    "\t\tprotected MessageResponseBody initResponseBody() {\n" +
                    "\t\t\treturn new ResponseBody();\n" +
                    "\t\t}\r\n");
            stringBuffer.append("}\r\n");

            stringBuffer.append("public static class ResponseBody extends MessageResponseBody {\r\n");
            if(sb4.length()!=0){
                stringBuffer.append("public List<ResponseBody"+jiekouhao+"> data = new ArrayList<ResponseBody"+jiekouhao+">();\r\n");
                stringBuffer.append("public List<ResponseBody"+jiekouhao+"> getData() {\n" +
                        "\t\t\treturn data;\n" +
                        "\t\t}\r\n");
                stringBuffer.append("public void setData(List<ResponseBody"+jiekouhao+"> data) {\n" +
                        "\t\t\tthis.data = data;\n" +
                        "\t\t}\r\n");
                stringBuffer.append("}\r\n");
            }else {
                stringBuffer.append(sb3.toString());
                stringBuffer.append(sb7.toString());
                stringBuffer.append("}\r\n");
            }

            if(sb4.length()!=0){
                stringBuffer.append("public static class ResponseBody"+jiekouhao+" implements Serializable {\r\n");
                stringBuffer.append(sb4.toString());
                stringBuffer.append(sb8.toString());
                stringBuffer.append("}\r\n");
            }
            if(sb2.length()!=0){
                stringBuffer.append("public static class "+className+" implements Serializable{\r\n");
                stringBuffer.append(sb2.toString());
                stringBuffer.append(sb6.toString());
                stringBuffer.append("}\r\n");
            }
            if(sbaf.length()!=0){
                stringBuffer.append("public static class RequestBody"+jiekouhao+" implements java.io.Serializable{");
                stringBuffer.append(sbaf.toString());
                stringBuffer.append(sbaf1.toString());
                stringBuffer.append("}\r\n");
            }
            stringBuffer.append("}");
            //System.out.println(stringBuffer.toString());
            String content = stringBuffer.toString();
            try {
                File file = new File("D:\\jar\\EsbInterface" + jiekouhao + ".java");
                if (!file.exists()) {
                    file.createNewFile();
                }
                FileWriter fw = new FileWriter(file.getAbsoluteFile());
                BufferedWriter bw = new BufferedWriter(fw);
                bw.write(content);
                bw.close();

                System.out.println("Done");
            }catch (Exception e){
                e.printStackTrace();
            }

        }else {
            System.out.println("找不到指定的文件");
        }

    }
}
