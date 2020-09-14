package org.zhire.controller;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.pojo.User;
import org.zhire.thread.MyDelayQueue;
import org.zhire.thread.QueuePool;
import org.zhire.utils.R;
import org.zhire.utils.SequenceGenerator;

import javax.annotation.PostConstruct;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    @Qualifier("test")
    private QueuePool queuePool;

    @GetMapping(value = "/test")
    public User test() {
        System.out.println(sequenceGenerator.generate(11));
        System.out.println(sequenceGenerator.generate(11));
        System.out.println(sequenceGenerator.generate(11));
        System.out.println(sequenceGenerator.generate(11));
        System.out.println(sequenceGenerator.generate(11));
        System.out.println(sequenceGenerator.generate(11));
        return null;
    }

    /**
     * 向延迟队列添加数据
     *
     * @return
     */
    @GetMapping(value = "/delayQueueTest")
    public User delayQueueTest() {
        Map map = new HashMap<>();
        map.put("name", "cq");
        map.put("age", 11);
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(1, TimeUnit.SECONDS, map));
        MyDelayQueue.DELAY_QUEUE.add(new MyDelayQueue(10, TimeUnit.SECONDS, map));
        return null;
    }


    /**
     * 初始化延迟队列
     */
    @PostConstruct
    public void initDelayQueueWorker() {
        new Thread(new MyDelayQueue()).start();
        log.info("延迟队列工作者初始化完成");
    }


    /**
     * 向阻塞队列里添加数据
     *
     * @return
     */
    @GetMapping(value = "/blockingQueueTest")
    public void BlockingQueueTest() throws Exception {
        for (int i = 0; i < 100; i++) {
            final int a = i;
            queuePool.execute(i + "", () -> {
                System.out.println(Thread.currentThread().getName() + " " + a);
            });
        }
    }


    @RequestMapping("/login")
    public String login(String name, String pwd, HttpServletRequest request) {
        HttpSession session = request.getSession();

        if (name.equals("test") && pwd.equals("test123")) {
            session.setAttribute("user", name);
            return "登录成功";
        } else {
            return "用户名或密码错误!";
        }
    }

    @RequestMapping("/s")
    public String testwork() {
        String s = "{" +
                "    \"name\": \"陈琦\"" +
                "}";
        // String s = "{'name':'陈琦'}";
        return s;
    }

    @RequestMapping("/j")
    public JSONObject j() {
        JSONObject j = new JSONObject();
        j.put("name", "陈琦");
        return j;
    }

    @RequestMapping("/js")
    public String js() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("age", "chernww");
        Object parse = JSON.parse(String.valueOf(jsonObject));
        //String s ="{\"key\":\"陈琦\"}";
        System.out.println(parse);
        String s1 = JSON.toJSONString(parse);
        System.out.println(s1);
        return s1;
    }

    @RequestMapping("/jss")
    public JSONObject js(@RequestBody JSONObject jsonObject) {
        System.out.println(jsonObject);
        return jsonObject;
    }

    @RequestMapping("/date")
    public Date date(@RequestParam Date date) {
        System.out.println(date);
        return date;
    }

    @GetMapping(value = "/outPutUsers")
    public void outPutUsers(HttpServletResponse response) {
        List<IOCUser> list = new ArrayList<>();
        list.add(new IOCUser(1, "1231", "奥", System.currentTimeMillis(), IOCUser.STATUS.Default));
        list.add(new IOCUser(2, "3123", "rwe奥", System.currentTimeMillis(), IOCUser.STATUS.NO));

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("id", "id");
        writer.addHeaderAlias("name", "姓名");
        writer.addHeaderAlias("address", "地址");
        writer.addHeaderAlias("ctime", "创建");
        writer.addHeaderAlias("status", "状态");

        // 合并单元格后的标题行，使用默认标题样式
        //  writer.merge(2, "申请人员信息");
        // 只导出上面设置别名的字段
        writer.setOnlyAlias(true);
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(list, true);

        // out为OutputStream，需要写出到的目标流
        // response为HttpServletResponse对象
        response.setContentType("application/vnd.ms-excel;charset=utf-8");

        // test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        String fileName = "user";
        try {
            fileName = URLEncoder.encode("用户信息", "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".xls");
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);
        // return Response.ok("");
    }

    @ApiMethod(description = "批量导入用户")
    @PostMapping(value = "/importUsers")
    public R importUsers(MultipartFile file) {
        if (file.isEmpty()) {
            throw new RuntimeException("file is null");
        }
        InputStream inputStream = null;
        try {
            inputStream = file.getInputStream();
        } catch (Exception e) {
            log.info("文件流读取失败：{}", e);
        }
        ExcelReader excelReader = ExcelUtil.getReader(inputStream);
        List<List<Object>> excels = excelReader.read(1, excelReader.getRowCount());
        log.info("读取到的数据：{}", JSON.toJSONString(excels));
        return R.ok();
    }

    public static void main(String[] args) {
        ExcelReader reader = ExcelUtil.getReader("/Users/admin/Downloads/用户信息模板.xls");
        List<List<Object>> read = reader.read(1, reader.getRowCount());
        for (List<Object> objects : read) {
            System.out.println(objects);
            System.out.println(objects.get(0));
        }
        System.out.println(read);
    }


    @PostMapping(value = "/test")
    public User testUser(@RequestHeader(value = "id", required = false) String id, @RequestBody User[] user) {
        System.out.println(id);
        if (null == id) {
            System.out.println("nulllllll");
        }
        for (User user1 : user) {
            System.out.println(user1.toString());
        }
        return new User();
    }

}
