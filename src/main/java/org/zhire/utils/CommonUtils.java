package org.zhire.utils;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

import java.util.*;

/**
 * 通用工具类
 */

public class CommonUtils {

    /**
     * Bean方法，复制属性
     *
     * @param source
     * @return
     */
    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * Bean方法，复制属性
     *
     * @param src
     * @param target
     */
    public static void copyProperties(Object src, Object target) {
        if (src != null) {
            BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
        }
    }


    /**
     * list 转map
     *
     * @param list
     * @return
     */
    public static Map<String, Boolean> listToMap(List<String> list) {
        if (list != null && list.size() > 0) {
            Map<String, Boolean> map = new HashMap<String, Boolean>();
            for (int i = 0; i < list.size(); i++) {
                String value = list.get(i);
                if (StringUtils.isNotBlank(value)) {
                    map.put(value, true);
                }
            }
            return map;
        }
        return null;
    }


    /**
     * 将一个list均分成n个list,主要通过偏移量来实现的
     * note:请不要对子list进行任何增删改查操作，会影响主list
     *
     * @param source
     * @return
     */
    public static <T> List<List<T>> averageAssign(List<T> source, int n) {
        List<List<T>> result = new ArrayList<>();
        // 计算出余数
        int remaider = source.size() % n;
        // 商
        int number = source.size() / n;
        // 偏移量
        int offset = 0;
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = source.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider--;
                offset++;
            } else {
                value = source.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }


        return result;

    }

    public static void main(String[] args) {
        List<Integer> integers = new ArrayList<>();
        integers.add(9);
        integers.add(1);
        integers.add(8);
        integers.add(10);
        integers.add(77);
        List<List<Integer>> lists = averageAssign(integers, 2);
        System.out.println(JSON.toJSONString(lists));
        List<Integer> integers1 = lists.get(0);
        integers1.add(199);
        System.out.println(integers);

    }
}
