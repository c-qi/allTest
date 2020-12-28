package org.zhire.controller;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiQueryParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Date;

import static com.alibaba.fastjson.JSON.toJSONString;

@Api(name = "beanController", description = "")
@RestController
@RequestMapping("/bean")
@Slf4j
public class BeanController {

    @Autowired
    private ApplicationContext applicationContext;

    @PostMapping("/refresh")
    @ApiMethod(description = "更新bean")
    public String refresh(
            @ApiQueryParam(name = "name", description = "bean名称")
            @RequestParam("name") String name,
            @ApiQueryParam(name = "keyValue", description = "要更新的key|value")
            @RequestParam("keyValue") String keyValue) {

        Object bean = applicationContext.getBean(name);
        String beanStrOld = toJSONString(bean);
        log.info("==beanStrOld==" + beanStrOld);

        String[] split = keyValue.split("\\|");
        String key = split[0];
        String value = split[1];

        // 拿到bean的Class对象
        Class<?> beanType = applicationContext.getType(name);
        if (beanType == null) {
            return "error beanName";
        }

        // 找到bean需要更新的字段
        Field[] declaredFields = beanType.getDeclaredFields();
        Arrays.stream(declaredFields)
                .filter(field -> field.getName().equals(key))
                .findFirst()
                .ifPresent(field -> setFieldData(field, bean, value));
        String beanStrNew = toJSONString(bean);
        log.info("==beanStrNew==" + beanStrNew);
        return beanStrNew;
    }

    @ApiMethod(description = "获取bean")
    @GetMapping("/getBean")
    public String getBean(
            @ApiQueryParam(name = "name", description = "bean名称")
            @RequestParam("name") String name) {
        Object bean = applicationContext.getBean(name);
        String beanStr = toJSONString(bean);
        log.info("==getBean==" + beanStr);
        return beanStr;
    }

    @SneakyThrows
    private void setFieldData(Field field, Object bean, String data) {
        // 注意这里要设置权限为true
        field.setAccessible(true);
        Class<?> type = field.getType();
        if (type.equals(String.class)) {
            field.set(bean, data);
        } else if (type.equals(Integer.class)) {
            field.set(bean, Integer.valueOf(data));
        } else if (type.equals(Long.class)) {
            field.set(bean, Long.valueOf(data));
        } else if (type.equals(Double.class)) {
            field.set(bean, Double.valueOf(data));
        } else if (type.equals(Short.class)) {
            field.set(bean, Short.valueOf(data));
        } else if (type.equals(Byte.class)) {
            field.set(bean, Byte.valueOf(data));
        } else if (type.equals(Boolean.class)) {
            field.set(bean, Boolean.valueOf(data));
        } else if (type.equals(Date.class)) {
            field.set(bean, new Date(Long.valueOf(data)));
        }
    }
}
