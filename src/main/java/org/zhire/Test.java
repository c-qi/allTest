package org.zhire;

import cn.hutool.bloomfilter.bitMap.BitMap;
import cn.hutool.bloomfilter.bitMap.IntMap;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ZipUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;
import lombok.extern.slf4j.Slf4j;
import org.Person;
import org.apache.kafka.common.protocol.types.Field;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.pojo.User;
import org.zhire.pojo.UserCopy;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.lang.ref.*;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.net.URL;
import java.nio.charset.Charset;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Supplier;

import static java.util.Objects.isNull;

//import cn.hutool.core.io.file.FileReader;


/**
 * @Author: chenqi
 * @Date: 2019.3.6 14:19
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
//        String logStr = new StringBuilder()
//                .append(" spreadWorkbookTagId:").append("aaa")
//                .append(" version:").append("bbb")
//                .toString();
//        System.out.println(logStr);
//
//       String sl = "0.09";
//        BigDecimal bigDecimal = BigDecimal.valueOf(Double.parseDouble(sl));
//        System.out.println(bigDecimal);

//        Set s = new HashSet();
//        s.add(1);
//        s.add(2);
//        s.add(3);
//        String ss = "[";
//        for (Object o : s) {
//            ss += o.toString() + ",";
//        }
//        String s1 = ss.substring(0, ss.lastIndexOf(","))+"]";
//        System.out.println(ss);
//        System.out.println(s1);
//        long[] param = new long[10];
//        param[0]=1;
//        param[1]=2;
//        param[2]=3;
//
//        for (long s : param) {
//            System.out.println(s);
//        }
//     System.out.println(param);
//        JSONArray array = JSON.parseArray(s);
//        for (Object o : array) {
//
//
//        }
//
//        System.out.println(array);

//        Date date = new Date();
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        System.out.println(sdf.format(date));
//
//        int maxCurrentMonthDay = 0;
//        Calendar calendar = Calendar.getInstance();
//        System.out.println("当前时间: " + sdf.format(calendar.getTime()));
//
//
//        Calendar calendar5 = Calendar.getInstance();
//        maxCurrentMonthDay = calendar5.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar5.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
//        calendar5.set(Calendar.DAY_OF_MONTH, 1);
//        System.out.println("上月第一天: " + sdf.format(calendar5.getTime()));
//        System.out.println(getLastMonthFirstDay());
//        System.out.println(getLastMonthLastDay());
//        Calendar calendar6 = Calendar.getInstance();
//        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar6.add(Calendar.DAY_OF_MONTH, -maxCurrentMonthDay);
//        maxCurrentMonthDay = calendar6.getActualMaximum(Calendar.DAY_OF_MONTH);
//        calendar6.set(Calendar.DAY_OF_MONTH, maxCurrentMonthDay);
//        System.out.println("上月最后一天: " + sdf.format(calendar6.getTime()));

//        Calendar c = Calendar.getInstance();
//        c.add(Calendar.MONTH, 0);
//        c.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
//        System.err.println(c.getTime());//这个就是本月的第一天；
//        System.out.println(sdf.format(c.getTime()));
//
//        Calendar c1 = Calendar.getInstance();
//        c1.add(Calendar.MONTH, 0);
//        c1.set(Calendar.DAY_OF_MONTH, 1);//设置为1号,当前日期既为本月第一天 
//        c1.add(Calendar.DATE, -1);//设置上个月的最后一天
//        System.err.println(c1.getTime());//这个就是上月的最后一天；  
//        System.out.println(sdf.format(c1.getTime()));
//        LocalDate now = LocalDate.now();
//        System.out.println(now.getDayOfMonth());
//        System.out.println(now.getMonthValue());
//        System.out.println(now.getYear());
//        System.out.println(LocalDate.now().toString());

    }

    @org.junit.Test
    public void tetstss() {
        int total = 151;
        if (total > 50) {
            // 如果用户的订单数量超过1页
            int flag = total % 50 == 0 ? (total / 50) : (total / 50) + 1;
            for (int j = 2; j <= flag; j++) {
                System.out.println(j);
            }
        }
    }


    /**
     * json处理
     */
    @org.junit.Test
    public void testyuan12() {
        String s = "{\"msg\":\"success\",\"code\":0,\"data\":[{\"sourceId\":\"110108008\",\"unionid\":\"o_v6RwdDMZs81GJ5N3Hav0Ebuec4\",\"phone\":\"18888663399\",\"userType\":\"C\",\"userId\":36702},{\"sourceId\":\"110108008\",\"unionid\":\"o_v6RweLunGLMTT93TORkxHJQetU\",\"phone\":\"17134025278\",\"userType\":\"C\",\"userId\":36757},{\"sourceId\":\"110108008\",\"unionid\":\"o_v6RwQNgbXbOC01QaO-p0y2_fl0\",\"phone\":\"18500369176\",\"userType\":\"C\",\"userId\":27390,\"email\":\"18500369176@qw.om\"},{\"sourceId\":\"110108008\",\"unionid\":\"o_v6RweLunGLMTT93TORkxHJQetU\",\"phone\":\"17135192845\",\"userType\":\"P\",\"userId\":1000000246}]}";
        JSONObject jsonObject = JSON.parseObject(s);
        JSONArray array = (JSONArray) jsonObject.get("data");
        for (int i = 0; i < array.size(); i++) {
            System.out.println(array.get(i));
            System.out.println();
            JSONObject json = (JSONObject) array.get(i);
        }
    }

    /**
     * json处理
     */
    @org.junit.Test
    public void testyuan1() {
        String s = "{\"status\":false,\"msg\":\"\\u60a8\\u8bf7\\u6c42\\u7684\\u9875\\u9762\\u4e0d\\u5b58\\u5728\\uff01\"}";
        JSONObject jsonObject = JSON.parseObject(s);
        System.out.println(jsonObject);
    }

    /**
     * json处理
     */
    @org.junit.Test
    public void dssd() {
        String s = " {\"key\":\"232\"}";
        JSONObject j = (JSONObject) JSONObject.parse(s);
        boolean b = null == "" ? true : false;
        System.out.println(b);
    }

    /**
     * 分转元
     */
    @org.junit.Test
    public void testyuan() {
        System.out.println(BigDecimal.valueOf(Long.valueOf(1000)).divide(new BigDecimal(100)).toString());
    }


    @org.junit.Test
    public void testJson() {
        String arr = "{depts:}";

    }

    @org.junit.Test
    public void testu() {
        List<User> list = new ArrayList();
        list.add(new User("zs", "111"));
        list.add(new User("ww", "222"));
        list.add(new User("zl", "333"));

        for (User user : list) {
            user.setPassword("zs");
        }
        list.stream().forEach(l -> {
            System.out.println(l);
        });
    }


    @org.junit.Test
    public void wqerwe() {
        boolean is = false;
        if (!is) {
            System.out.println("111111");
        }
    }


    @org.junit.Test
    public void testu333() {
        Map map = new HashMap();
        map.put("name", "chenqi");
    }

    @org.junit.Test
    public void testu3eee33() throws ParseException {
//        User user = new User();
//        User user1 = new User();
//        System.out.println(user == user1);
//        System.out.println(user.equals(user1));
//        System.out.println(user.hashCode());
//        System.out.println(user1.hashCode());
//        System.out.println(user.hashCode() == user1.hashCode());

//        int a = 1 << 1;
//        int b = 1 << 2;
//        int c = 1 << 3;
//        int d = 1 << 4;
//        System.out.println(a);
//        System.out.println(b);
//        System.out.println(c);
//        System.out.println(d);

        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX", Locale.US);
        String createTime = "2019-07-09T06:41:08.000+0000";
        Date into = inputFormat.parse(createTime);
        String format = df.format(into);
        System.out.println(format);
    }

    @org.junit.Test
    public void test11111() {
        //  double a = 2.6 + 3.2 + 2.8 + 2.5 + 2.6 + 2.9 + 3 + 3.7 + 2.5 + 6 + 11.2 + 4.6 + 3.1 + 2.9 + 3.2 + 3.7 + 3.8 + 3.5 + 3.8;
        // System.out.println(a);
        Set<String> set = new HashSet();
        set.add("");
        set.add("112");
        set.add("13312");
        set.add("");
        for (String o : set) {
            System.out.println(o);
            try {
                if (StringUtils.isEmpty(o)) {
                    System.out.println(1111);
                }
                int as = 1 / 0;
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        System.out.println("1111111111111111111111");
    }


    @org.junit.Test
    public void duan() {
        String longUrl = "要转换的长链接";
        // 测试生成1千万个短链接 看是否有重复
//        Set set = new HashSet();
//        for (int i = 0; i < 10000000; i++) {
//            StringBuilder builder = dotest();
//            set.add(builder);
//        }
//        System.out.println(set.size());
        StringBuilder shortUrl = dotest();
        // if... 查库里面是否存在这个短链接 如果存在重新生成一个
        // else... 返回页面
        // return "https://qi.rs/" + shortUrl;
    }

    StringBuilder dotest() {
        String[] chars = new String[]{"a", "b", "c", "d", "e", "f", "g", "h",
                "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t",
                "u", "v", "w", "x", "y", "z", "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H",
                "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
                "U", "V", "W", "X", "Y", "Z"};

        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        StringBuilder shortUrl = new StringBuilder();

        for (int i = 0; i < 7; i++) {
            int a = random.nextInt(chars.length);
            shortUrl = sb.append(chars[a]);
        }
        System.out.println(shortUrl);
        return shortUrl;

    }

    @org.junit.Test
    public void dddddd() {
//        for (int i = 0; i < 1000; i++) {
//            System.out.println(i);
//        }

        try {
            int a = 1 / 2;
            System.out.println(a);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("哈哈哈");
        }

    }

    @org.junit.Test
    public void generateWord() {
        String[] beforeShuffle = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N",
                "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z", "a", "b", "c", "d", "e", "f", "g", "h", "i",
                "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};

        //for (int i = 0; i < 1000; i++) {
        List list = Arrays.asList(beforeShuffle);
        //Collections.shuffle(list);
        //      StringBuilder sb = new StringBuilder();
//            for (int j = 0; j < list.size(); j++) {
//            sb.append(list.get(j));
//        }
//            String afterShuffle = sb.toString();
//            String result = afterShuffle.substring(5, 10);
        for (Object o : list) {
            System.out.println(o + "CQ@live.cn;");

        }
        //}

    }


    @org.junit.Test
    public void generateWordwww() throws IOException {
        // 150
        BufferedReader br = new BufferedReader(new FileReader("D:\\work\\ideawork\\myBlog\\src\\main\\resources\\xxx.txt"));
        BufferedWriter bw = new BufferedWriter(new FileWriter("yyy.txt"));
        for (int i = 20000; i < 21000; i++) {
            bw.write(br.readLine());
            bw.write("\n");
        }
        bw.close();
    }

    @org.junit.Test
    public void qw4eqweq() throws IOException {
        String s = "{\"name\":\"\\x\"}";

        Object parse = JSON.parse(s);
        System.out.println(parse);

    }

    @org.junit.Test
    public void testMap() {
        String s = "{\n" +
                "  \"serialNum\": \"4b98790e-86ef-4864-92d4-2667b305d00d\",\n" +
                "  \"port\": 10000,\n" +
                "  \"appsecret\": \"PTPYJ9SlN0tvrm7jr0Un\",\n" +
                "  \"phones\": [\n" +
                "    \"17601026576\"\n" +
                "  ],\n" +
                "  \"prodIpAddress\": \"10.1.2.177\",\n" +
                "  \"appkey\": \"igicv1X4LQ6iZdqzpRwA\",\n" +
                "  \"templateCode\": \"2322\",\n" +
                "  \"params\": [\n" +
                "    \"8414\",\n" +
                "    \"17601026576\"\n" +
                "  ],\n" +
                "  \"url\": \"http://newyfb.5ifapiao.com/api4.0/v4/smsSend\"\n" +
                "}";
        JSONObject smsSend = (JSONObject) JSONObject.parse(s);
        String key = "";
        if (null == smsSend.get("phones")) {
            JSONArray array = (JSONArray) smsSend.get("to");
            String mail = array.get(0).toString();
            key = mail + "_" + System.currentTimeMillis();
            System.out.println("1111");
        }
        if (null == smsSend.get("to")) {
            JSONArray array = (JSONArray) smsSend.get("phones");
            String phone = array.get(0).toString();
            key = phone + "_" + System.currentTimeMillis();
            System.out.println("2222");

        }
        System.out.println(key);
    }

    boolean a;


    @org.junit.Test
    public void t3333t() {
// 22044287.85
        double[] ddd = {683, 135, 0.01, 0.01, 0.01, 0.01, 0.06, 0.01, 683, 0.01, 0.03, 683, 2000000,
                683, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 683, 0.01, 0.01, 683, 0.01, 90, 683, 0.01,
                683, 0.01, 0.01, 0.01, 0.01, 683, 683, 0.01, 683, 0.01, 0.01, 683, 683, 90, 0.01, 0.01, 0.01,
                683, 90, 0.01, 683, 0.01, 0.01, 0.01, 123, 0.01, 0.01, 90, 0.01, 30, 30, 683, 0.01, 683, 683,
                0.01, 90, 30, 683, 0.01, 0.01, 30, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 683, 0.01, 683,
                0.01, 683, 0.01, 0.03, 0.01, 0.01, 0.01, 683, 0.01, 0.01, 0.01, 20001, 0.01, 20000000,
                0.01, 30, 135, 0.01, 5535.11, 0.01, 0.01, 0.01, 683, 683, 0.01, 0.01, 683};
        List<Double> doubles = new ArrayList<>();
        for (double v : ddd) {
            doubles.add(v);
        }

        List<String> strings = new ArrayList<>();
//        doubles.add(0.01);
//        doubles.add(0.09);
//        doubles.add(0.13);
//        doubles.add(1.91);
//        doubles.add(99.01);
//        doubles.add(100.09);

        for (Double aDouble : doubles) {
            strings.add(String.valueOf(aDouble));
        }

        BigDecimal bigDecimal = new BigDecimal("0.00");
        for (String s : strings) {
            bigDecimal = bigDecimal.add(new BigDecimal(s));
        }
        System.out.println(Double.valueOf(bigDecimal.toString()));


    }

    @org.junit.Test
    public void ttttqw3e2() {
        Map map = new HashMap(8);
        map.put("rtyuu", 42341);
        map.put("SDFSD", 12312);
        map.put("AS", 1453);
        map.put("ghdfh", 1645);
        map.put("asdf", 17456);
        map.put("asdfsa", 14356);
        map.put("vzxcvzx", 11234);
        map.put("piopyu", 1123);
        for (Object o : map.keySet()) {
            System.out.println(o + " : " + o.hashCode());
        }
        System.out.println();
        map.put("ertyurety", 11234);
        map.put("qweqe", 1756);
        map.put("qweqwe", 1756);
        for (Object o : map.keySet()) {
            System.out.println(o + " : " + o.hashCode());
        }
    }

    // BCryp加密
    @org.junit.Test
    public void testpass() {
        String password = "123456";
        String afterPass = BCrypt.hashpw(password, BCrypt.gensalt());
        System.out.println(afterPass);
        boolean checkpw = BCrypt.checkpw(password, afterPass);
        System.out.println(checkpw);
    }

    @org.junit.Test
    public void testList() {
        List<User> users = new ArrayList<>();
        System.out.println(users == null);
        User user = new User();
        User user1 = new User();
        //System.out.println(user1.getName().equals(user.getName()));
        System.out.println("a".contains("aa"));
    }

    @org.junit.Test
    public void test3232() {
        String s = "{\"time\":1586328044757}";
        JSONObject jsonObject = JSON.parseObject(s);
        long l = jsonObject.getLong("time");
        Date date = new Date(l);
        System.out.println(date);
    }

    @org.junit.Test
    public void test() {
        String key = "expert:chat:b52211cecfdd4ab3ae09857a3835535a:4928";
        String[] keyItems = key.split("\\:");
        for (String keyItem : keyItems) {
            System.out.println(keyItem);
        }
    }

    /**
     * 给hashMap排序
     */
    @org.junit.Test
    public void testHashMap() {
        HashMap<Integer, Person> map = new HashMap();
        map.put(1, new Person("zs", 1));
        map.put(3, new Person("ll", 2));
        map.put(2, new Person("ww", 3));
        Set<Map.Entry<Integer, Person>> entries = map.entrySet();
        System.out.println("排序前：");
        map.entrySet().forEach(System.out::println);
        ArrayList<Map.Entry<Integer, Person>> list = new ArrayList<>(entries);

        Collections.sort(list, new Comparator<Map.Entry<Integer, Person>>() {
            @Override
            public int compare(Map.Entry<Integer, Person> o1, Map.Entry<Integer, Person> o2) {
                return o2.getValue().getAge() - o1.getValue().getAge();
            }
        });
        System.out.println();
        LinkedHashMap<Object, Object> linkedHashMap = new LinkedHashMap<>();
        list.forEach(l -> {
            linkedHashMap.put(l.getKey(), l.getValue());
        });
        System.out.println("排序后：");
        linkedHashMap.entrySet().forEach(System.out::println);

    }

    @org.junit.Test
    public void ttt() {
        String aaa = "111";
        // \u000d aaa = "222";
        System.out.println(aaa);
        HashMap<Object, Object> hashMap = new HashMap<>();
        hashMap.put("aaa", null);
        if (StringUtils.isEmpty(hashMap.get("aaa").toString())) {
            System.out.println(hashMap.get("aaa"));
        }
    }

    /**
     * switch可以传入的类型
     */
    @org.junit.Test
    public void testSwitch() {
        int l = 2;
        // char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
        switch (l) {
            case 0:
                System.out.println(0);
                break;
            case 1:
                System.out.println(1);
                break;
            default:
                break;
        }
        System.out.println("11111");
    }

    @org.junit.Test
    public void testNull() {
        Map map = Maps.newHashMap();
        map.put("name", "");
        map.put("name2", null);
        map.put("name3", "zs");
        boolean b = Optional.ofNullable(map.get("name")).isPresent(); // 非空 true
        boolean b2 = Optional.ofNullable(map.get("name2")).isPresent(); // 空 false
        boolean b3 = Optional.ofNullable(map.get("name3")).isPresent(); // 非空 true
        boolean b4 = Optional.ofNullable(map.get("name4")).isPresent(); // 空 false
        System.out.println(b);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);

    }


    /**
     * 数组中某个元素出现的次数最多的值和出现的次数
     */
    @org.junit.Test
    public void testArray() {
        int[] array = {1, 2, 1, 2, 3, 2, 11, 3, 4, 5, 6};
        List<Integer> list = new ArrayList();
        Set<Integer> set = new LinkedHashSet<>();
        Map map = Maps.newHashMap();
        for (int i : array) {
            list.add(i);
            set.add(i);
        }
        for (Integer i : set) {
            System.out.println("当前遍历数据Set：" + i);
            int flag = 0;
            for (Integer l : list) {
                if (i.equals(l)) {
                    flag = flag + 1;
                }
            }
            System.out.println("当前遍历重复数据：" + flag);
            map.put(i, flag);
        }

        System.out.println(map);

        Set<Integer> keySet = map.keySet();
        int big = 0;
        int bigKey = 0;
        for (Integer key : keySet) {
            Integer value = (Integer) map.get(key);
            if (value > big) {
                bigKey = key;
                big = value;
            }
        }
        System.out.println("最多出现次数的值是：" + bigKey);
        System.out.println("最多出现次数是：" + big);


    }

    @org.junit.Test
    public void tttttds() {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("orderId", "3412e2dfqw");
        JSONArray orderIds = JSONArray.parseArray(map.get("orderId").toString());
        System.out.println(orderIds);
    }

    @org.junit.Test
    public void testQRRX() {
        ReferenceQueue<String> referenceQueue = new ReferenceQueue<>();
        // 强引用 只要引用一直在 内存不足时JVM宁愿抛异常也不清除对象
        String str = new String("abc");
        // 软引用 内存不够的时候才会回收 比如可以实现一个图片缓存
        SoftReference<String> softReference = new SoftReference<>(str, referenceQueue);
        // 弱引用 在GC的时候，不管内存空间足不足都会回收这个对象，同样也可以配合ReferenceQueue 使用，也同样适用于内存敏感的缓存。ThreadLocal中的key就用到了弱引用。
        WeakReference<String> weakReference = new WeakReference<>(str, referenceQueue);
        // 虚引用 任何时候可能被GC回收，就像没有引用一样。
        PhantomReference phantomReference = new PhantomReference<>(str, referenceQueue);
        str = null;
        // 通知GC JVM什么时候扫描回收对象是JVM自己的状态决定的。就算扫描到软引用对象也不一定会回收它，只有内存不够的时候才会回收。
        System.gc();
        System.out.println("softReference：" + softReference.get()); // abc
        System.out.println("weakReference：" + weakReference.get()); // abc
        System.out.println("phantomReference：" + phantomReference.get()); // 软引用这样获取不到 没意义 abc
        Reference<? extends String> reference = referenceQueue.poll();
        System.out.println("reference：" + reference); //null
    }

    private Random rand;

    {
        try {
            rand = SecureRandom.getInstanceStrong();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * 生成谁随机数
     */
    @org.junit.Test
    public void testRandom() {
        for (int j = 0; j <= 10; j++) {
            int i = rand.nextInt(10);
            System.out.println(i);
        }
    }

    private final Logger logger = LoggerFactory.getLogger(Test.class);

    @org.junit.Test
    public void testLog() {
//       String type = "2";
//       if (type.equals("3")){
//           System.out.println("333");
//       }
//       if (!type.equals("3")){
//           System.out.println(type);
//       }
//        try {
//            int i = 1 / 0;
//        } catch (Exception e) {
//            //e.printStackTrace();
//            log.info("error:,", e);
//            log.info("error:{}", e.getMessage());
//            log.info("error:{}", e.getStackTrace());
//            log.info(e.getMessage(), e);
//
//        }

        try {
            System.out.println("----1");
            Thread.sleep(5000);
            System.out.println("----2");
        } catch (InterruptedException e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }

    }

    /**
     * i++，先使用变量，再进行变量自增1
     * ++i，先进行变量自增1，再使用变量
     */
    @org.junit.Test
    public void testAdd() {
        int a = 0;
        for (int i = 0; i < 99; i++) {
            a = a++;
        }
        System.out.println(a);

        int b = 0;
        for (int i = 0; i < 99; i++) {
            b = ++b;
        }
        System.out.println(b);
    }

    @org.junit.Test
    public void trtr() {
        String s = "abc";
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            System.out.println(chars[i]);
        }
        System.out.println(chars);
        System.out.println(chars);

    }

    @org.junit.Test
    public void age() {
        Long t = 1597929244l;
        Long time = 1597929244000l;
        if (String.valueOf(t).length() == 10) {
            t = t * 1000;
        }
        Date date = new Date(time);
        Date date2 = new Date(t);
        System.out.println(getAgeByBirth(date));
        System.out.println(getAgeByBirth(date2));

    }

    public int getAgeByBirth(Date birthDay) {

        int age = 0;
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) { //出生日期晚于当前时间，无法计算
            throw new IllegalArgumentException(
                    "The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);  //当前年份
        int monthNow = cal.get(Calendar.MONTH);  //当前月份
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH); //当前日期
        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        age = yearNow - yearBirth;   //计算整岁数
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) age--;//当前日期在生日之前，年龄减一
            } else {
                age--;//当前月份在生日之前，年龄减一
            }
        }
        return age;
    }


    @org.junit.Test
    public void te() {

        Person person = new Person();
        person.setName("cq");

        Person person2 = getPer(person);

        System.out.println(person);
        System.out.println(person2);

        System.out.println(person.equals(person2));

    }

    public Person getPer(Person person) {

        // person.setId(1);

        Person p = new Person();
        p.setId(1);
        p.setName(person.getName());
        person = p;
        return p;
    }

    @org.junit.Test
    public void testInt() {
        AtomicInteger integer = new AtomicInteger(0);
        for (int j = 0; j < 100; j++) {
            System.out.println(integer.addAndGet(1));
        }
    }

    private volatile int aa;

    @org.junit.Test
    public void ets() {
        new Thread(() -> {
            setaa();
        }).start();
        try {
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void etaas() {
        System.out.println(aa);
    }

    private void setaa() {
        aa = 1;
        System.out.println("a:" + aa);

    }

    private volatile AtomicInteger pageNumber = new AtomicInteger(0);

    /**
     * AtomicInteger
     */
    @org.junit.Test
    public void testtt() {
        for (; ; ) {
            System.out.println(pageNumber.get());
            pageNumber.addAndGet(1);
        }
    }

    /**
     * BeanUtils.copyProperties
     */
    @org.junit.Test
    public void testCopy() {
        User user = new User();
        user.setName("cq");
        user.setPassword("1231");
        UserCopy copy = new UserCopy();
        BeanUtils.copyProperties(user, copy);
        System.out.println(JSON.toJSONString(user));
        copy.setId(1L);
        System.out.println(JSON.toJSONString(copy));
        System.out.println(user == copy);
        System.out.println(user.equals(copy));
    }

    @org.junit.Test
    public void testMaps() {
        Integer a = null;
        System.out.println(isNull(a));
    }


    @org.junit.Test
    public void testMaweps() {
        String s = String.valueOf(250.00 / (4 * 100) * 100);
        System.out.println(s.substring(0, s.indexOf(".")));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println(200.00 / (4 * 100) * 100);
    }


    @org.junit.Test
    public void testMaweps33() {
        StringBuffer sb = new StringBuffer();
        sb.append("csdfsdfs").append(",");
        System.out.println(sb.substring(0, sb.length() - 1));
    }

    /**
     * 生成带logo的二维码
     */
    @org.junit.Test
    public void testMawdeps33() {
        // 生成二维码
        // File file = null;
        try {
            BufferedImage image = ImageIO.read(new URL("https://zhi.re/hot.png"));
            // file = FileUtil.file();
            QrConfig config = QrConfig.create().setImg(image).setMargin(4);
            byte[] bytes = QrCodeUtil.generatePng("https://zhi.re", config);
            String erCode = Base64.encode(bytes);
            System.out.println("data:image/jpg;base64," + erCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @org.junit.Test
    public void t() {

        ArrayList<User> list = new ArrayList<>();
        ArrayList<Person> list2 = new ArrayList<>();
        List<Object> res = new ArrayList<>();
        User user = new User();
        User user2 = new User();
        Person person = new Person();
        Person person2 = new Person();
        person.setName("address01");
        person2.setName("address02");
        list2.add(person);
        list2.add(person2);
        user.setName("cq01");
        user2.setName("cq02");
        list.add(user);
        list.add(user2);
        list.forEach(l -> {
            IOCUser iocUser = new IOCUser();
            iocUser.setName(l.getName());
            list2.forEach(ll -> {
                iocUser.setAddress(ll.getName());
                System.out.println(iocUser);
                res.add(iocUser);
                System.out.println(res);
//                IOCUser iocUser2 = new IOCUser();
//                BeanUtils.copyProperties(iocUser,iocUser2);
//                iocUser2.setAddress(ll.getName());
//                res.add(iocUser2);
            });
        });
        // System.out.println(JSON.toJSONString(res);
        // 上面的输出会打印：[{"address":"d3e2dd","name":"cqq"},{"$ref":"$[0]"},{"address":"d3e2dd","name":"c22qq"},{"$ref":"$[2]"}]
        // {"$ref":"$[0]"}是重复引用的意思
        // 实际上的输出是这样的：[{"address":"address02","name":"cq01"},{"address":"address02","name":"cq01"},
        // {"address":"address02","name":"cq02"},{"address":"address02","name":"cq02"}]
        // 原因 list存引用类型存放的是引用地址，里面的循环，其实最后保存的是最后一次遍历的值，所以之前保存的值会改变
        System.out.println(JSON.toJSONString(res, SerializerFeature.DisableCircularReferenceDetect));

    }

    @org.junit.Test
    public void oo() {
        ArrayList<IOCUser> list = new ArrayList<>();
        ArrayList<IOCUser> list2 = new ArrayList<>();
        IOCUser user = new IOCUser();
        IOCUser user2 = new IOCUser();
        user.setId(1);
        user2.setId(2);
        list.add(user);
        list2.add(user2);
        list.forEach(this::setValue);
        System.out.println(list);
        HashMap<Object, Object> map = new HashMap<>();
        map.put(1, list);
        // map.put(2, list2);
        Collection<Object> values = map.values();
        System.out.println(JSON.toJSONString(map));
        ArrayList<Object> arrayList = new ArrayList<>(values);
        System.out.println(arrayList);
        System.out.println(values);
    }

    int f = 0;

    private void setValue(IOCUser iocUser) {
        iocUser.setAddress("address" + f);
        f++;
    }

    /**
     * 对称加密
     */
    @org.junit.Test
    public void t77() {
        String content = "test12345";
        //随机生成密钥
        byte[] key = SecureUtil.generateKey(SymmetricAlgorithm.AES.getValue()).getEncoded();
        //构建
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key);

        //加密为16进制表示
        String encryptHex = aes.encryptHex(content);
        System.out.println("加密后：" + encryptHex);
        //解密为字符串
        String decryptStr = aes.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        System.out.println("解密后：" + decryptStr);

    }

    /**
     * 带负数的数组排序
     */
    @org.junit.Test
    public void t773() {
        int[] a = {-2, 3, -4, 5, 1};
        int[] b = new int[a.length];
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < a.length; i++) {
            if (a[i] < 0) {
                int abs = Math.abs(a[i]);
                list.add(abs);
                b[i] = abs;
            } else {
                b[i] = a[i];
            }
        }
        Arrays.sort(b);
        for (int i = 0; i < b.length; i++) {
            if (list.contains(b[i])) {
                b[i] = -(b[i]);
            }
        }
        System.out.println(Arrays.toString(b));
    }

    @org.junit.Test
    public void read() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("/script.sql"));
            String line;
            // long i = 0;
//            Map<String, String> map = new HashMap<>();
            while ((line = br.readLine()) != null) {
                //   i++;
                // System.out.println(line);
                // String s = line.replace("----", ",");
                //List<String> list = Arrays.stream(s.split(",")).collect(Collectors.toList());
//                map.put(list.get(0), list.get(1));
            }
            System.out.println("ok");
            // System.out.println(i);
            // System.out.println(JSON.toJSONString(map));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    /**
     * 解压
     */
    @org.junit.Test
    public void zi() {
        ZipUtil.unzip("/xx.rar");
    }

    // 常用的通配符为： T，E，K，V，？
    // ？ 表示不确定的 java 类型
    // T (type) 表示具体的一个 java 类型
    // K V (key value) 分别代表 java 键值中的 Key Value
    // E (element) 代表 Element
    @org.junit.Test
    public void fanxin() {
        List<Integer> list = new ArrayList<>();
        list.add(12);
        //这里直接添加会报错
        // list.add("a");
        Class<? extends List> clazz = list.getClass();
        try {
            Method add = clazz.getDeclaredMethod("add", Object.class);
            //但是通过反射添加，是可以的
            add.invoke(list, "kl");
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(list);

        Generic<Integer> genericInteger = new Generic<Integer>(123456);

        // 创建不同类型数组： Integer, Double 和 Character
        Integer[] intArray = {1, 2, 3};
        String[] stringArray = {"Hello", "World"};
        printArray(intArray);
        printArray(stringArray);

    }

    // 1.泛型类：
    //此处T可以随便写为任意标识，常见的如T、E、K、V等形式的参数常用于表示泛型
    //在实例化泛型类时，必须指定T的具体类型
    public class Generic<T> {

        private T key;

        public Generic(T key) {
            this.key = key;
        }

        public T getKey() {
            return key;
        }
    }

    // 2.泛型接口 ：
    public interface Generator<T> {
        public T method();
    }

    // 实现泛型接口，不指定类型：
    class GeneratorImpl<T> implements Generator<T> {
        @Override
        public T method() {
            return null;
        }
    }

    // 实现泛型接口，指定类型：
    class GeneratorImpl2<T> implements Generator<String> {
        @Override
        public String method() {
            return "hello";
        }
    }

    // 3.泛型方法 ：
    public static <E> void printArray(E[] inputArray) {
        for (E element : inputArray) {
            System.out.printf("%s ", element);
        }
        System.out.println();
    }

    /**
     * ImmutableMap
     */
    @org.junit.Test
    public void temap() {
        getss("cq", 1);
        getss("cq2", 2);
    }

    private void getss(String n, Integer a) {
        Map<String, Object> map = ImmutableMap.of("name", n, "age", a);
        System.out.println(JSON.toJSONString(map));

    }


    @org.junit.Test
    public void tes() {
        User user = new User();
        user.setName("cq");
        User user1 = get(user);
        System.out.println(user);
        System.out.println(user.equals(user1));
    }

    private User get(User u) {
        u.setName("chenqi");
        u.setManagerId(1L);
        System.out.println(u);
        return u;
    }

    /**
     * ArrayList和LinkedList插入性能对比
     * 数据量小的情况下 LinkedList快
     * 数据量大的情况下 ArrayList快
     */
    @org.junit.Test
    public void te2eee() {
        long l = System.currentTimeMillis();
        ArrayList list = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            list.add(i);
            System.out.print(i);
        }
        long l2 = System.currentTimeMillis();
        System.out.println();
        System.out.println(l2 - l);

        long l3 = System.currentTimeMillis();
        LinkedList list2 = new LinkedList();
        for (int i = 0; i < 1000; i++) {
            list2.add(i);
            System.out.print(i);
        }
        long l4 = System.currentTimeMillis();
        System.out.println();
        System.out.println(l4 - l3);
    }


    @org.junit.Test
    public void t0() {
        int i = 2;
        // i -= i++;
        i = i++;
        System.out.println(i);
        i = i - i;
        System.out.println(i);

    }

    @org.junit.Test
    public void tr() {
        int a = 1;
        Integer b = getSupplier(() -> a + 1);
        System.out.println(b);

        int[] numbers = {100, 200, 300, 400, 500, -600, -700, -800, -900, -1000};
        int numberMax = getSupplier(
                () -> {
                    int max = numbers[0];
                    for (int number : numbers) {
                        if (max < number) {
                            max = number;
                        }
                    }
                    return max;
                }
        );
        System.out.println("数组中的最大值为：" + numberMax);
    }

    public <T> T getSupplier(Supplier<T> supplier) {
        return supplier.get();
    }

    /**
     * List 的随机访问
     */
    @org.junit.Test
    public void ty() {
        // 调用别人的服务获取到list
        List<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        if (list instanceof RandomAccess) {
            // 内部数组实现，可以随机访问
            System.out.println(list.get(list.size() - 1));
        } else {
            // 内部可能是链表实现，随机访问效率低
            System.out.println(list.get(0));
        }

    }

    /**
     * 在 java 集合类库中，List 的 contains 方法普遍时间复杂度是 O(n) ，
     * 如果在代码中需要频繁调用 contains 方法查找数据，
     * 可以先将 list 转换成 HashSet 实现，将 O(n) 的时间复杂度降为 O(1) 。
     */
    @org.junit.Test
    public void ty23() {
        ArrayList<Integer> list = new ArrayList<>();
        Set<Integer> set = new HashSet(list);
        for (int i = 0; i <= Integer.MAX_VALUE; i++) {
            // 时间复杂度O(1)
            set.contains(i);
        }

    }

    @org.junit.Test
    public void tre() {
        String pinyin = PinyinUtil.getPinyin("长沙", " ");
        System.out.println(pinyin);

    }

    @org.junit.Test
    public void tre32() {
        Map<String, Object> map = Maps.newTreeMap();
        map.put("z", "cq");
        map.put("c", "cq3");
        map.put("b", "cq3");
        System.out.println(JSON.toJSONString(map));
    }

    @org.junit.Test
    public void tre32342() {
        try {
            Map<String, String> map = CollUtil.newHashMap(4096);
            BufferedReader br = new BufferedReader(
                    new InputStreamReader(
                            new ClassPathResource("static/district.txt").getStream()));
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                String[] s = line.split(" ");
                System.out.println(Arrays.toString(s));
                map.put(s[0], s[1]);
            }
            log.info("map:{}", map.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @org.junit.Test
    public void tre3233342() throws IOException {
        Map<String, String> map = CollUtil.newHashMap(256);
        org.springframework.core.io.ClassPathResource resource = new org.springframework.core.io.ClassPathResource("static/countries.json");
        cn.hutool.json.JSON json = JSONUtil.readJSONObject(resource.getFile(), Charset.defaultCharset());
        cn.hutool.json.JSONArray records = ((cn.hutool.json.JSONObject) json).getJSONArray("RECORDS");
        records.forEach(l -> {
            Map map1 = JSONObject.parseObject(JSON.toJSONString(l), Map.class);
            map.put(map1.get("cname").toString(), map1.get("name").toString());
        });
        System.out.println(JSON.toJSONString(map));
    }

    @org.junit.Test
    public void ter() {
        long l = System.currentTimeMillis();
        BitMap map = new IntMap(5000);
        for (int i = 0; i < 5000; i++) {
            map.add(i);
        }
        System.out.println(System.currentTimeMillis() - l + " " + map.contains(5000));

        long l2 = System.currentTimeMillis();
        Map<Integer, Integer> map1 = new HashMap<>(5000);
        for (int i = 0; i < 5000; i++) {
            map1.put(i, i);
        }
        System.out.println(System.currentTimeMillis() - l2 + " " + map1.containsKey(5000));

    }

    /**
     * 谷歌BloomFilter布隆过滤器
     */
    @org.junit.Test
    public void tf() {
        Funnel<CharSequence> funnel = Funnels.stringFunnel(Charset.defaultCharset());
        BloomFilter f = BloomFilter.create(funnel, 1000000000, 0.03);
        long l = System.currentTimeMillis();
        System.out.println(l);
        for (int i = 0; i < 1000000; i++) {
            f.put(i + "hello");
        }
        long l2 = System.currentTimeMillis();
        System.out.println(l2);
        System.out.println(f.mightContain("999999999hello"));
        long l3 = System.currentTimeMillis() - l2;
        System.out.println(l3);
    }

    @org.junit.Test
    public void tr33() {
        int[] a = {1, 3, 2, 5, 4, 0, -1};
        sortNum(a);
        System.out.println(Arrays.toString(a));
        int b = getNum(a, 0);
        int c = getNum2(a, 0, 0, a.length - 1);
        System.out.println(b);
        System.out.println(c);
    }

    /**
     * 二分递归
     *
     * @param a
     * @param i
     * @param l
     * @param r
     * @return
     */
    private int getNum2(int[] a, int i, int l, int r) {
        if (l > r)
            return -1;
        int m = (l + r) / 2;
        if (a[m] > i) {
            return getNum2(a, i, l, m - 1);
        } else if (a[m] < i) {
            return getNum2(a, i, m + 1, r);
        } else {
            return i;
        }
    }


    private void sortNum(int[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                if (a[i] > a[j]) {
                    int t = a[i];
                    a[i] = a[j];
                    a[j] = t;
                }
            }
        }
    }

    private int getNum(int[] a, int b) {
        int l = 0;
        int r = a.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            if (a[m] > b) {
                r = m - 1;
            } else if (a[m] < b) {
                l = m + 1;
            } else {
                return m;
            }
        }
        return -1;
    }

    @org.junit.Test
    public void rew1() {
        String s = "1,2,3";
        List list = new ArrayList(Arrays.asList(s.split(",")));
        System.out.println(list);
        System.out.println(Joiner.on(",").join(list));
    }

    /**
     * LinkedHashMap实现LRU(最近最少使用算法)
     */
    @org.junit.Test
    public void rew13() {
        LinkedHashMap map = new LinkedHashMap(10, 0.75f, true) {
            private static final long serialVersionUID = 1;

            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > 10;
            }
        };
        map.put("a", 1);
        map.put("b", 2);
        map.put("c", 3);
        map.put("d", 4);
        map.put("e", 5);
        map.put("f", 6);
        map.put("g", 7);
        map.put("h", 8);
        map.put("i", 9);
        map.put("j", 10);
        map.forEach((k, v) -> {
            System.out.println("k:" + k + " v:" + v);
        });
        System.out.println();
        map.get("a");
        map.get("a");
        map.get("a");
        map.get("a");
        map.get("a");
        map.get("a");
        map.get("a");
        map.put("m", 11);
        map.put("n", 12);

        map.forEach((k, v) -> {
            System.out.println("k:" + k + " v:" + v);
        });

    }

    /**
     * 二分查找(递归和非递归)
     * 反转链表(递归和非递归)
     * 常用Java集合类
     * HashMap为什么长度是2的n次幂,数据结构,扩容(包括元素移动的细节),线程不安全的问题
     * ConcurrentHashMap怎么保证线程安全, 1.7和1.8有什么变化,为什么要要这么优化
     * CopyOnWriteList怎么保证线程安全, 为什么这么做
     * Java synchronized关键字的作用,原理,锁升级、锁粗化、锁消除
     * volatile关键字的作用,原理
     * MVCC
     * 事务的ACID ,每一项是如何保证的
     * MySQL的索引结构,为什么是B+树而不是B树
     * 先升序后降序的数组排序
     * 求递增数组中相加等于10的元素对
     * 17^400 - 19100计算结果能不能被10整除
     * 一个urI对应一个random值 ,要求设计-个系统 ,根据url查询random值,具体到表怎么设计,索引怎么
     * 加，代码怎么写
     * 镜像二叉树(递归和非递归)
     * 删除二叉搜索树的某一个节点
     * 给定数组,求第k大的数字
     * 单例模式的几种写法,解释为什么
     * tcp握手挥手过程,以及socket的状态变化
     * 线程的状态,以及变化的时机
     * Java内存模型,堆的组成, gc过程
     * synchronized修饰同一个类的两个静态***同步吗,为什么
     * 线程池设置了coreSize和maxSize之后,如果线程数量已经达到了coreSize ,这个时候进来一个任务,会怎么处理
     * SQL查询优化怎么做
     */

    @org.junit.Test
    public void rrtre() {
        System.out.println(mod(1, 3));
        System.out.println(mod(2, 3));
        System.out.println(mod(3, 3));
        System.out.println(mod(4, 3));
        System.out.println(mod(5, 3));
        System.out.println(mod(6, 3));
        System.out.println(mod(7, 3));
        System.out.println(mod(8, 3));
        System.out.println(mod(9, 3));
        System.out.println(mod(10, 3));
        System.out.println(mod(11, 3));
    }

    public static String mod(long key, int radix) {
        if (radix == 1) {
            return "";
        }
        int suffix = (int) (key % radix);
        if (suffix < 0) {
            suffix += radix;
        }
        return String.format("_%0" + pads(radix) + "d", suffix);
    }

    private static int pads(int radix) {
        return (int) Math.ceil(Math.log10(radix));
    }

    @org.junit.Test
    public void tefcs() {
        Map<Long, Long> map = Collections.emptyMap();
        List<Long> list = Arrays.asList(1L, 2L, 3L);
        list.forEach(l -> {
            if (map.containsKey(l)) {
                System.out.println("11");
            }
        });
    }

    /**
     * Timestamp
     */
    @org.junit.Test
    public void gsasf() {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);
    }

    @org.junit.Test
    public void trrt() {
        List<Integer> ins = Arrays.asList(3, 2, 5, 6, 9);
        // 集合反转
        Collections.reverse(ins);
        System.out.println(JSON.toJSONString(ins));
    }


}


