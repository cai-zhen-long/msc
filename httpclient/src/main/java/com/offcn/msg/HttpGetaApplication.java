package com.offcn.msg;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * Created by Administrator on 2019/10/11.
 */
public class HttpGetaApplication {
    public static void main(String[] args) throws Exception {
        //确定首页url
        String indexUrl = "http://www.ujiuye.com";
        //发送请求,获取数据
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http方法对象
        HttpGet httpGet = new HttpGet(indexUrl);
        //设置请求头
        httpGet.setHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
        //发送请求,获取响应的数据
        CloseableHttpResponse response = httpClient.execute(httpGet);

        //获取请求的响应码
        if(response.getStatusLine().getStatusCode()==200){
           // Header[] headers = response.getHeaders("Content-Type");
            //System.out.println(headers[0].getValue());
            //获取响应体
            HttpEntity httpEntity = response.getEntity();
            String html=EntityUtils.toString(httpEntity,"GB2312");
            System.out.println(html);
        }
    }
}
