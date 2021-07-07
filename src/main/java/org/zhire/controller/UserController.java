package org.zhire.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.zhire.config.MyException;
import org.zhire.model.ApplyForm;
import org.zhire.model.UserDTO;
import org.zhire.pojo.User;
import org.zhire.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/findUser/{name}")
    public List<User> findUser(@PathVariable String name) {
        List<User> userList = userService.findName(name);
        return userList;
    }

    @PostMapping("/findUser2")
    public String findUser2(String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser3")
    public String findUser3(@RequestParam String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser4")
    public String findUser4(@RequestBody(required = false) String name) {
        System.out.println(name);
        return name;
    }

    @PostMapping("/findUser5")
    public String findUser4(@RequestBody Object request) {
        System.out.println(request);
        return request + "";
    }

    @RequestMapping("/findAll")
    public List<User> findAll() {
        List<User> userList = userService.findAll();
        return userList;
    }

    @RequestMapping("/login")
    public String login(String name, String pass, HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = userService.login(name, pass);
        if (user == null) {
            return "账户名或密码错误！";
        } else {
            // request.getRequestDispatcher("/admin/main.html").forward(request, response);
            request.getSession().setAttribute("loginUser", user);
            response.sendRedirect("/admin/main.html");
        }
        return "ok";
    }

    @RequestMapping("/findTest")
    public UserDTO findTest(@Valid @RequestBody UserDTO userDTO, BindingResult bindingResult) {
        System.out.println(JSON.toJSONString(userDTO));
        if (bindingResult.hasErrors())
            throw new MyException(-1, bindingResult.getAllErrors().get(0).getDefaultMessage());
//            throw new RuntimeException("缺少必传字段");
        UserDTO u = new UserDTO()
                .setName("cq")
                .setPassword("23424");
        return u;
    }

    @RequestMapping("/findListDTO")
    public List<UserDTO> findListDTO(@Valid @RequestBody UserDTO userDTO) {
        System.out.println(JSON.toJSONString(userDTO));
//        if (bindingResult.hasErrors())
//            throw new MyException(99999, "缺少必传字段");
        return userService.findListDTO();
    }

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

    @GetMapping("/dojob2")
    @CrossOrigin
    public JSONObject dojob2(@RequestParam(required = false) String content) {
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

    @RequestMapping("/applyFor.json")
    @CrossOrigin
    public boolean applyFor(@RequestParam Long job_id,
                            @RequestParam Long user_id,
                            @RequestParam String user_name,
                            @RequestParam String job_title,
                            @RequestParam Long res_id,
                            @RequestParam Long hr_id,
                            @RequestParam String user_info) {
        System.out.println("jobId:" + job_id);
        System.out.println("user_id:" + user_id);
        System.out.println("user_name:" + user_name);
        System.out.println("job_title:" + job_title);
        System.out.println("res_id:" + res_id);
        System.out.println("hr_id:" + hr_id);
        System.out.println("user_info:" + user_info);
        return true;
    }

    @RequestMapping("/findUserSendResume.json")
    @CrossOrigin
    public JSONObject findUserSendResume(@RequestParam(required = false) Integer status) {
        System.out.println("Status:" + status);
        String s = "{\n" +
                "    \"flag\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"hasResume\": true,\n" +
                "            \"createtime\": \"2021-06-11 17:05:57\",\n" +
                "            \"comp_name\": \"南风科技001\",\n" +
                "            \"statusList\": [\n" +
                "                {\n" +
                "                    \"createtime\": 1623411551000,\n" +
                "                    \"resume_status\": 3\n" +
                "                },\n" +
                "                {\n" +
                "                    \"createtime\": 1623402357000,\n" +
                "                    \"resume_status\": 1\n" +
                "                }\n" +
                "            ],\n" +
                "            \"job_title\": \"软件测试开发工程师1-1\",\n" +
                "            \"job_ordertime\": \"20190420001000\",\n" +
                "            \"job_salary\": \"14-28万\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return JSONObject.parseObject(s);
    }

    /**
     * 根据简历ID查询简历
     *
     * @param resId
     * @return
     * @throws
     */
    @RequestMapping("/findResumeByResId.json")
    @CrossOrigin
    public JSONObject findResumeByResId(Long resId) {
        String s = "{\n" +
                "    \"flag\": 1,\n" +
                "    \"data\": {\n" +
                "        \"user_id\": 51073535,\n" +
                "        \"res_id\": 24076915,\n" +
                "        \"res_edulevel\": \"020\",\n" +
                "        \"res_sex\": \"女\",\n" +
                "        \"res_dq\": \"210030050\",\n" +
                "        \"res_workyear\": 2021,\n" +
                "        \"res_caption\": \"中文简历_20210602\",\n" +
                "        \"c_photo\": \"5f8fa1445ab7a2158babe2a204u.png\",\n" +
                "        \"res_name\": \"你在\",\n" +
                "        \"res_birth_year_age\": 21,\n" +
                "        \"res_edulevel_name\": \"MBA/EMBA\",\n" +
                "        \"res_workyear_age\": 0,\n" +
                "        \"res_dq_name\": \"鞍山-台安县\",\n" +
                "        \"res_title\": \"\",\n" +
                "        \"res_company\": \"\"\n" +
                "    }\n" +
                "}";
        return JSONObject.parseObject(s);
    }


    /**
     * 根据用户ID查询简历列表
     *
     * @param userId
     * @return
     */
    @CrossOrigin
    @RequestMapping("/findResumeByUserId.json")
    public JSONObject findResumeByUserId(@RequestParam(required = false) Long userId) {
        System.out.println("userId " + userId);
        String s = "{\n" +
                "    \"flag\": 1,\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            \"user_id\": 51073535,\n" +
                "            \"res_id\": 24076915,\n" +
                "            \"res_edulevel\": \"020\",\n" +
                "            \"res_sex\": \"女\",\n" +
                "            \"res_dq\": \"210030050\",\n" +
                "            \"res_workyear\": 2021,\n" +
                "            \"res_caption\": \"中文简历_20210602\",\n" +
                "            \"c_photo\": \"5f8fa1445ab7a2158babe2a204u.png\",\n" +
                "            \"res_name\": \"你在\",\n" +
                "            \"res_birth_year_age\": 21,\n" +
                "            \"res_edulevel_name\": \"MBA/EMBA\",\n" +
                "            \"res_workyear_age\": 0,\n" +
                "            \"res_dq_name\": \"鞍山-台安县\",\n" +
                "            \"res_title\": \"\",\n" +
                "            \"res_company\": \"\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";
        return JSONObject.parseObject(s);
    }


}
