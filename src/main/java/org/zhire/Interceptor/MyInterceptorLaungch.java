package org.zhire.Interceptor;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.zhire.demo.spring.ioc.IOCUser;
import org.zhire.utils.LanguageUtil;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Component
@RestControllerAdvice(
        basePackages = {"org.zhire.controller.*"}, annotations = Controller.class)
public class MyInterceptorLaungch implements ResponseBodyAdvice<Object> {

    @Autowired
    private LanguageUtil languageUtil;

    @Override
    public boolean supports(MethodParameter returnType,
                            Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request, ServerHttpResponse response) {

        if (request.getHeaders().containsKey("appLanguage") &&
                request.getHeaders().getFirst("appLanguage").equals("EN_US")) {
            log.info("怕：{}", returnType);
//            获取controller org.zhire.controller.LaungchController
//            String name = Objects.requireNonNull(returnType.getMethod()).getDeclaringClass().getName();
            log.info("name:{}", Objects.requireNonNull(returnType.getMethod()).getName());
            if (body instanceof Map) {
                Map<Object, Object> map = (Map) body;
                Object data = map.get("data");
                List<Object> list = Lists.newArrayList();
                List<IOCUser> iocUsers = JSONArray.parseArray(JSON.toJSONString(data), IOCUser.class);
                iocUsers.forEach(k -> {
                    String address = k.getAddress();
                    String s = languageUtil.chToEn(address);
                    k.setAddress(s);
                    list.add(k);
                });
                map.put("data", list);
                body = map;
                log.info("body1:{}", JSON.toJSONString(body));
            }
        }
        log.info("body2:{}", JSON.toJSONString(body));
        return body;
    }
}
