package org.zhire.work.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zhire.work.entity.works.user.ZpUserFollow;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class ZpFollowResponse implements Serializable {

    @ApiModelProperty("id")
    @Column(name = "id", updatable = false)
    private Long id;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("头像")
    private String head = null;

    @ApiModelProperty("用户微信昵称")
    private String weixinNickname;

    @ApiModelProperty("用户生日")
    private Long birthday;

    @ApiModelProperty("年龄")
    private Long age;

    @ApiModelProperty("省")
    private String province = null;

    @ApiModelProperty("市")
    private String city = null;

    @ApiModelProperty("区")
    private String area = null;

    @ApiModelProperty("关注状态")
    ZpUserFollow.FOLLOWTYPE followtype;

}
