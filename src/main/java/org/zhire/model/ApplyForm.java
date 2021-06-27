package org.zhire.model;

import lombok.Data;

/**
 * 应聘请求实体
 */
@Data
public class ApplyForm {
    /**
     * 职位id
     */
    private Long job_id;
    /**
     * 用户id
     */
    private Long user_id;
    /**
     * 用户姓名
     */
    private String user_name;
    /**
     * 职位名称
     */
    private String job_title;
    /**
     * 简历id
     */
    private Long res_id;
    /**
     * hrid
     */
    private Long hr_id;
    /**
     * 个人介绍
     */
    private String user_info;
    /**
     * 是否是敏感词
     */
    private boolean words_status;
}
