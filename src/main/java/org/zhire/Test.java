package org.zhire;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import org.Person;
import org.zhire.pojo.User;
import org.mindrot.jbcrypt.BCrypt;

//import javax.persistence.Persistence;
import java.io.*;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Author: chenqi
 * @Date: 2019.3.6 14:19
 */
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

        for (int i = 0; i < 1000; i++) {
            List list = Arrays.asList(beforeShuffle);
            Collections.shuffle(list);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < list.size(); j++) {
                sb.append(list.get(j));
            }
            String afterShuffle = sb.toString();
            String result = afterShuffle.substring(5, 10);
            System.out.println(result);
        }

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
    public void ttt(){
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
        int l = 0;
        // char, byte, short, int, Character, Byte, Short, Integer, String, or an enum
        switch (l) {
            case 0:
                System.out.println(true);
                break;
            default:
                System.out.println("default");
        }
    }


}