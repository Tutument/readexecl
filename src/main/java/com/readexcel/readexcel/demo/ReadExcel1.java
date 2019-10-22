package com.readexcel.readexcel.demo;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

public class ReadExcel1 {

    public static void main(String[] args) {
        /*String arg0 = args[0];
        String arg1 = args[1];*/
        //查询沪港通(资金账号)相关资产信息
        //readExcel("320007","查询沪港通(资金账号)相关资产信息","查询沪港通(资金账号)相关资产信息.xlsx","D:\\\\jar");
       readExcel("FW_004","海通证券条件单服务开通协议","柜台无纸化签署接口文档v2.2.7.xlsx","D:\\\\jar");
       //readExcel("130001","证券账户查询","ESB对外接口文档1.2.4.xlsx","D:\\\\jar");
       //readExcel(args[0],args[1],args[2],args[3]);
    }

    public static void readExcel(String jiekouhao,String jiekouming,String xlsx,String path,String ...request){
       // String excelPath = "D:\\jar\\柜台无纸化签署接口文档v2.2.7.xlsx";
        String excelPath = path+"\\\\"+xlsx;
        String[] splits = excelPath.split("\\\\");
        boolean esb = splits[splits.length - 1].startsWith("ESB");
        //esb = true;
        //装载拼接类
        StringBuffer stringBuffer = new StringBuffer();

        String className = "RequestBody"+jiekouhao;
        for(String str : request){
            className = str;
        }
        File excel = new File(excelPath);
        if(esb){
            if (excel.isFile() && excel.exists()) {
                String[] split = excel.getName().split("\\.");  //.是特殊字符，需要转义！！！！！
                Workbook wb;

                Sheet sheet = null;
                //根据文件后缀（xls/xlsx）进行判断,现在用的都是xlsx，故不用判断
                /*try {
                    if ("xls".equals(split[3])) {
                        FileInputStream fis = new FileInputStream(excel);   //文件流对象
                        wb = new HSSFWorkbook(fis);
                        sheet = wb.getSheetAt(0);
                    } else if ("xlsx".equals(split[3])) {
                        //FileInputStream fis = new FileInputStream(excel);
                        //XSSFWorkbook可以传File类型
                        wb = new XSSFWorkbook(excel);
                        // sheet = wb.getSheetAt(0);
                        sheet = wb.getSheet(jiekouming);
                    } else {
                        System.out.println("文件类型错误!");
                        return;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }*/

                //XSSFWorkbook可以传File类型
                try {
                    wb = new XSSFWorkbook(excel);
                    sheet = wb.getSheet(jiekouming);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (InvalidFormatException e) {
                    e.printStackTrace();
                }
                // sheet = wb.getSheetAt(0);

                //开始解析
                //读取sheet
                int firstRowIndex = sheet.getFirstRowNum() + 3;   //前三行是列名，所以不读
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
                for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                    // System.out.println("rIndex: " + rIndex);
                    Row row = sheet.getRow(rIndex);
                    if (row != null) {
                        int firstCellIndex = row.getFirstCellNum();
                        int lastCellIndex = row.getLastCellNum();


                        for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列

                            Cell cell = row.getCell(cIndex);
                            if(cell!=null){
                            if (cIndex == 2) {
                                if (cell.toString().equals("messageRequestBody")) {
                                    b1 = true;
                                }
                                if (cell.toString().equals("messageResponseHead")) {
                                    b1 = false;
                                }
                                if ("messageResponseBody".equals(cell.toString())) {
                                    b3 = true;
                                }

                            }
                            if (cIndex == 3) {
                                //request 的 list判断
                                if ("secAccList".equals(cell.toString()) || "bankList".equals(cell.toString()) || "secList".equals(cell.toString()) ) {
                                    b1 = false;
                                    b2 = true;
                                    listName = cell.toString();
                                    int i = listName.codePointAt(0);
                                    char j = (char) i;
                                    char c = Character.toUpperCase(j);
                                    listNameUpper = listName.replaceFirst(j + "", c + "");
                                }
                                if ("before".equals(cell.toString())) {
                                    b1 = false;
                                    baf = true;
                                }
                                if ("after".equals(cell.toString())) {
                                    baf = false;
                                }
                                if ("N/A".equals(cell.toString())) {
                                    b2 = false;
                                }
                                //response 的 list判断
                                if ("itemList".equals(cell.toString()) || "return".equals(cell.toString())|| "taxPayerItems".equals(cell.toString())|| "holderAccList".equals(cell.toString())) {
                                    b4 = true;
                                    b3 = false;
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
                                if (cIndex == 6) {
                                    String str = cell.toString();
                                    if (str.length() > 0) {
                                        sb1.append("\t\tpublic String " + cell.toString() + ";");
                                        int i = str.codePointAt(0);
                                        char j = (char) i;
                                        char c = Character.toUpperCase(j);
                                        String s1 = str.replaceFirst(j + "", c + "");
                                        sb5.append("\t\tpublic String get" + s1 + "() {\n" +
                                                "\t\t\treturn " + cell.toString() + ";\n" +
                                                "\t\t}\n" +
                                                "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                                "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                                "\t\t}\r\n");
                                    }
                                }
                                if (cIndex == 7) {
                                    if (cell.toString().length() > 0) {
                                        sb1.append("//" + cell.toString());
                                        sb1.append("\r\n");
                                    }
                                }

                            }
                            //控制RequestBody中的list
                            if (b2) {
                                if (cIndex == 6) {
                                    String str = cell.toString();
                                    if (str.length() > 0) {
                                        sb2.append("\t\tpublic String " + cell.toString() + ";");
                                        int i = str.codePointAt(0);
                                        char j = (char) i;
                                        char c = Character.toUpperCase(j);
                                        String s1 = str.replaceFirst(j + "", c + "");
                                        sb6.append("\t\tpublic String get" + s1 + "() {\n" +
                                                "\t\t\treturn " + cell.toString() + ";\n" +
                                                "\t\t}\n" +
                                                "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                                "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                                "\t\t}\r\n");
                                    }
                                }
                                if (cIndex == 7) {
                                    if (cell.toString().length() > 0) {
                                        sb2.append("//" + cell.toString());
                                        sb2.append("\r\n");
                                    }
                                }
                            }
                            //控制RequestBody中的before,after
                            if (baf) {
                                if (cIndex == 6) {
                                    String str = cell.toString();
                                    if (str.length() > 0) {
                                        sbaf.append("\t\tpublic String " + cell.toString() + ";");
                                        int i = str.codePointAt(0);
                                        char j = (char) i;
                                        char c = Character.toUpperCase(j);
                                        String s1 = str.replaceFirst(j + "", c + "");
                                        sbaf1.append("\t\tpublic String get" + s1 + "() {\n" +
                                                "\t\t\treturn " + cell.toString() + ";\n" +
                                                "\t\t}\n" +
                                                "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                                "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                                "\t\t}\r\n");
                                    }
                                }
                                if (cIndex == 7) {
                                    if (cell != null && cell.toString().length() > 0) {
                                        sbaf.append("//" + cell.toString());
                                        sbaf.append("\r\n");
                                    }
                                }
                            }
                            //控制ResponseBody
                            if (b3) {
                                if (cIndex == 6) {
                                    String str = cell.toString();
                                    if (str.length() > 0) {
                                        sb3.append("\t\tpublic String " + cell.toString() + ";");
                                        int i = str.codePointAt(0);
                                        char j = (char) i;
                                        char c = Character.toUpperCase(j);
                                        String s1 = str.replaceFirst(j + "", c + "");
                                        sb7.append("\t\tpublic String get" + s1 + "() {\n" +
                                                "\t\t\treturn " + cell.toString() + ";\n" +
                                                "\t\t}\n" +
                                                "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                                "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                                "\t\t}\r\n");
                                    }
                                }
                                if (cIndex == 7) {
                                    if (cell != null && cell.toString().length() > 0) {
                                        sb3.append("//" + cell.toString());
                                        sb3.append("\r\n");
                                    }
                                }
                            }
                            //控制ResponseBody的list
                            if (b4) {
                                if (cIndex == 6) {
                                    String str = cell.toString();
                                    if (str.length() > 0) {
                                        sb4.append("\t\tpublic String " + cell.toString() + ";");
                                        int i = str.codePointAt(0);
                                        char j = (char) i;
                                        char c = Character.toUpperCase(j);
                                        String s1 = str.replaceFirst(j + "", c + "");
                                        sb8.append("\t\tpublic String get" + s1 + "() {\n" +
                                                "\t\t\treturn " + cell.toString() + ";\n" +
                                                "\t\t}\n" +
                                                "\t\tpublic void set" + s1 + "(String " + cell.toString() + ") {\n" +
                                                "\t\t\tthis." + cell.toString() + " = " + cell.toString() + ";\n" +
                                                "\t\t}\r\n");
                                    }

                                }
                                if (cIndex == 7) {
                                    if (cell != null && cell.toString().length() > 0) {
                                        sb4.append("//" + cell.toString());
                                        sb4.append("\r\n");
                                    }
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

                stringBuffer.append("package com.esb.support.interfaces;\n" +
                        "\n" +
                        "import com.esb.om.*;\n" +
                        "import com.esb.support.EsbSupport;\n" +
                        "\n" +
                        "import java.io.Serializable;\n" +
                        "import java.util.ArrayList;\n" +
                        "import java.util.List;\r\n");
                stringBuffer.append("@InterfaceName(\"esb-"+jiekouhao + jiekouming + "\")\r\n");
                stringBuffer.append("public class EsbInterface" + jiekouhao + " extends EsbSupport {\n" +
                        "\n" +
                        "\tpublic EsbInterface" + jiekouhao + "(String emp, String optDept, String macAdd,\n" +
                        "                              String reqSN) {\n" +
                        "\t\tsuper(emp, optDept, macAdd, reqSN);\n" +
                        "\t}\n" +
                        "\n" +
                        "\t@Override\n" +
                        "\tprotected void initRequestHead(MessageRequestHead requestHead) {\n" +
                        "\t\trequestHead.setInterfaceCode(\"" + jiekouhao + "\");\n" +
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
                stringBuffer.append("\t//请求体\r\n");
                if (sb1.length() != 0) {
                    stringBuffer.append("\tpublic static class RequestBody extends MessageRequestBody{\r\n");
                    stringBuffer.append(sb1.toString());
                    stringBuffer.append(sb5.toString());
                    if (sb2.length() != 0) {
                        stringBuffer.append("\t\tpublic List<" + className + "> " + listName + " = new ArrayList<" + className + ">();\r\n");
                        stringBuffer.append("\t\tpublic List<" + className + "> get" + listNameUpper + "() {\n" +
                                "\t\t\treturn " + listName + ";\n" +
                                "\t\t}\n" +
                                "\t\tpublic void set" + listNameUpper + "(List<" + className + "> " + listName + ") {\n" +
                                "\t\t\tthis." + listName + " = " + listName + ";\n" +
                                "\t\t}\r\n");
                    }
                    if (sbaf.length() != 0) {
                        stringBuffer.append("\t\tpublic RequestBody" + jiekouhao + " before = new RequestBody" + jiekouhao + "();\n" +
                                "        public RequestBody" + jiekouhao + " after  = new RequestBody" + jiekouhao + "();\r\n");
                        stringBuffer.append("        public RequestBody" + jiekouhao + " getBefore() {\n" +
                                "            return before;\n" +
                                "        }\n" +
                                "\n" +
                                "        public void setBefore(RequestBody" + jiekouhao + " before) {\n" +
                                "            this.before = before;\n" +
                                "        }\n" +
                                "\n" +
                                "        public RequestBody" + jiekouhao + " getAfter() {\n" +
                                "            return after;\n" +
                                "        }\n" +
                                "\n" +
                                "        public void setAfter(RequestBody" + jiekouhao + " after) {\n" +
                                "            this.after = after;\n" +
                                "        }");
                    }
                    stringBuffer.append("\t}\r\n");
                }
                stringBuffer.append("\tpublic static class Response extends ResponseVO {\r\n");
                stringBuffer.append("\t\t@Override\r\n");
                stringBuffer.append("\t\tprotected MessageResponseHead initResponseHead() {\n" +
                        "\t\t\treturn null;\n" +
                        "\t\t}\r\n");
                stringBuffer.append("\t\t@Override\n" +
                        "\t\tprotected MessageResponseBody initResponseBody() {\n" +
                        "\t\t\treturn new ResponseBody();\n" +
                        "\t\t}\r\n");
                stringBuffer.append("\t}\r\n");

                stringBuffer.append("\tpublic static class ResponseBody extends MessageResponseBody {\r\n");
                if (sb4.length() != 0) {
                    stringBuffer.append("\t\tpublic List<ResponseBody" + jiekouhao + "> data = new ArrayList<ResponseBody" + jiekouhao + ">();\r\n");
                    stringBuffer.append("\t\tpublic List<ResponseBody" + jiekouhao + "> getData() {\n" +
                            "\t\t\treturn data;\n" +
                            "\t\t}\r\n");
                    stringBuffer.append("\t\tpublic void setData(List<ResponseBody" + jiekouhao + "> data) {\n" +
                            "\t\t\tthis.data = data;\n" +
                            "\t\t}\r\n");
                    stringBuffer.append("\t}\r\n");
                } else {
                    stringBuffer.append(sb3.toString());
                    stringBuffer.append(sb7.toString());
                    stringBuffer.append("\t}\r\n");
                }

                if (sb4.length() != 0) {
                    stringBuffer.append("\tpublic static class ResponseBody" + jiekouhao + " implements Serializable {\r\n");
                    stringBuffer.append(sb4.toString());
                    stringBuffer.append(sb8.toString());
                    stringBuffer.append("\t}\r\n");
                }
                if (sb2.length() != 0) {
                    stringBuffer.append("\tpublic static class " + className + " implements Serializable{\r\n");
                    stringBuffer.append(sb2.toString());
                    stringBuffer.append(sb6.toString());
                    stringBuffer.append("\t}\r\n");
                }
                if (sbaf.length() != 0) {
                    stringBuffer.append("\tpublic static class RequestBody" + jiekouhao + " implements java.io.Serializable{");
                    stringBuffer.append(sbaf.toString());
                    stringBuffer.append(sbaf1.toString());
                    stringBuffer.append("\t}\r\n");
                }
                stringBuffer.append("}");
                //System.out.println(stringBuffer.toString());


            }else {
                System.out.println("找不到指定的文件");
            }
        }else{
            Workbook wb;
            Sheet sheet = null;
            try {
                wb= new XSSFWorkbook(excel);
                sheet = wb.getSheet(jiekouming);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (InvalidFormatException e) {
                e.printStackTrace();
            }

            //开始解析
            //读取sheet
            int firstRowIndex = sheet.getFirstRowNum();
            int lastRowIndex = sheet.getLastRowNum();
            StringBuffer sb1 = new StringBuffer();
            StringBuffer sbsup = new StringBuffer();
            //去除前面的空列
            int rowIndex = 99999;
            int var = 0;
            int ann = 0;
            int count = 0;
            for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {   //遍历行
                // System.out.println("rIndex: " + rIndex);
                Row row = sheet.getRow(rIndex);
                if (row != null) {
                    int firstCellIndex = row.getFirstCellNum();
                    int lastCellIndex = row.getLastCellNum();
                    for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {   //遍历列
                        Cell cell = row.getCell(cIndex);
                        //去除前面的空行
                        if(count == 0){
                            if("参数名".equals(cell.toString())){

                                var = cIndex;
                                ann = cIndex+2;
                                rowIndex = rIndex;
                                count++;
                            }
                        }
                        if(rIndex>rowIndex) {
                            if (cIndex == var) {
                                if(cell.toString().length()>0) {
                                    sb1.append("\t\tpublic String " + cell.toString() + ";");
                                    sbsup.append("\t\torderData." + cell.toString() + " = \"\";\r\n");
                                }
                            }
                            if (cIndex == ann) {
                                //StringBuffer的append在循环体中比String的+快很多，循环很大的情况下
                                if(cell.toString().length()>0)
                                    sb1.append("//" + cell.toString());
                                sb1.append("\r\n");
                            }
                        }
                    }
                }
            }
            //System.out.println(sb1.toString());
            stringBuffer.append("package com.apexsoft.plugins.wzh.config;\r\n");
            stringBuffer.append("import com.apexsoft.plugins.exception.WzhException;\r\n");
            stringBuffer.append("import org.springframework.stereotype.Component;\r\n\r\n");
            stringBuffer.append("@Component(\""+jiekouhao+"\")\r\n" +
                    "public class AgrementParse"+jiekouhao+" implements AgreementParse {\r\n" +
                    "\n" +
                    "    public static class OrderData{\r\n");
            stringBuffer.append(sb1.toString());
            stringBuffer.append("\t}\r\n");
            stringBuffer.append("\n" +
                    "    @Override\r\n" +
                    "    public JSONObject parseFromJson(JSONObject data) throws WzhException {\r\n\r\n");
            stringBuffer.append("       AgrementParse"+jiekouhao+".OrderData orderData = new AgrementParse"+jiekouhao+".OrderData();\r\n");
            stringBuffer.append(sbsup.toString());
            stringBuffer.append("       return JSONObject.fromObject(GsonCreator.getGson().toJson(orderData));\n");
            stringBuffer.append("\t}\r\n");
            stringBuffer.append("}");
        }
        String content = stringBuffer.toString();

        //写入文件
        BufferedWriter bw = null;
        try {
            File file = null;
            if(esb) {
                file = new File(path+"\\\\"+"EsbInterface" + jiekouhao + ".java");
            }else {
                file = new File(path+"\\\\"+"AgrementParse" + jiekouhao + ".java");
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            bw.write(content);
            System.out.println("Done");
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
