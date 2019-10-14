package com.offcn.msg;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.parser.JSONParser;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import redis.clients.jedis.Jedis;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2019/10/11.
 */
public class JsonNews {

    public static void main(String[] args) throws IOException {

        String newurl = "bigdata:news:url";
        Jedis jedis = JedisUtils.getJedis();
        int i = 0;
        while (true) {
            //确定访问地址
            String url = "https://pacaio.match.qq.com/irs/rcd?cid=146&token=49cbb2154853ef1a74ff4e53723372ce&ext=ent&page=" + (i++);
            //确定HttpClient请求对象
            CloseableHttpClient httpClient = HttpClients.createDefault();
            //确定http请求方法
            HttpGet httpGet = new HttpGet(url);
            //执行请求
            CloseableHttpResponse response = httpClient.execute(httpGet);
            //获取请求体
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity entity = response.getEntity();
                String result = EntityUtils.toString(entity, "GB2312");
                NewList newList = JSON.parseObject(result, NewList.class);
                List<New> list = newList.getData();

                System.out.println("去重前--------------"+list.size());

                for (int j = 0; j < list.size(); j++) {
                    New news = list.get(j);
                    boolean flag = jedis.hexists(newurl, news.getUrl());
                    if (flag) {
                        System.out.println("存在：" + news.getUrl());
                        list.remove(news);
                    } else {
                        System.out.println("不存在：" + news.getUrl());
                        jedis.hset(newurl, news.getUrl(), JSON.toJSONString(news));
                    }
                }

                if(list.size()==0){
                    break;
                }

                System.out.println("去重后--------------"+list.size());
            }
            System.out.println("读取第"+i+"页");

        }
        jedis.close();
    }
}
