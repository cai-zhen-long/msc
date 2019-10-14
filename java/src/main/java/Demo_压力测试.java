

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by Administrator on 2019/10/10.
 */
public class Demo_压力测试 {

    public static void main(String[] args) {

        PutMoney putMoney = new PutMoney();
        for(int i=1;i<=1;i++){
            new Thread(putMoney).start();
        }

    }
}


class PutMoney implements Runnable {
    public void run() {

        //确定首页url
        //String indexUrl = "http://localhost:9001/dept/queryAll";
        String indexUrl = "http://localhost:9001/dept/queryAll";

        //发送请求,获取数据
        //创建HttpClient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //创建http方法对象
        HttpGet httpGet = new HttpGet(indexUrl);
        //设置请求头
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:69.0) Gecko/20100101 Firefox/69.0");
        //发送请求,获得相应的数据
        while (true) {
            CloseableHttpResponse response = null;
            try {
                response = httpClient.execute(httpGet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //获得请求的响应码
            if (response.getStatusLine().getStatusCode() == 200) {
                HttpEntity httpEntity = response.getEntity();
                try {
                    System.out.println(EntityUtils.toString(httpEntity, "GB2312") + "------------------");// + (++count)
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
