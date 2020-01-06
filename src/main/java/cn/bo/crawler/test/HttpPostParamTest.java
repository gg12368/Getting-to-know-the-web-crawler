package cn.bo.crawler.test;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

public class HttpPostParamTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //1.打开浏览器，创建一个HttpClient对象
        CloseableHttpClient httpClient= HttpClients.createDefault();
        //2.输入网址，发起get请求创建HttpGet对象
        HttpPost httpPost=new HttpPost("http://yun.itheima.com/search");

        //声明List集合，封装表单中参数
        List<NameValuePair> params=new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("keys","Java"));
        //创建表单的Entity对象。第一个参数是封装好的表单数据，第二个参数是编码
        UrlEncodedFormEntity formEntity=new UrlEncodedFormEntity(params,"UTF-8");
        //设置表单的Entity对象到Post请求中
        httpPost.setEntity(formEntity);

        //3.回车键，发起请求，返回响应，使用HttpClient对象发起请求
        CloseableHttpResponse response=null;
        try {
            response=httpClient.execute(httpPost);

            //4.解析响应，获取数据
            //判断状态码是否是200
            if(response.getStatusLine().getStatusCode()==200){
                HttpEntity httpEntity=response.getEntity();
                String content= EntityUtils.toString(httpEntity,"UTF-8");
                //System.out.println(content);
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
