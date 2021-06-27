package org.zhire.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhire.model.UserDTO;
import org.zhire.pojo.User;
import org.zhire.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private UserService userService;


    @GetMapping("/dojob")
    @CrossOrigin
    public List<User> dojob(@RequestParam String content, @RequestParam Long hr_id) {
        System.out.println(content);
        System.out.println(hr_id);
//        userService.dojob(name);
        User user = new User();
        user.setId(1L);
        user.setName("cq");
        List<User> users = Lists.newArrayList();
        users.add(user);
        return users;
    }

    @GetMapping("/findJob.json")
//    @CrossOrigin
    public JSONObject dojob2(@RequestParam(required = false) String userId,
                             @RequestParam(required = false) String content) {
        System.out.println(content);
//        userService.dojob(name);
        User user = new User();
        user.setId(1L);
        user.setName("cq");
        List<User> users = Lists.newArrayList();
        users.add(user);
        String s = "{\n" +
                "  \"flag\": 1,\n" +
                "  \"data\": [\n" +
                "    {\n" +
                "      \"comp_name\": \"南风科技001\",\n" +
                "      \"hr_id\": 51005637,\n" +
                "      \"job_title\": \"软件测试开发工程师1-1\",\n" +
                "      \"job_ordertime\": \"20190420001000\",\n" +
                "      \"job_salary\": \"14-28万\",\n" +
                "      \"job_dq_name\": \"北京-朝阳区\",\n" +
                "      \"job_id\": 1967166,\n" +
                "      \"hasResume\": true\n" +
                "    },\n" +
                "    {\n" +
                "      \"comp_name\": \"哈哈科技001\",\n" +
                "      \"hr_id\": 51005637,\n" +
                "      \"job_title\": \"JAVA开发工程师\",\n" +
                "      \"job_dq_name\": \"北京-通州区\",\n" +
                "      \"job_ordertime\": \"20190420001000\",\n" +
                "      \"job_salary\": \"143-283万\",\n" +
                "      \"job_id\": 19671696,\n" +
                "      \"hasResume\": false\n" +
                "    },\n" +
                "    {\n" +
                "      \"comp_name\": \"哈哈科技r001\",\n" +
                "      \"hr_id\": 510052637,\n" +
                "      \"job_title\": \"JAVA234开发工程师\",\n" +
                "      \"job_dq_name\": \"北京rqw-通州区\",\n" +
                "      \"job_ordertime\": \"20qwer190420001000\",\n" +
                "      \"job_salary\": \"143q-283万\",\n" +
                "      \"job_id\": 196711696,\n" +
                "      \"hasResume\": true\n" +
                "    }\n" +
                "  ]\n" +
                "}";
        JSONObject jsonObject = JSONObject.parseObject(s);
        return jsonObject;
    }

    /**
     * @param searchContent
     * @return
     */
    @RequestMapping("/findJobByHr.json")
    @CrossOrigin
    public JSONObject findJobByHr(@RequestParam(required = false) String searchContent) {
        String s = "{\n" +
                "    \"flag\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"job_id\": 1967166,\n" +
                "            \"user_id\": 51073535,\n" +
                "            \"createtime\": \"2021-06-11 17:05:57.0\",\n" +
                "            \"res_id\": 24076915,\n" +
                "            \"res_edulevel\": \"020\",\n" +
                "            \"res_sex\": \"女\",\n" +
                "            \"res_dq\": \"210030050\",\n" +
                "            \"res_workyear\": 2021,\n" +
                "            \"res_caption\": \"中文简历\",\n" +
                "            \"c_photo\": \"99.png\",\n" +
                "            \"res_name\": \"你在\",\n" +
                "            \"res_birth_year_age\": 21,\n" +
                "            \"res_edulevel_name\": \"MBA/EMBA\",\n" +
                "            \"res_workyear_age\": 0,\n" +
                "            \"res_dq_name\": \"鞍山-台安县\",\n" +
                "            \"res_title\": \"\",\n" +
                "            \"res_company\": \"\",\n" +
                "            \"job_title\": \"软件测试开发工程师1-1\",\n" +
                "            \"hr_id\": 51005637\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return JSONObject.parseObject(s);
    }

    /**
     * hr更新简历状态
     *
     * @return
     */
    @CrossOrigin
    @RequestMapping("/updateResumeStatusByHr.json")
    public Boolean updateResumeStatusByHr(Long userId,
                                          Long hrId,
                                          Long jobId,
                                          Integer status,
                                          @RequestParam(required = false) String content) {
        System.out.println(userId);
        System.out.println(hrId);
        System.out.println(jobId);
        System.out.println(status);
        System.out.println(content);
        return true;
    }

}
