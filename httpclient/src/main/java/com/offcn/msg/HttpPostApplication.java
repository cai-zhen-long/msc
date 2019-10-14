package com.offcn.msg;


import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/10/11.
 */
public class HttpPostApplication {
    public static void main(String[] args) throws IOException {
        //1.确定URL
        String indexURl = "https://www.chsi.com.cn/";
        //2.发送请求,获取数据
        //创建请求方法对象
        HttpPost httpPost = new HttpPost(indexURl);
        //设置请求头
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/70.0.3538.110 Safari/537.36");
        //设置请求体
        List<BasicNameValuePair> list = new ArrayList();
        list.add(new BasicNameValuePair("username", "liang"));
        list.add(new BasicNameValuePair("password", "123"));
        HttpEntity requestEntity = new UrlEncodedFormEntity(list);
        httpPost.setEntity(requestEntity);
        //创建请求对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //发送请求获取数据
        CloseableHttpResponse response = httpClient.execute(httpPost);
        //判断是否请求成功
        System.out.println(response.getStatusLine().getStatusCode());
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            System.out.println(EntityUtils.toString(entity, "UTF-8"));
        }
    }
}
