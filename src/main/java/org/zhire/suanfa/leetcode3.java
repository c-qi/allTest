package org.zhire.suanfa;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.*;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * <p>
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * <p>
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * @Author: chenqi
 * @Date: 2020.1.19 14:24
 */
public class leetcode3 {
    @Test
    public void lengthOfLongestSubstring() {
        String s = "dvdf";
        // int i = lengthOfLongestSubstring(s);
        int i2 = lengthOfLongestSubstring2(s);
        System.out.println(i2);

    }
    //  6 5 4
    //  6 4 3


    public int lengthOfLongestSubstring(String s) {
        char[] chars = s.toCharArray();
        Set<String> set = new LinkedHashSet();
        for (int i = 0; i < chars.length; i++) {
            set.add(String.valueOf(chars[i]));
        }
        String str = "";
        for (String o : set) {
            str += o;
        }
        if (s.contains(str) && set.size() != 1) {
            return set.size();
        } else if (set.size() == 1) {
            return 1;
        } else if (!s.contains(str)) {
            return set.size() - 1;
        } else {
            return set.size();
        }

    }

    // dvdf
    public int lengthOfLongestSubstring2(String s) {
        int n = s.length(), ans = 0;
        //创建map窗口,i为左区间，j为右区间，右边界移动
        Map<Character, Integer> map = new HashMap<>();
        for (int j = 0, i = 0; j < n; j++) {
            // 如果窗口中包含当前字符，
            if (map.containsKey(s.charAt(j))) {
                //左边界移动到 相同字符的下一个位置和i当前位置中更靠右的位置，这样是为了防止i向左移动
                i = Math.max(map.get(s.charAt(j)), i);
            }
            //比对当前无重复字段长度和储存的长度，选最大值并替换
            //j-i+1是因为此时i,j索引仍处于不重复的位置，j还没有向后移动，取的[i,j]长度
            ans = Math.max(ans, j - i + 1);
            // 将当前字符为key，下一个索引为value放入map中
            // value为j+1是为了当出现重复字符时，i直接跳到上个相同字符的下一个位置，if中取值就不用+1了
            map.put(s.charAt(j), j + 1);
        }
        return ans;
    }


    public static void main(String[] args) throws Exception {
//        Map tokenMap = Maps.newHashMap();
//        tokenMap.put("110108008","qwfdewqfwqfwqfwqfwqf");
//        tokenMap.put("110108009","qqweqwewqwfdewqfwqfwqfwqfwqf");
//        String s = "{\"msg\":\"success\",\"code\": 0,\"data\":[{\"sourceId\":\"1101080080\",\"unionid\":\"o_v6RwXZBcdCEXI0KQmByRR13yTU\",\"phone\":\"17375363838\",\"userType\":\"C\",\"userId\":27391,\"email\":\"fghuuhh@ee.com\"},{\"sourceId\":\"110108008\",\"unionid\":\"o_v6RwaYzLjZCaFC-NDTzHXRVxeM\",\"phone\":\"13764976345\",\"userType\":\"C\",\"userId\":27392,\"email\":null}]}";
//        JSONObject jsonObject = JSON.parseObject(s);
//        JSONArray array = (JSONArray) jsonObject.get("data");
//        for (int i = 0; i < array.size(); i++) {
//            System.out.println(array.get(i));
//            System.out.println();
//            JSONObject json = (JSONObject) array.get(i);
//            if (tokenMap.containsKey(json.get("sourceId").toString())) {
//                System.out.println(tokenMap.get(json.get("sourceId").toString()).toString());
//            }
//        }
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-05 00:00:00").getTime());
        //System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse("2020-02-05 23:59:59").getTime());
        String s = "{\n" +
                "  \"msg\": \"success\",\n" +
                "  \"code\": 0,\n" +
                "  \"data\": {\n" +
                "    \"total\": 2,\n" +
                "    \"list\": [\n" +
                "      {\n" +
                "        \"period\": 31536000,\n" +
                "        \"resource_type\": 23,\n" +
                "        \"created_at\": \"2020-02-05 11:19:38\",\n" +
                "        \"wx_app_type\": 1,\n" +
                "        \"settle_time\": \"0000-00-00 00:00:00\",\n" +
                "        \"pay_time\": \"2020-02-05 11:19:57\",\n" +
                "        \"payment_type\": 15,\n" +
                "        \"user_id\": \"u_5e3a2a611e80e_EPFo4rfyIz\",\n" +
                "        \"price\": 29900,\n" +
                "        \"product_id\": \"s_5e016185b56ae_Yq4GPEKZ\",\n" +
                "        \"out_order_id\": \"oo_5e3a344aca77a_jReDgf7P\",\n" +
                "        \"purchase_name\": \"优税学院VIP超级会员\",\n" +
                "        \"app_id\": \"app0Dse2FPd5809\",\n" +
                "        \"order_id\": \"o_1580872778_5e3a344ac3c13_89538772\",\n" +
                "        \"order_state\": 1\n" +
                "      },\n" +
                "      {\n" +
                "        \"period\": 31536000,\n" +
                "        \"resource_type\": 23,\n" +
                "        \"created_at\": \"2020-02-05 11:18:42\",\n" +
                "        \"wx_app_type\": 1,\n" +
                "        \"settle_time\": \"0000-00-00 00:00:00\",\n" +
                "        \"pay_time\": \"0000-00-00 00:00:00\",\n" +
                "        \"payment_type\": 15,\n" +
                "        \"user_id\": \"u_5e3a2a611e80e_EPFo4rfyIz\",\n" +
                "        \"price\": 29900,\n" +
                "        \"product_id\": \"s_5e016185b56ae_Yq4GPEKZ\",\n" +
                "        \"purchase_name\": \"优税学院VIP超级会员\",\n" +
                "        \"app_id\": \"app0Dse2FPd5809\",\n" +
                "        \"order_id\": \"o_1580872722_5e3a3412b64cd_17527462\",\n" +
                "        \"order_state\": 6\n" +
                "      }\n" +
                "    ]\n" +
                "  }\n" +
                "}";
        JSONObject jsonObject = JSON.parseObject(s);
        JSONObject data = (JSONObject) jsonObject.get("data");
        JSONArray orderArrayList = (JSONArray) data.get("list");
        for (int j = 0; j < orderArrayList.size(); j++) {
            JSONObject obj = (JSONObject) orderArrayList.get(j);
            // 商品名称
            String purchaseName = obj.get("purchase_name").toString();

            // 订单状态
            String orderState = obj.get("order_state").toString();
            // 订单编号、
            String orderId = obj.get("order_id").toString();
            // 下单时间、
            String createdAt = obj.get("created_at").toString();
            // 订单类型(图文、音频、视频、专栏、直播、知识会员)、
            String resourceType = obj.get("resource_type").toString();
            // 价格(单价)
            String price = obj.get("price").toString();
            // 数量、

            // 实付金额、

            // 买家昵称、
            // 买家微信ID、

            // 买家所属渠道、
            String source = null;
            // 客户类型(个人)、
            // 订单来源（小鹅通）


        }

        System.out.println(orderArrayList);
    }

    @Test
    public void test3232() {
        String s = "{\"time\":1586328044757}";
        JSONObject jsonObject = JSON.parseObject(s);
        long l = jsonObject.getLong("time");
        Date date = new Date(l);
        System.out.println(date);
    }

    @Test
    public void test() {
        String key = "expert:chat:b52211cecfdd4ab3ae09857a3835535a:4928";
        String[] keyItems = key.split("\\:");
        for (String keyItem : keyItems) {
            System.out.println(keyItem);
        }
    }


}

