//package org.zhire.config.mq;
//
//import cn.hutool.core.collection.CollUtil;
//import com.alibaba.fastjson.JSONObject;
//import com.aliyuncs.DefaultAcsClient;
//import com.aliyuncs.IAcsClient;
//import com.aliyuncs.exceptions.ClientException;
//import com.aliyuncs.ons.model.v20190214.*;
//import com.aliyuncs.profile.DefaultProfile;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.util.CollectionUtils;
//
//import javax.annotation.PostConstruct;
//import java.util.Collections;
//import java.util.List;
//
///**
// * 初始化 topic、组
// */
//@Slf4j
////@Component
//public class MqTopicConfig {
//
//    @Autowired
//    private RocketMQConfig rocketMQConfig;
//
//    @Autowired
//    private MyDataConfig myDataConfig;
//
//    @PostConstruct
//    public void init() throws ClientException {
//        // 检查topic与group是否存在，不存在则创建
//        IAcsClient acsClient = new DefaultAcsClient(DefaultProfile.getProfile(rocketMQConfig.getRegionId(), rocketMQConfig.getAccessKey(), rocketMQConfig.getSecretKey()));
//
//        //初始化 组id
//        initGroupId(acsClient);
//
//        //初始化 topic
//        initTopic(acsClient, myDataConfig.getTopicName(), TopicTypeEnum.NORMAL);
//    }
//
//    /**
//     * 初始化组id
//     * @param acsClient
//     * @throws ClientException
//     */
//    public void initGroupId(IAcsClient acsClient) throws ClientException {
//        if(!isExistOfGroupId(acsClient, myDataConfig.getGroupId())){
//            //创建
//            log.info("groupId创建成功：groupId名称：{}", myDataConfig.getGroupId());
//            this.createGroup(acsClient, myDataConfig.getGroupId());
//        }
//    }
//
//    /**
//     * 校验组是否存在
//     * @param acsClient
//     * @param groupId
//     * @return
//     */
//    private boolean isExistOfGroupId(IAcsClient acsClient, String groupId) throws ClientException {
//        // 检查topic与group是否存在，不存在则创建
//        List<OnsGroupListResponse.SubscribeInfoDo> groupList = this.queryGroupList(acsClient, groupId);
//        if (!CollectionUtils.isEmpty(groupList)) {
//            log.info("已存在的组：{}", groupList.get(0).getGroupId());
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 查询组id
//     * @param acsClient
//     * @param groupId
//     * @return
//     * @throws ClientException
//     */
//    private List<OnsGroupListResponse.SubscribeInfoDo> queryGroupList(IAcsClient acsClient, String groupId) throws ClientException {
//        OnsGroupListRequest createRequest = new OnsGroupListRequest();
//        createRequest.setPreventCache(System.currentTimeMillis());
//        createRequest.setInstanceId(rocketMQConfig.getInstanceId());
//        createRequest.setGroupId(groupId);
//        final OnsGroupListResponse acsResponse = acsClient.getAcsResponse(createRequest);
//        final List<OnsGroupListResponse.SubscribeInfoDo> infoDoList = acsResponse.getData();
//        if (CollUtil.isNotEmpty(infoDoList)) {
//            return infoDoList;
//        }
//        log.info("queryGroupList to result:{}", JSONObject.toJSONString(acsResponse));
//        return Collections.emptyList();
//    }
//
//    /**
//     * 初始化topic
//     * @param acsClient
//     * @param topic
//     * @param topicTypeEnum
//     */
//    public void initTopic(IAcsClient acsClient, String topic, TopicTypeEnum topicTypeEnum) throws ClientException {
//        if(!isExistOfTopic(acsClient, topic)){
//            try {
//                this.createTopic(acsClient, topic, topicTypeEnum);
//                log.info("topic创建成功：topic名称：{}", topic);
//            } catch (ClientException e) {
//                log.error("topic创建失败：topic名称：{}", topic);
//            }
//        }
//    }
//
//    /**
//     * 判断topic是否存在
//     * @param acsClient
//     * @param topic
//     * @return
//     * @throws ClientException
//     */
//    private boolean isExistOfTopic(IAcsClient acsClient, String topic) throws ClientException {
//
//        List<OnsTopicListResponse.PublishInfoDo> topicList = this.queryTopicList(acsClient, topic);
//
//        if (!CollectionUtils.isEmpty(topicList)) {
//            log.info("已存在的topic：{}", topicList.get(0).getTopic());
//            return true;
//        }
//        return false;
//    }
//
//    /**
//     * 创建topic
//     * @param acsClient
//     * @param topic
//     * @param topicMsgType
//     * @throws ClientException
//     */
//    private void createTopic(IAcsClient acsClient, String topic, TopicTypeEnum topicMsgType) throws ClientException {
//        OnsTopicCreateRequest createRequest = new OnsTopicCreateRequest();
//        createRequest.setPreventCache(System.currentTimeMillis());
//        createRequest.setInstanceId(rocketMQConfig.getInstanceId());
//        createRequest.setMessageType(topicMsgType.getCode());
//        createRequest.setTopic(topic);
//        createRequest.setRemark("系统自动创建的topic");
//        final OnsTopicCreateResponse acsResponse = acsClient.getAcsResponse(createRequest);
//        log.info("createTopic to result:{}", JSONObject.toJSONString(acsResponse));
//    }
//
//    /**
//     * 查询topic
//     * @param acsClient
//     * @param topic
//     * @return
//     * @throws ClientException
//     */
//    private List<OnsTopicListResponse.PublishInfoDo> queryTopicList(IAcsClient acsClient, String topic) throws ClientException {
//        OnsTopicListRequest createRequest = new OnsTopicListRequest();
//        createRequest.setPreventCache(System.currentTimeMillis());
//        createRequest.setInstanceId(rocketMQConfig.getInstanceId());
//        createRequest.setTopic(topic);
//        final OnsTopicListResponse acsResponse = acsClient.getAcsResponse(createRequest);
//        final List<OnsTopicListResponse.PublishInfoDo> infoDoList = acsResponse.getData();
//        if (CollUtil.isNotEmpty(infoDoList)) {
//            return infoDoList;
//        }
//        log.info("queryTopicList to result:{}", JSONObject.toJSONString(acsResponse));
//        return Collections.emptyList();
//    }
//
//
//    /**
//     * 创建组
//     * @param acsClient
//     * @param groupId
//     * @throws ClientException
//     */
//    private void createGroup(IAcsClient acsClient, String groupId) throws ClientException {
//        OnsGroupCreateRequest createRequest = new OnsGroupCreateRequest();
//        createRequest.setPreventCache(System.currentTimeMillis());
//        createRequest.setInstanceId(rocketMQConfig.getInstanceId());
//        createRequest.setGroupId(groupId);
//        createRequest.setRemark("系统自动创建的groupId");
//        final OnsGroupCreateResponse acsResponse = acsClient.getAcsResponse(createRequest);
//        log.info("createGroup to result:{}", JSONObject.toJSONString(acsResponse));
//    }
//}
