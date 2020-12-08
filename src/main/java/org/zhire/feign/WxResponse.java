package org.zhire.feign;

import lombok.Data;

@Data
public class WxResponse {

    /**错误码*/
    private Integer errcode;

    /**错误信息*/
    private String errmsg;
}
