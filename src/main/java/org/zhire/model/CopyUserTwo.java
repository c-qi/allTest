package org.zhire.model;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.Data;
import org.junit.Test;
import org.springframework.beans.BeanUtils;

import java.util.*;


@Data
public class CopyUserTwo {
    private Long id;
    private String userName;
    private Integer userAge;
    private String password;
    private String country;
    private String city;
    private String area;
    private String address;
    private String mobile;
    private String email;
    private String qq;
    private String wechat;
    private String sex;
    private String workAddress;
    private String workYear;
    private String favorite;
    private String book;
    private String fruit;
    private String color;
    private String animal;

    @Test
    public void t() {
        CopyUserOne copyUserOne = new CopyUserOne();
        copyUserOne.setId(1234L);
        copyUserOne.setUserName("cq");
        copyUserOne.setUserAge(22+"");
        CopyUserTwo two = new CopyUserTwo();
        BeanUtil.copyProperties(copyUserOne, two);
        System.out.println(JSON.toJSONString(two));

    }

    public static void main(String[] args) {
        Map<String, Object> map = Maps.newHashMap();
        CopyUserOne copyUserOne = new CopyUserOne();
        copyUserOne.setId(1234L);
        copyUserOne.setUserName("cq");
        copyUserOne.setUserAge(22+"");
        copyUserOne.setPassword("q23423");
        copyUserOne.setCountry("rqwreq");
        copyUserOne.setCity("rqwerq");
        copyUserOne.setArea("qwerqw");
        copyUserOne.setAddress("qwerqw");
        copyUserOne.setMobile("qwerqw");
        copyUserOne.setEmail("qwerqw");
        copyUserOne.setQq("qwerqwe");
        copyUserOne.setWechat("qwer");
        copyUserOne.setSex("rqwerqwqwe");
        copyUserOne.setWorkAddress("qwerqwe");
        copyUserOne.setWorkYear("qwerwq");
        copyUserOne.setFavorite("qwerwq");
        copyUserOne.setBook("werwqer");
        copyUserOne.setFruit("qwerwe");
        copyUserOne.setColor("qwerwq");
        copyUserOne.setAnimal("werwqe");

        long l = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            CopyUserTwo copyUserTwo = new CopyUserTwo();
            copyUserTwo.setId(copyUserOne.getId());
            copyUserTwo.setUserName(copyUserOne.getUserName());
//            copyUserTwo.setUserAge(copyUserOne.getUserAge());
            copyUserTwo.setPassword(copyUserOne.getPassword());
            copyUserTwo.setCountry(copyUserOne.getCountry());
            copyUserTwo.setCity(copyUserOne.getCity());
            copyUserTwo.setArea(copyUserOne.getArea());
            copyUserTwo.setAddress(copyUserOne.getAddress());
            copyUserTwo.setMobile(copyUserOne.getMobile());
            copyUserTwo.setEmail(copyUserOne.getEmail());
            copyUserTwo.setQq(copyUserOne.getQq());
            copyUserTwo.setWechat(copyUserOne.getWechat());
            copyUserTwo.setSex(copyUserOne.getSex());
            copyUserTwo.setWorkAddress(copyUserOne.getWorkAddress());
            copyUserTwo.setWorkYear(copyUserOne.getWorkYear());
            copyUserTwo.setFavorite(copyUserOne.getFavorite());
            copyUserTwo.setBook(copyUserOne.getBook());
            copyUserTwo.setFruit(copyUserOne.getFruit());
            copyUserTwo.setColor(copyUserOne.getColor());
            copyUserTwo.setAnimal(copyUserOne.getAnimal());
        }
        map.put("set", System.currentTimeMillis() - l);

        long l1 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            CopyUserTwo copyUserThree = new CopyUserTwo();
            BeanUtil.copyProperties(copyUserOne, copyUserThree);
        }
        map.put("hutool", System.currentTimeMillis() - l1);


        long l2 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            CopyUserTwo copyUserThree = new CopyUserTwo();
            BeanUtils.copyProperties(copyUserOne, copyUserThree);
        }
        map.put("spring", System.currentTimeMillis() - l2);

        long l3 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            CopyUserTwo copyUserThree = new CopyUserTwo();
            try {
                org.apache.commons.beanutils.PropertyUtils.copyProperties(copyUserThree, copyUserOne);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("apache PropertyUtils", System.currentTimeMillis() - l3);

        long l4 = System.currentTimeMillis();
        for (int i = 0; i < 10000; i++) {
            try {
                CopyUserTwo copyUserThree = new CopyUserTwo();
                org.apache.commons.beanutils.BeanUtils.copyProperties(copyUserThree, copyUserOne);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        map.put("apache BeanUtils", System.currentTimeMillis() - l4);

        System.out.println(JSON.toJSONString(map));

    }
}
