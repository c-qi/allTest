package org.zhire.work.model;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.zhire.work.entity.works.user.ZpUserFollow;

import javax.persistence.Column;
import java.io.Serializable;

@Data
public class ZphdResponse implements Serializable {

    @ApiModelProperty("id")
    @Column(name = "id", updatable = false)
    private Long id;

    private Long worksId;

    private Long ctime;

    private Long worksCommentUserId;
    private Integer worksCommentUserType;
    private String worksVideoContent;
    private String worksWordsContent;

    @ApiModelProperty("用户名称")
    private String username;

    @ApiModelProperty("头像")
    private String head;

    @ApiModelProperty("关注状态")
    ZpUserFollow.FOLLOWTYPE followtype;

}
