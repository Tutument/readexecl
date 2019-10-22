package com.readexcel.readexcel.read;

import net.sf.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author coder
 * @title HttpClientUtil
 * @description
 * @date 2019/7/10 16:49
 * ((      /|_/|
 * \\.._.'  , ,\
 * /\ | '.__ v /
 * (_ .   /   "
 * ) _)._  _ /
 * '.\ \|( / (
 * '' ''\\ \\
 * 一直被模仿，从未被超越--SCW
 */
public class HttpClientUtil {

//    private Integer count = 0;

    private String userName;

    private String password;

    private String token;

    public HttpClientUtil() {
    }

    public HttpClientUtil(String userName , String password){
        this.userName = userName;
        this.password = password;
        token = loginModel(userName,password);
    }

    public String doPost(String url, String param){
        String responseBody = null;
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPostCustomize(url);
        httpPost.addHeader("X-Auth-Token",token);
        StringEntity stringEntity = new StringEntity(param,"utf-8");
        httpPost.setEntity(stringEntity);
        final String userName =this.userName;
        final String password = this.password;
        try {
            responseBody = (String)httpClient.execute(httpPost,new ResponseHandlerImpl());
        } catch (IOException e) {
            e.printStackTrace();
            if(e.getMessage().indexOf("获取token失败")>=0){
                token = loginModel(userName,password);
                return doPost(url,param);
            }
        }finally {
            httpPost.releaseConnection();
        }
        return responseBody;
    }
    //获取登录token
    public  String loginModel(String username, String password) {
        String token = "";
        String url = "http://172.190.104.46:8080/system/session";
        String param = "{\"employee_no\": \""+username+"\", \"password\": \""+password+"\"}";
        HttpPost httpPost = new HttpPostCustomize(url);//httppost
        try {
            StringEntity stringEntity = new StringEntity(param,"utf-8");
            httpPost.setEntity(stringEntity);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String jsonStr = (String)httpClient.execute(httpPost,new ResponseHandlerImpl());
            JSONObject obj = JSONObject.fromObject(jsonStr);
            token = obj.optString("token");
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return token;
    }

    class ResponseHandlerImpl implements ResponseHandler{
        @Override
        public Object handleResponse(HttpResponse httpResponse) throws ClientProtocolException, IOException {
            int status = httpResponse.getStatusLine().getStatusCode();
            String token = "";
            if(status>=200 && status<300){
                HttpEntity entity = httpResponse.getEntity();
                return entity!=null? EntityUtils.toString(entity):null;
            }else if(status ==403){
                throw new ClientProtocolException("获取token失败：" + status);
            }
            throw new ClientProtocolException("返回状态码异常：" + status);
        }
    }

    class HttpPostCustomize extends HttpPost{
        public HttpPostCustomize(String uri) {
            super(uri);
            super.addHeader("Content-Type","application/json; charset=utf-8");
            super.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/74.0.3729.169 Safari/537.36");
            super.addHeader("Accept","application/json");
        }
    }

    public static void main(String[] args) {
        System.out.println(new HttpClientUtil().loginModel("sicongwei","111111"));
    }
}
