package com.readexcel.readexcel.test;

import com.readexcel.readexcel.read.HttpClientUtil;
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

public class httptest {
    public static void main(String[] args) {
        httptest httptest = new httptest();
        long start = System.currentTimeMillis();
        for(int i=1;i<=500;i++){
            httptest.doPost("测试"+i+"号");
        }
      long end =  System.currentTimeMillis();


        System.out.println(end-start);
    }

    public void doPost(String username){
        String url = "https://test.nainaiwang.com/api/auth/jwt/passwordLogin";
       // String username = "tutua";
        String password = "123456";
        String param = "{\"username\": \""+username+"\", \"password\": \""+password+"\"}";
        //String param = "token:eyJhbGciOiJSUzI1NiJ9.eyJzdWIiOiJ0dXR1YSIsInVzZXJJZCI6IjZhY2Y4MWIxYWM5MjQ3ZGJiZTRmNmQ2NTFhYmRiYzhiIiwibmFtZSI6IjE1NzM1MTcwNjgyIiwiZXhwIjoxNTcyNTAzODQ0fQ.D5es1CYnnZt85bhDTj34QJd-Z_vTToKrTHlZ5L7rZO30sown45tPTt2zcoJkkFSXpZbRIy9Z5pfjhmnBeWmfYZAw8tEQ1TlCxKTx20Xlmgwto4EFlCfF0s7hSsq3UqDj-MSRfDPYmJYomWfrj4m7TMvmVPFFn-Dqn5LIoGvfYQA";
        HttpPost httpPost = new HttpPostCustomize(url);//httppost
        try {
            StringEntity stringEntity = new StringEntity(param,"utf-8");
            httpPost.setEntity(stringEntity);
            CloseableHttpClient httpClient = HttpClients.createDefault();
            String jsonStr = (String)httpClient.execute(httpPost,new ResponseHandlerImpl());
            System.out.println(jsonStr);
        } catch (IOException ex) {
            ex.printStackTrace();
        } finally {
            httpPost.releaseConnection();
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
    class ResponseHandlerImpl implements ResponseHandler {
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
}
