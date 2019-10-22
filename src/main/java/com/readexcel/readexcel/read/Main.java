package com.readexcel.readexcel.read;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author coder
 * @title Main
 * @description
 * @date 2019/7/10 17:07
 * ((      /|_/|
 * \\.._.'  , ,\
 * /\ | '.__ v /
 * (_ .   /   "
 * ) _)._  _ /
 * '.\ \|( / (
 * '' ''\\ \\
 * 一直被模仿，从未被超越--SCW
 */
public class Main {

    public static void main(String[] args) {

        String interfaceCode = "090001";

        HttpClientUtil httpClientUtil = new HttpClientUtil("sicongwei","111111");//登录获取token
        String systemRes = httpClientUtil.doPost("http://172.190.104.46:8080/system/query","{\"token\":\"\",\"params\":{}}");//获取systemId;
        JSONObject systemDesc = JSONObject.fromObject(systemRes);
        JSONObject result = systemDesc.optJSONObject("result");
        JSONArray systemList = result.optJSONArray("systemList");
        String interfaceId = "";

        c:for (int i=0;i<systemList.size();i++){//循环获取interfaceId(注:interfaceId和interfacecode一一对应)

            String systemId = systemList.optJSONObject(i).optString("systemId");
            String interfaceCount = systemList.optJSONObject(i).optString("interfaceCount");
            String resStr = httpClientUtil.doPost("http://172.190.104.46:8080/interface/query",
                    "{\"token\":\"\",\"params\":{\"moduleId\":\"1\",\"systemId\":\""+systemId+"\",\"moduleOperating\":\"0\"},\"pageSize\":"+interfaceCount+",\"pageNum\":1}");
            JSONObject res = JSONObject.fromObject(resStr);
            JSONArray jsonArray = res.optJSONObject("result").optJSONArray("list");

            for (int j=0;j<jsonArray.size();j++){
                String interfaceCodeTemp = jsonArray.optJSONObject(j).optString("interfaceCode");
                if(interfaceCode.equals(interfaceCodeTemp)){
                    interfaceId = jsonArray.optJSONObject(j).optString("interfaceId");
                    break c;
                }
            }
        }
        String interfaceDetailUrl = "http://172.190.104.46:8080/interface/queryDetail";
        String param = "{\"token\": \"\", \"params\": {\"interfaceId\": \""+interfaceId+"\", \"moduleOperating\": \"0\"}}";
        String res =httpClientUtil.doPost(interfaceDetailUrl,param);
        JSONObject obj = JSONObject.fromObject(res);

        /*JSONObject result1 = obj.optJSONObject("result");
        JSONArray request = result1.optJSONArray("request");
        JSONArray response = result1.optJSONArray("response");
        JSONObject jsonObject = request.optJSONObject(1);
        //JSONArray jsonArray = request.getJSONArray(1);
        JSONArray children = jsonObject.optJSONArray("children");
        String string = children.optJSONObject(1).optString("FieldName");
       // System.out.println("before".equals(string));
        if("before".equals(string)){
            JSONArray children1 = children.optJSONObject(1).optJSONArray("children");
            for(int i=0; i<children1.size(); i++){
                System.out.println(children1.optJSONObject(i).optString("FieldName"));
            }
            JSONArray children2 = children.optJSONObject(2).optJSONArray("children");
            for(int i=0; i<children2.size(); i++){
                System.out.println(children2.optJSONObject(i).optString("FieldName"));
            }
        }*/
        System.out.println(obj);

        doit(obj,interfaceCode);
    }

    public static void doit( JSONObject obj,String interfaceCode){
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

        String className = "RequestBody"+interfaceCode;
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


        String jiekouhao = interfaceCode;
        String jiekouming = "";
        //String className = "RequestBody"+jiekouhao;

        JSONObject result1 = obj.optJSONObject("result");
        JSONObject jsonObject2 = obj.optJSONObject("");
        System.out.println(jsonObject2+"+++++++++++++++++++++++++++++++++++++++");
        jiekouming= result1.optString("interfaceName");
        //请求
        JSONArray request = result1.optJSONArray("request");
        //响应
        JSONArray response = result1.optJSONArray("response");
        //请求消息体
        JSONObject jsonObject = request.optJSONObject(1);
        //JSONArray jsonArray = request.getJSONArray(1);
        //请求消息体具体内容
        JSONArray children = jsonObject.optJSONArray("children");
        //请求具体字段
        String string = "";
        String listName = "";
        String listNameUpper = "";
        String name = children.optJSONObject(children.size()-1).optString("FieldName");
        b1= true;
        if("unqualifiedAccItem".equals(name)||"ymtAcctList".equals(name)||"securityList".equals(name)||"custAccList".equals(name)){
            b2=true;
            listName = name;
            String substring = name.substring(0, 1);
            String substring1 = substring.toUpperCase();
            listNameUpper =  listName.replaceFirst(substring , substring1);
        }


       /* if(b1) {
            for (int i=0;i<children.size()-1;i++){
                String str = children.optJSONObject(i).optString("FieldName");
                sb1.append("\t\tpublic String "+str);
                int i1 = str.codePointAt(0);
                char j1 = (char) i1;
                char c1 = Character.toUpperCase(j1);
                String ss1 = str.replaceFirst(j1 + "", c1 + "");
                sb5.append("\t\tpublic String get" + ss1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + ss1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sb1.append("//" + children.optJSONObject(i).optString("FieldDesc"));
                sb1.append("\r\n");
            }
        }*/
        if(b2){
            JSONArray children1 =   children.optJSONObject(children.size()-1).optJSONArray("children");
            //JSONArray jsonArray =   children.optJSONObject(children.size()-1).optJSONArray("children");
            // JSONArray children1 = jsonArray.optJSONObject(0).optJSONArray("children");
            for (int j=0;j<children1.size();j++){
                String str = children1.optJSONObject(j).optString("FieldName");
                sb2.append("\t\tpublic String "+str);
                int i1 = str.codePointAt(0);
                char j1 = (char) i1;
                char c1 = Character.toUpperCase(j1);
                String ss1 = str.replaceFirst(j1 + "", c1 + "");
                sb6.append("\t\tpublic String get" + ss1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + ss1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sb2.append("//" + children1.optJSONObject(j).optString("FieldDesc"));
                sb2.append("\r\n");
            }
        }

        if(children.size()>1)
            string = children.optJSONObject(1).optString("FieldName");
        //System.out.println("before".equals(string));
        //if()

        if("before".equals(string)){
            String string2 = children.optJSONObject(0).optString("FieldName");
                sb1.append("        public String " + string2.toString() + ";");
            int i = string2.codePointAt(0);
            char j = (char) i;
            char c = Character.toUpperCase(j);
            String s1 = string2.replaceFirst(j + "", c + "");
            sb5.append("\t\tpublic String get" + s1 + "() {\n" +
                    "\t\t\treturn " + string2.toString() + ";\n" +
                    "\t\t}\n" +
                    "\t\tpublic void set" + s1 + "(String " + string2.toString() + ") {\n" +
                    "\t\t\tthis." + string2.toString() + " = " + string2.toString() + ";\n" +
                    "\t\t}\r\n");
            sb1.append("//" + children.optJSONObject(0).optString("FieldDesc"));
            sb1.append("\r\n");
            JSONArray children1 = children.optJSONObject(1).optJSONArray("children");
            for(int k=0; k<children1.size(); k++){
                //System.out.println(children1.optJSONObject(i).optString("FieldName"));
                String str = children1.optJSONObject(k).optString("FieldName");
                sbaf.append("\t\t"+str);
                int i1 = str.codePointAt(0);
                char j1 = (char) i1;
                char c1 = Character.toUpperCase(j1);
                String ss1 = str.replaceFirst(j1 + "", c1 + "");
                sbaf1.append("\t\tpublic String get" + ss1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + ss1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sbaf.append("//" + children1.optJSONObject(k).optString("FieldDesc"));
                sbaf.append("\r\n");
            }
            baf  = true;
            b1 = true;
        }else{

            int size = b2 ? children.size()-1:children.size();

            for(int k=0; k<size; k++){
                String str = children.optJSONObject(k).optString("FieldName");
                sb1.append("\t\tpublic String " + str.toString() + ";");
                int i = str.codePointAt(0);
                char j = (char) i;
                char c = Character.toUpperCase(j);
                String s1 = str.replaceFirst(j + "", c + "");
                sb5.append("\t\tpublic String get" + s1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + s1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sb1.append("//" + children.optJSONObject(k).optString("FieldDesc"));
                sb1.append("\r\n");
                b1 = true;
            }
        }
        //请求响应体
        JSONObject jsonObject1 = response.optJSONObject(1);
        //请求响应体具体内容
        JSONArray children1 = jsonObject1.optJSONArray("children");
        String string1 = "";
        if(children1.size()>2)
            string1 = children1.optJSONObject(2).optString("FieldName");
        if("list".equals(string1)||"return".equals(string1)||"item".equals(string1)){
            JSONArray children2 = children1.optJSONObject(2).optJSONArray("children");
            for (int k=0; k<children2.size();k++){
                String str = children2.optJSONObject(k).optString("FieldName");
                sb4.append("\t\tpublic String " + str.toString() + ";");
                int i = str.codePointAt(0);
                char j = (char) i;
                char c = Character.toUpperCase(j);
                String s1 = str.replaceFirst(j + "", c + "");
                sb8.append("\t\tpublic String get" + s1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + s1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sb4.append("//" + children2.optJSONObject(k).optString("FieldDesc"));
                sb4.append("\r\n");
            }
            b4=true;
        }else{
            for(int k=0; k<children1.size(); k++){
                String str = children1.optJSONObject(k).optString("FieldName");
                sb3.append("\t\tpublic String " + str.toString() + ";");
                int i = str.codePointAt(0);
                char j = (char) i;
                char c = Character.toUpperCase(j);
                String s1 = str.replaceFirst(j + "", c + "");
                sb7.append("\t\tpublic String get" + s1 + "() {\n" +
                        "\t\t\treturn " + str.toString() + ";\n" +
                        "\t\t}\n" +
                        "\t\tpublic void set" + s1 + "(String " + str.toString() + ") {\n" +
                        "\t\t\tthis." + str.toString() + " = " + str.toString() + ";\n" +
                        "\t\t}\r\n");
                sb3.append("//" + children1.optJSONObject(k).optString("FieldDesc"));
                sb3.append("\r\n");
            }
            b3 = true;
        }




        StringBuffer stringBuffer = new StringBuffer();
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
            stringBuffer.append("\tpublic static class RequestBody" + jiekouhao + " implements java.io.Serializable{\r\n");
            stringBuffer.append(sbaf.toString());
            stringBuffer.append(sbaf1.toString());
            stringBuffer.append("\t}\r\n");
        }
        stringBuffer.append("}");
        //System.out.println(stringBuffer.toString());
        String path =  "D:\\\\jar" ;
        String content = stringBuffer.toString();
        boolean esb = true;
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
        //System.out.println(stringBuffer.toString());
    }
        //jsonObject1.optJSONArray("")
}


