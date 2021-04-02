package org.zhire.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.openservices.ons.api.Message;
import com.aliyun.openservices.ons.api.SendResult;
import com.aliyun.openservices.ons.api.bean.ProducerBean;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.ApiMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.task.TaskExecutor;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zhire.config.MapConfig;
import org.zhire.config.mq.MyDataConfig;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.pojo.User;
import org.zhire.thread.MyDelayQueue;
import org.zhire.thread.QueuePool;
import org.zhire.utils.R;
import org.zhire.utils.SequenceGenerator;
import org.zhire.utils.SignUtils;

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
import java.util.concurrent.*;

@Slf4j
@RestController
@RequestMapping("/users")
public class TestController {

    @Autowired
    private SequenceGenerator sequenceGenerator;

    @Autowired
    @Qualifier("test")
    private QueuePool queuePool;

    @Autowired
    @Qualifier("taskExecutor")
    private TaskExecutor taskExecutor;


    @PostMapping("/insert")
    public JSONObject insert(@RequestBody User user) {
        System.out.println(JSON.toJSONString(user));
        return JSON.parseObject(JSON.toJSONString(user));
    }

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


    @GetMapping(value = "/test23")
    public Map<String, Object> test222() {
        Map<String, Object> map = new HashMap<>();
        ArrayList<User> list = CollUtil.newArrayList();
        User user = new User();
        user.setName("cq");
        user.setManagerId(12345678987654321L);
        list.add(user);
        map.put("list", list);
        System.out.println(JSON.toJSONString(map));
        return map;
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
    // @PostConstruct
    public void initDelayQueueWorker() {
        new Thread(new MyDelayQueue()).start();
        log.info("延迟队列工作者初始化完成");
    }

    /**
     * 异步线程池初始化延迟队列
     */
    @PostConstruct
    public void test2() {
        taskExecutor.execute(new MyDelayQueue());
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


    /**
     * 使用自定义注解获取用户信息
     *
     * @return
     */
    @GetMapping(value = "/getUSer")
    public IOCUser getUser(@org.zhire.annotation.User IOCUser user) {
        return user;
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
        response.setHeader("Content-Disposition", "attachment;filename=" + fileName + ".csv");
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

    private static ThreadFactory threadFactory = new ThreadFactoryBuilder().setNameFormat("线程-%d").build();
    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(
            Runtime.getRuntime().availableProcessors() * 2 >= 2 ? 2 : Runtime.getRuntime().availableProcessors() * 2,
            Runtime.getRuntime().availableProcessors() * 2 >= 2 ? 2 : Runtime.getRuntime().availableProcessors() * 2,
            0L,
            TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(100),
            threadFactory,
            new CustomRejectedExecutionHandler()); // 使用自定义的拒绝策略，队列满了，阻塞等待


    private static class CustomRejectedExecutionHandler implements RejectedExecutionHandler {
        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                System.out.println("放入了队列");
                executor.getQueue().put(r);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws Exception {
        StopWatch watch = new StopWatch();
        watch.start();
        ExcelReader reader = ExcelUtil.getReader("/Users/admin/Documents/获奖用户信息模板.xlsx");
        List<List<Object>> read = reader.read(1, reader.getRowCount());
        // for (List<Object> objects : read) {
        // System.out.println(objects);
//            executor.execute(() -> {
//                System.out.println(Thread.currentThread().getName() + " " + objects.get(0));
//            });
        // }
        read.forEach(l -> {
            String mobile = (String) l.get(0);
            Long level = (Long) l.get(1);
            Long payInfo = (Long) l.get(2);
            System.out.println(mobile);
            System.out.println(level);
            System.out.println(payInfo);
        });
        watch.stop();
        System.out.println("耗时：" + watch.getTotalTimeSeconds());
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

    //@Autowired
    private ProducerBean producer;

    //@Autowired
    private MyDataConfig myDataConfig;

    @RequestMapping("/mq")
    public void t() {
        //循环发送消息
        for (int i = 0; i < 10; i++) {
            String sb = "hello" + i;
            Message msg = new Message(
                    // Message所属的Topic
                    myDataConfig.getTopicName(),
                    // Message Tag 可理解为Gmail中的标签，对消息进行再归类，方便Consumer指定过滤条件在MQ服务器过滤
                    myDataConfig.getTag(),
                    // Message Body 可以是任何二进制形式的数据， MQ不做任何干预
                    // 需要Producer与Consumer协商好一致的序列化和反序列化方式
                    sb.getBytes());
            // 设置代表消息的业务关键属性，请尽可能全局唯一
            // 以方便您在无法正常收到消息情况下，可通过MQ 控制台查询消息并补发
            // 注意：不设置也不会影响消息正常收发
            msg.setKey("first-mq-test");
            // 发送消息，只要不抛异常就是成功
            try {
                SendResult sendResult = producer.send(msg);
                assert sendResult != null;
                System.out.println(sendResult);
            } catch (Exception e) {
                log.info("发送失败:{}", e);
                //出现异常意味着发送失败，为了避免消息丢失，建议缓存该消息然后进行重试。
            }
        }
    }

    @RequestMapping("/mqdeny")
    public void mqdeny() {
        String sb = "这是延时消息";
        try {
            Message msg = new Message(
                    myDataConfig.getPayDelayName(),
                    myDataConfig.getDelayTag(),
                    sb.getBytes());
            // 设置消息需要被投递的时间。单位毫秒（ms），在指定延迟时间（当前时间之后）进行投递，例如消息在 3 秒后投递。
            msg.setStartDeliverTime(System.currentTimeMillis() + 3000);
            SendResult sendResult = producer.send(msg);
            // 同步发送消息，只要不抛异常就是成功。
            if (sendResult != null) {
                log.info("发送消息 Topic:{} msgId:{} message: {}", msg.getTopic(), sendResult.getMessageId(), sb);
            }
        } catch (Exception e) {
            // 消息发送失败，需要进行重试处理，可重新发送这条消息或持久化这条数据进行补偿处理。
            log.error("发送消息 失败. Topic:{} message:{} 异常信息：{}", myDataConfig.getPayDelayName(), sb, e);
        }
    }

    @PostMapping("/check")
    public String check(@RequestParam String method,
                        @RequestParam String appId,
                        @RequestParam String timestamp,
                        @RequestParam String v,
                        @RequestParam String signMethod,
                        @RequestParam String sign,
                        @RequestBody Map<String, String> map,
                        HttpServletRequest servletRequest) {
        String requestBody = null;
        try {
            SignUtils.CheckResult checkResult = SignUtils.checkSign2(servletRequest, "XGXTEST", map);
            if (checkResult.getSuccess()) {
                log.info("checkSign success");
                // 获取body
                requestBody = checkResult.getRequestBody();
            } else {
                log.error("checkSign fail");
            }
        } catch (Exception e) {
            log.error("checkSign error", e);
        }

        return requestBody;

    }

    @Autowired
    private MapConfig mapConfig;

    @GetMapping("/map")
    public String check() {
        Map<String, Integer> map = mapConfig.getSkuMap();
        return JSON.toJSONString(map);
    }


    @PostMapping("/postt")
    public String postt(HttpServletRequest request) {

        Map<String, String[]> map = request.getParameterMap();
        map.forEach((k, v) -> {
            System.out.println(k);
            System.out.println(Arrays.toString(v));
        });
        return "ok";
    }

}
