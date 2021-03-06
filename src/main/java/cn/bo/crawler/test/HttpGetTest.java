package cn.bo.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpGetTest {
    public static void main(String[] args) {
        //1.打开浏览器，创建一个HttpClient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //2.输入网址，发起get请求创建HttpGet对象
        HttpGet httpGet=new HttpGet("http://www.itcast.cn");
        //3.回车键，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpGet);

            //4.解析响应，获取数据
            //判断状态码是否是200
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity=response.getEntity();
                String content= EntityUtils.toString(httpEntity,"UTF-8");
                System.out.println(content);
                System.out.println(content.length());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            //关闭response
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
