package org.zhire.work.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ZpWorksLikeResponse   {

    @ApiModelProperty("用户头像")
    private String head;

    @ApiModelProperty("用户名称")
    private String username;


}
