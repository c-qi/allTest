package org.zhire.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.green.model.v20180509.ImageSyncScanRequest;
import com.aliyuncs.http.FormatType;
import com.aliyuncs.http.HttpResponse;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.http.ProtocolType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 阿里云图片检测
 *
 * @Date 2020/10/19 13:09
 * @Author by chenqi
 */
@Slf4j
public class ImageCheckAliYunUtil {

    // accessKeyId
    private static final String accessKeyId = "";

    // accessKeySecret
    private static final String accessKeySecret = "";

    // porn：图片智能鉴黄, terrorism：图片暴恐涉政, ad：图文违规, qrcode：图片二维码, live：图片不良场景, logo：图片logo
    private static final List<String> scenesList = Collections.singletonList("porn");


    public static void main(String[] args) {
        //System.out.println(checkImg("https://bkimg.cdn.bcebos.com/pic/8718367adab44aed30126fffbe1c8701a18bfb49"));
        System.out.println(checkImg("http://pic.netbian.com/uploads/allimg/180128/112234-1517109754d925.jpg"));
    }

    /**
     * 发送请求
     *
     * @param imgUrl
     * @return
     */
    private static HttpResponse buildParams(String imgUrl) {
        IClientProfile profile = DefaultProfile
                .getProfile("cn-shanghai", accessKeyId, accessKeySecret);
        try {
            DefaultProfile
                    .addEndpoint("cn-shanghai", "cn-shanghai", "Green", "green.cn-shanghai.aliyuncs.com");
        } catch (Exception e) {
            log.info("构建阿里云请求参数失败：{}", e);

        }
        IAcsClient client = new DefaultAcsClient(profile);
        ImageSyncScanRequest imageSyncScanRequest = new ImageSyncScanRequest();
        // 指定API返回格式。
        imageSyncScanRequest.setAcceptFormat(FormatType.JSON);
        // 指定请求方法。
        imageSyncScanRequest.setMethod(MethodType.POST);
        imageSyncScanRequest.setEncoding("utf-8");
        //支持HTTP和HTTPS。
        imageSyncScanRequest.setProtocol(ProtocolType.HTTP);

        JSONObject httpBody = new JSONObject();
        /**
         * 设置要检测的风险场景。计费依据此处传递的场景计算。
         * 一次请求中可以同时检测多张图片，每张图片可以同时检测多个风险场景，计费按照场景计算。
         * 例如：检测2张图片，场景传递porn和terrorism，计费会按照2张图片鉴黄，2张图片暴恐检测计算。
         * porn：表示鉴黄场景。
         */
        httpBody.put("scenes", scenesList);

        /**
         * 设置待检测图片。一张图片对应一个task。
         * 多张图片同时检测时，处理的时间由最后一个处理完的图片决定。
         * 通常情况下批量检测的平均响应时间比单张检测的要长。一次批量提交的图片数越多，响应时间被拉长的概率越高。
         * 这里以单张图片检测作为示例, 如果是批量图片检测，请自行构建多个task。
         */
        JSONObject task = new JSONObject();
        task.put("dataId", UUID.randomUUID().toString());
        task.put("url", imgUrl);
        task.put("time", new Date());
        httpBody.put("tasks", Collections.singletonList(task));

        imageSyncScanRequest.setHttpContent(org.apache.commons.codec.binary.StringUtils.getBytesUtf8(httpBody.toJSONString()),
                "UTF-8", FormatType.JSON);

        /**
         * 请设置超时时间。服务端全链路处理超时时间为10秒，请做相应设置。
         * 如果您设置的ReadTimeout小于服务端处理的时间，程序中会获得一个read timeout异常。
         */
        imageSyncScanRequest.setConnectTimeout(3000);
        imageSyncScanRequest.setReadTimeout(10000);
        HttpResponse httpResponse = null;
        try {
            httpResponse = client.doAction(imageSyncScanRequest);
        } catch (Exception e) {
            log.info("调用阿里云图片检测接口失败:{}", e);
        }
        return httpResponse;
    }


    /**
     * 图片检测
     *
     * @param imgUrl
     * @return
     */
    public static boolean checkImg(String imgUrl) {
        log.info("图片检测入参：{}", imgUrl);
        HttpResponse httpResponse = buildParams(imgUrl);
        boolean flag = false;
        // 服务端接收到请求，完成处理后返回的结果。
        if (httpResponse != null && httpResponse.isSuccess()) {
            JSONObject scrResponse =
                    JSON.parseObject(org.apache.commons.codec.binary.StringUtils.newStringUtf8(httpResponse.getHttpContent()));
            log.info("获取请求结果：{}", JSON.toJSONString(scrResponse, true));
            int requestCode = scrResponse.getIntValue("code");
            // 每一张图片的检测结果。
            JSONArray taskResults = scrResponse.getJSONArray("data");
            if (200 == requestCode) {
                for (Object taskResult : taskResults) {
                    // 单张图片的处理结果。
                    int taskCode = ((JSONObject) taskResult).getIntValue("code");
                    // 图片对应检测场景的处理结果。如果是多个场景，则会有每个场景的结果。
                    JSONArray sceneResults = ((JSONObject) taskResult).getJSONArray("results");
                    if (200 == taskCode) {
                        for (Object sceneResult : sceneResults) {
                            String scene = ((JSONObject) sceneResult).getString("scene");
                            String suggestion = ((JSONObject) sceneResult).getString("suggestion");
                            // 根据scene和suggetion做相关处理。
                            log.info("当前处理的图片场景scene为:{},suggestion为:{}", scene, suggestion);
                            if (!"pass".equals(suggestion)) {
                                flag = false;
                                return flag;
                            } else {
                                flag = true;
                            }
                        }
                    } else {
                        log.info("单张图片处理失败:{}", JSON.toJSONString(taskResult));
                    }
                }
            } else {
                log.info("请求整体处理失败:{}", JSON.toJSONString(scrResponse));
            }
        }
        return flag;
    }

}
