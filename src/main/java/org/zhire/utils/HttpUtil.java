package org.zhire.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;

@Slf4j
public class HttpUtil {

    /**
     * @Desc 发送HttpGet请求
     * @Param
     * @Author zhaocong
     * @Date 2019/4/9 16:57
     **/
    public static String httpGet(String url, Map<Object, Object> map) {
        String result = "";
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            Iterator<Map.Entry<Object, Object>> iteratorMap = map.entrySet().iterator();
            //封装参数到url中
            int count = 0;
            while (iteratorMap.hasNext()) {
                Map.Entry<Object, Object> next = iteratorMap.next();
                if (count == 0) {
                    url = url + "?" + next.getKey() + "=" + next.getValue();
                } else {
                    url = url + "&" + next.getKey() + "=" + next.getValue();
                }
                count++;
            }
            HttpGet httpGet = new HttpGet(url);
            // 执行get请求.
            result = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), "UTF-8");
            //释放连接
            httpGet.releaseConnection();
            httpClient.close();
            //返回数据
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 通过代理发送get请求
     *
     * @param url
     * @param map
     * @param prodIpAddress
     * @param port
     * @return
     */
    public static String httpGetByProxy(String url, Map<Object, Object> map, String prodIpAddress, Integer port) {
        String result = null;
        HttpHost proxy = new HttpHost(prodIpAddress, port); // 添加代理
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            Iterator<Map.Entry<Object, Object>> iteratorMap = map.entrySet().iterator();
            // 封装参数到url中
            int count = 0;
            while (iteratorMap.hasNext()) {
                Map.Entry<Object, Object> next = iteratorMap.next();
                if (count == 0) {
                    url = url + "?" + next.getKey() + "=" + next.getValue();
                } else {
                    url = url + "&" + next.getKey() + "=" + next.getValue();
                }
                count++;
            }
            // 代理
            RequestConfig config = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .build();
            // 创建httpGet
            HttpGet httpGet = new HttpGet(url);
            httpGet.setConfig(config);
            // 执行get请求
            result = EntityUtils.toString(httpClient.execute(httpGet).getEntity(), "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
            log.info(e.getMessage());
        } finally {
            // 关闭连接，释放资源
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * @Desc 发送httppost请求
     * @Param
     * @Author zhaocong
     * @Date 2019/4/16 10:22
     **/
    public static String httpPost(String url, Map map) {
        String result = "";
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(new StringEntity(objectMapper.writeValueAsString(map),
                        ContentType.create("text/json", "UTF-8")));
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * @Desc 发送httppost请求
     * @Param
     * @Author zhaocong
     * @Date 2019/4/16 10:22
     **/
    public static String httpPostByStr(String url, String str) {
        String result = "";
        try {
            CloseableHttpClient client = null;
            CloseableHttpResponse response = null;
            try {
                ObjectMapper objectMapper = new ObjectMapper();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setHeader(HTTP.CONTENT_TYPE, "application/json");
                httpPost.setEntity(new StringEntity(str,
                        ContentType.create("text/json", "UTF-8")));
                client = HttpClients.createDefault();
                response = client.execute(httpPost);
                HttpEntity entity = response.getEntity();
                result = EntityUtils.toString(entity);
                return result;
            } finally {
                if (response != null) {
                    response.close();
                }
                if (client != null) {
                    client.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    // post请求参数为json格式
    public static String HttpPostWithJson(String url, String json, String prodIpAddress, Integer port) {
        String returnValue = "这是默认返回值，接口调用失败";
        log.info("url:{} , json:{} , ip:{} , port:{}", url, json, prodIpAddress, port);
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();

            // HttpClient使用代理IP
            // HttpHost proxy = new HttpHost("10.1.30.26" , 10000);
            log.info("prodIpAddress: {}", prodIpAddress);
            log.info("port: {}", port);
            HttpHost proxy = new HttpHost(prodIpAddress, port);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .build();

            // 第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.setEntity(requestEntity);
            // 第四步：发送HttpPost请求，获取返回值
            log.info("开始发送HttpPost请求");
            returnValue = httpClient.execute(httpPost, responseHandler); // 调接口获取返回值时，必须用此方法
            log.info("发送HttpPost请求，获取返回值:{}", returnValue);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        //第五步：处理返回值
        return returnValue;
    }

    /**
     * @Desc 发送httppost请求--加header
     * @Param
     * @Author zhaocong
     * @Date 2019/4/16 10:22
     **/
    public static String httpPostAddHeader(String url, String json, String prodIpAddress, Integer port, String authorization) {
        String returnValue = "这是默认返回值，接口调用失败";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        try {
            //第一步：创建HttpClient对象
            httpClient = HttpClients.createDefault();
            // HttpClient使用代理IP
            // HttpHost proxy = new HttpHost("10.1.30.26" , 10000);
            HttpHost proxy = new HttpHost(prodIpAddress, port);
            RequestConfig requestConfig = RequestConfig.custom()
                    .setProxy(proxy)
                    .setConnectTimeout(10000)
                    .setSocketTimeout(10000)
                    .setConnectionRequestTimeout(3000)
                    .build();

            // 第二步：创建httpPost对象
            HttpPost httpPost = new HttpPost(url);
            httpPost.setConfig(requestConfig);
            // 第三步：给httpPost设置JSON格式的参数
            StringEntity requestEntity = new StringEntity(json, "utf-8");
            requestEntity.setContentEncoding("UTF-8");
            httpPost.setHeader("Content-type", "application/json");
            httpPost.addHeader("Authorization", authorization);
            httpPost.setEntity(requestEntity);
            // 第四步：发送HttpPost请求，获取返回值
            returnValue = httpClient.execute(httpPost, responseHandler); // 调接口获取返回值时，必须用此方法

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        // 第五步：处理返回值
        return returnValue;
    }

}


