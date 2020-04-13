package org.zhire.model;

/**
 * @author chendongzhi
 * @date 17:252019/2/27 0027
 * @description 错误码枚举类
 */
public enum ResponseCodeEnum {
    /**
     * 操作成功
     */
    SUCCESS("0000","SUCCESSFULL!"),
    DATA_NOT_FOUND("4004","数据未找到"),
    /**
     * 系统异常，可在9000---9998之间添加详细系统异常码
     */
    SYSTEM_ERROR("9999","SYSTEM ERROR!"),
    /**
     * mysql 相关错误，可在8000---9000添加详细错误码
     */
    MYSQL_ERROR("8000","MYSQL ERROR!"),
    /**
     * mongodb 错误，可在7000-8000之间添加详细错误码
     */
    MONGODB_ERROR("7000","MONGO ERROR!"),
    /**
     * redis相关错误，可在6000----7000之间添加详细错误码
     */
    REDIS_ERROR("6000","REDIS ERROR"),

    /**
     * 传参相关错误，可在1000----2000之间添加详细错误码
     */
    PARAM_LOGIN_NULL_ERROR("1000","登录信息不能为空"),
    PARAM_ACCOUNT_NULL_ERROR("1001","账号不能为空"),
    PARAM_PASSWORD_NULL_ERROR("1002","密码不能为空"),
    PARAM_LOGIN_ERROR("1003","账号密码错误"),
    PARAM_CURRENT_PASSWORD_NULL_ERROR("1004","当前密码不能为空"),
    PARAM_NEW_PASSWORD_NULL_ERROR("1005","新密码不能为空"),
    PARAM_CURRENT_PASSWORD_ERROR("1006","原密码错误"),
    PARAM_VERIFICATIONCODE_NULL_ERROR("1007","验证码不能为空"),
    PARAM_VERIFICATIONCODE_ERROR("1008","验证码错误"),
    PARAM_TRADE_NULL_ERROR("1009","查询行业数据为空"),
    PARAM_FIELD_NULL_ERROR("1010","查询领域数据为空"),
    PARAM_TAXCATEGORY_NULL_ERROR("1011","查询税种数据为空"),
    PARAM_COMMONPHRASES_NULL_ERROR("1012","根据parent查询常用语数据为空"),
    PARAM_VERIFICATIONCODE_SEND_FAIL_ERROR("1013","验证码发送失败"),
    PARAM_VERIFICATIONCODE_SAVE_REDIS_ERROR("1014","验证码存入Redis失败"),
    PARAM_VERIFICATIONCODE_INVALID_ERROR("1015","验证码已失效，请重新获取验证码"),
    PARAM_EXPERT_NAME_NULL_ERROR("1016","专家名称不能为空"),
    PARAM_QUESTIONSORT_NULL_ERROR("1017","问题分类信息不能为空"),
    PARAM_QUESTION_ID_NULL_ERROR("1018","咨询单id不能为空"),
    PARAM_EXPERT_NULL_ERROR("1019","查询专家信息为空"),
    PARAM_NUMBERFORMATEXCEPTION_ERROR("1020","数字格式化异常"),
    PARAM_SMS_SEND_COUNT_UPPER_LIMIT_ERROR("1021","发送次数已达上限，请明天再试"),
    PARAM_NOT_ON_LINE_EXPERT_ERROR("1022","当前没有专家在线"),
    PARAM_GET_EVALUATE_BY_ID_NULL_ERROR("1023","根据咨询单id查询评价信息数据为空"),
    PARAM_EVALUATE_NULL_ERROR("1024","评价信息不能为空"),
    PARAM_EXPERT_ACCOUNT_NULL_ERROR("1025","专家账号不能为空"),
    PARAM_GET_EVALUATE_BY_CONDITION_NULL_ERROR("1026","根据条件查询评价信息数据为空"),
    PARAM_QUESTION_STATUS_NULL_ERROR("1027","咨询状态不能为空"),
    PARAM_GET_QUESTION_BY_CONDITION_NULL_ERROR("1028","根据条件查询咨询单信息为空"),
    PARAM_GET_QUESTION_BY_ID_NULL_ERROR("1029","根据id查询咨询单数据为空"),
    PARAM_EXPERT_ID_NULL_ERROR("1030","专家id不能为空"),
    PARAM_GET_CONSULTEXPERTSERVICETIME_BY_EXPERTID_NULL_ERROR("1031","根据expertId查询ConsultExpertServiceTime 数据为空"),
    PARAM_GET_CONSULTQUESTION_NULL_ERROR("1032","模糊查询ConsultQuestion数据为空"),
    PARAM_UPDATE_PWD_FAIL_ERROR("1033","修改密码失败"),
    PARAM_UPDATE_PWD_RETURN_ZERO_ERROR("1034","没有修改密码"),
    PARAM_ERROR("1035","传入参数错误"),
    PARAM_QUESTION_ID_EXPIRE_ERROR("1036","咨询单号已过期"),
    PARAM_SET_QUESTION_ID_EXPIRE_FAIL_ERROR("1037","设置咨询单号过期失败"),
    PARAM_UPDATE_QUESTION_FAIL_ERROR("1038","修改咨询单失败"),
    PARAM_UPDATE_QUESTION_RETURN_ZERO_ERROR("1039","修改咨询单失败！update:0"),
    PARAM_UPDATE_CONSULT_DATA_REQUEST_AUTH_FAIL_ERROR("1040","修改权限表失败"),
    PARAM_UPDATE_CONSULT_DATA_REQUEST_AUTH_RETURN_ZERO_ERROR("1041","修改权限表失败！update:0"),
    PARAM_GET_QUESTION_BY_STATUS_FAIL_ERROR("1042","查询咨询单失败"),
    PARAM_GET_QUESTION_BY_STATUS_ZERO_ERROR("1043","没有未分配的咨询单数据"),
    PARAM_SIMPLEDATEFORMAT_ERROR("1044","时间格式转换异常"),
    PARAM_NOT_WORKING_HOURS_ERROR("1045","专家非工作时间登录"),
    PARAM_GET_MSG_ERROR("1046","查询聊天记录失败"),
    PARAM_UPDATE_CONSULT_QUESTION_ERROR("1047","更改ConsultQuestion失败"),
    PARAM_SEND_MSG_ERROR("1048","发送消息失败"),
    PARAM_GET_EXPERT_EVALUATE_ERROR("1049","查询专家评分信息失败"),
    PARAM_GET_QUESTION_ERROR("1050","查询咨询表信息失败"),
    PARAM_GET_CONSULT_QUESTION_EXCHANGE_ERROR("1051","查询ConsultQuestionExchange信息失败"),
    PARAM_GET_CONSULT_EXPERT_CERTIFICATE_ERROR("1052","查询ConsultExpertCertificate信息失败"),
    PARAM_GET_MSG_ORDER_ERROR("1053","查询聊天记录排序失败"),
    PARAM_GET_LOGIN_RECORD_ERROR("1054","查询ConsultExpertLoginRecord失败"),
    PARAM_ADD_LOGIN_RECORD_ERROR("1055","插入ConsultExpertLoginRecord失败"),
    PARAM_UPDATE_LOGIN_RECORD_ERROR("1056","更改ConsultExpertLoginRecord失败"),
    PARAM_LOGIN_INVALID_ERROR("1057","专家登录信息已失效"),
    TOKEN_ERROR("1058", "登录信息已过期，请重新登录"),
    LOGIN_INVALID_ERROR("1059", "由于此账号已在其他设备上登录，您的登录状态已失效"),
    GET_EXPERT_ERROR("1060", "查询专家信息失败"),
    GET_USERIMINFO_ERROR("1061", "查询用户信息失败"),
    PARAM_GET_CONSULTQUESTION_ZERO_ERROR("1062","没有此专家咨询问答记录"),
    END_CONSULTQUESTION("1063","本次咨询已结束"),
    GET_EXPERT_BY_ACCOUNT_ERROR("1064","查询专家手机号是否存在失败"),
    GET_COUNT_MSG_ERROR("1065","查询咨询记录数失败"),
    LIMITED_ENTRY("1066","该账户已被限制登录"),
    STOP_SERVICE("1067","该账户已停止服务"),
    PARAM_NULL_ERROR("1068","参数不能为空"),
    EXPERT_ACCOUNT_NULL("1069","专家手机号不存在"),
    GET_CONSULTQUESTION_BY_USERID_OR_EXPERTID_ERROR("1070","根据用户id或者专家id查询咨询单失败"),
    GET_GIFT_RECORD_ERROR("1071", "查询福利领取记录失败"),
    INSERT_GIFT_RECORD_ERROR("1072", "插入福利领取记录失败"),
    CONSULTVIPNOTICE_NULL_ERROR("1073", "预约信息不能为空"),
    ADD_CONSULTVIPNOTICE_ERROR("1074", "新增ConsultVipNotice失败"),
    FEEDBACK_NULL_ERROR("1075", "意见反馈内容不能为空"),
    ADD_FEEDBACK_ERROR("1076", "新增Feedback失败"),
    URL_NULL_ERROR("1077", "url不能为空"),
    GET_SERVICE_PROVIDER_INFO_ERROR("1078", "获取服务商信息失败"),
    BANNER_NULL_ERROR("1079", "焦点图数量不能为空"),
    GET_BANNER_ERROR("1080", "获取焦点图信息失败"),
    GET_VERSION_ERROR("1081", "获取版本号失败"),
    PARSE_EXCEPTION_ERROR("1082", "时间格式转换异常"),
    GET_INFO_BY_ACCOUNT_ERROR("1083", "获取用户会员信息失败"),
    GET_MSG_BY_USER_ID_ERROR("1084", "根据userId分页查询聊天记录失败"),
    GET_USER_EQUITY_COUNT_NULL("1085", "获取会员权益数量为空"),
    UPDATE_USERIMINFO_ERROR("1086", "修改userImInfo失败"),
    ADD_USERCLUE_ERROR("1087", "新增UserClue失败"),
    DISTRIBUTORID_NULL_ERROR("1088", "分支机构id不能为空"),
    CONTENT_NULL_ERROR("1089", "问题描述content不能为空"),
    NOT_EQUITY_ERROR("1090", "权益未开通"),
    GET_DISTRIBUTORID_BY_ACCOUNTID_ERROR("1091", "根据账号id获取渠道信息失败"),
    GET_TOKEN_ERROR("1092", "从融云获取聊天token失败"),
    UPDATE_EQUITY_ERROR("1093", "更改会员权益次数失败"),
    EQUITY_HAVE_RUN_OUT_ERROR("1094", "会员咨询权益数量已用完"),
    EXPERT_NO_ORDERS_ERROR("1095", "该专家不接单"),
    EQUITY_ID_NULL_ERROR("1096", "权益ID不能为空"),
    MEMBER_ID_NULL_ERROR("1097", "会员ID不能为空"),
    EQUITY_USEDCOUNT_NULL_ERROR("1098", "消耗权益数量不能为空单"),
    CHECK_ONLINE_STATUS_ERROR("1099", "获取用户在线状态失败"),
    GET_ACCOUNTINFO_ERROR("1100", "获取accountInfo信息失败"),
    EXPERT_TYPE_NULL("1101", "专家类型不能为空"),
    GET_ANSWER_NUM_ERROR("1102", "查询专家总回答数失败"),
    EXPERT_WORKCODE_NULL("1103", "专家工号不能为空"),
    EXPERT_OFFLINE("1104", "专家已下线，请选择其他在线专家"),
    GET_EXPERT_SHARE_ERROR("1105", "获取共享专家信息失败"),
    GET_EXPERT_SHARE_NULL("1106", "没有查到共享专家信息"),

    PARAM_Get_ClassicsQuestionCollection_NULL("1201","根据条件查询已收藏经典问答信息为空"),
    PARAM_Get_ClassicsQuestionId_NULL("1202","收藏问题id为空"),
    PARAM_Upd_CollectStatus_ERROR("1203","取消收藏失败"),
    PARAM_Get_ClassicsQuestion_Id_NULL("1204","经典问答id为空"),
    PARAM_Get_ClassicsQuestion_NULL("1205","根据条件查询经典问答信息为空"),
    PARAM_Get_OrderList_NULL("1205","查询订单信息为空"),
    PARAM_USER_ID_NULL_ERROR("1206","账号id不能为空"),
    PARAM_GET_USERVIP_ERROR("1207","用户VIP信息为空"),
    PARAM_VIP_NUll_ERROR("1208","用户未开通会员"),
    PARAM_GET_AUTH_NUll_ERROR("1208","取消授权失败"),

    PARAM_SAVE_ERROR("1500","保存失败"),
    PARAM_ACCOUN_ID_NULL_ERROR("1501","用户ID不能为空"),
    PARAM_ACCOUN_NULL_ERROR("1502","用户账号不能为空"),
    PARAM_FILE_NULL_ERROR("1503","文件数据不能为空"),
    PARAM_FILE_UPLODE_ERROR("1504","文件上传异常"),
    PARAM_NAME_NULL_ERROR("1505","名称不能为空"),
    PARAM_PHONE_NULL_ERROR("1506","联系电话不能为空"),
    PARAM_CHANNEL_FROM_NULL_ERROR("1507","咨询来源不能为空"),
    PARAM_FROM_NAME_NULL_ERROR("1508","来源名称不能为空"),
    PARAM_FROM_ADDRESS_NULL_ERROR("1509","来源跳转地址不能为空"),
    PARAM_FROM_CONTENT_NULL_ERROR("1510","来源内容不能为空"),
    PARAM_COMPANY_LOCATION_NULL_ERROR("1511","企业地址不能为空"),
    PARAM_USER_CITY_NULL_ERROR("1512","提问者所在地址不能为空"),
    PARAM_QUESTION_DESC_NULL_ERROR("1513","问题描述不能为空"),
    PARAM_EXPEERT_ID_NULL_ERROR("1514","专家ID不能为空"),
    PARAM_AUTH_TYPE_NULL_ERROR("1515","授权类型不能为空"),
    PARAM_EXPEERT_FROM_ID_NULL_ERROR("1516","转单前专家ID不能为空"),
    PARAM_EXPEERT_TO_ID_NULL_ERROR("1517","转单后专家ID不能为空"),
    PARAM_AUTH_ID_NULL_ERROR("1518","授权ID不能为空"),
    PARAM_QUESTUON_ID_NULL_ERROR("1519","咨询ID不能为空"),
    PARAM_EVALAUTE_CONTENT_NULL_ERROR("1519","评价内容不能为空"),
    PARAM_EVALAUTE_FLAG_NULL_ERROR("1520","评价标签不能为空"),
    PARAM_EVALAUTE_SCORE_NULL_ERROR("1521","评分不能为空"),
    PARAM_EXPEERT_NAME_NULL_ERROR("1522","专家名称不能为空"),
    PARAM_EXPEERT_ACCOUNT_NULL_ERROR("1523","专家账号不能为空"),
    PARAM_ID_NULL_ERROR("1524","ID不能为空"),
    PARAM_TOKEN_NULL_ERROR("1525","TOKEN不能为空"),
    PARAM_PRODUCT_ID_NULL_ERROR("1526","产品不能为空"),
    PARAM_DISTRIBUTOR_ID_NULL_ERROR("1527","分支机构ID不能为空"),
    PARAM_USER_COMPANY_NULL_ERROR("1528","请填写用户企业"),
    PARAM_USER_TYPE_NULL_ERROR("1529","用户类型不能为空"),
    PARAM_COMPANYTRADE_ID_NULL_ERROR("1530","请填写用户公司行业"),
    PARAM_EXPERTOFDISTRIBURTORID_NULL_ERROR("1531","该分支机构下没有专家"),
    LOGIN_DISTRIBUTORID_ERROR("1532","专家登录渠道不正确"),
    PARAM_AUTH_TOKEN_ERROR("1533","授权key不能为空"),
    PARAM_AUTH_URL_ERROR("1534","授权连接不能为空"),
    PARAM_QUESTION_ID_ERROR("1535","questionId不能为空"),
    PARAM_SAVE_AUTH_ERROR("1536","保存授权连接失败"),
    PARAM_CONSULT_AUTH_ERROR("1537","保存授权连接重复"),

    PARAM_UPDATE_EQUITY_ERROR("1601","更新用户权益失败"),
    PARAM_SAVE_INFO_ERROR("1602","保存咨询来源失败"),
    PARAM_SAVE_QUETION_ERROR("1603","保存咨询数据失败"),
    PARAM_QUERY_EQUITY_ERROR("1604","查询用户权益失败"),
    PARAM_DIS_EXPEERT_ERROR("1605","分配专家失败"),
    PARAM_AUTH_ERROR("1606","分配专家失败"),
    PARAM_WORK_TIME_ERROR("1607","不在工作时间"),
    PARAM_EVALUATE_EXIST_ERROR("1608","咨询单已评价"),
    PARAM_UESTION_NOT_EXIST_ERROR("1609","咨询单不存在"),
    PARAM_DISTRIBUTOR_ID_ERROR("1609","分支机构ID格式错误"),
    PARAM_END_QUESTION_ERROR("1610","咨询单已结束"),
    PARAM_USER_ID_NOT_EXIST_ERROR("1611","用户ID不存在"),
    PARAM_USER_NAME_NOT_EXIST_ERROR("1612","用户名称不存在"),
    PARAM_SOURCR_ID_NOT_EXIST_ERROR("1613","渠道ID不存在"),
    PARAM_PRODUCT_STATUS_EXIST_ERROR("1614","未开通专家服务"),
    PARAM_EXPERT_NOT_EXIST_ERROR("1615","未配置专家信息"),
    PARAM_ACCOUNT_NOT_EXIST_ERROR("1616","用户账号不存在"),
    PARAM_DISTRIBUTOR_NOT_EXIST_ERROR("1617","分支机构信息未找到"),
    PARAM_QUESTION_NOT_EXIST_ERROR("1618","咨询单不存在"),
    PARAM_QUESTION_EXIST_ERROR("1619","有正在咨询中的单据，请勿重复提交"),
    PARAM_USER_TYPE_ERROR("1529","用户类型格式错误"),
    PARAM_GROUP_CREATE_ERROR("1700","群组创建失败"),



    PARAM_COLLECT_STATUS_ERROR("2001","收藏失败"),
    PARAM_CANCLE_COLLECTION_ERROR("2001","取消收藏失败"),
    PARAM_GET_CLASSICS_QUESTION_ERROR("2002","查询经典问答列表失败"),
    PARAM_GET_CLASSICS_QUESTION_BY_ID_ERROR("2003","根据id查询经典问答详情失败"),
    PARAM_CLASSICS_QUESTION_ID_NULL_ERROR("2004","收藏问题id不能为空"),



    PARAM_TYPE_NULL_ERROR("2101","服务项类型不能为空"),
    GET_SERVICEINFO_ERROR("2102","查询首页服务项失败"),
    ;



    /**
     * 错误码
     */
    private final String code;
    /**
     * 错误描述
     */
    private final String message;
    ResponseCodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
