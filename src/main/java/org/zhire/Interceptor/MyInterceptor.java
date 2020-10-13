package org.zhire.Interceptor;


import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.zhire.utils.AuthToken;
import org.zhire.utils.DESUtils;
import org.zhire.utils.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@Slf4j
@Component
public class MyInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthToken authToken;

    private static final String TOKEN_NAME = "token";

    private static String TOKEN_DES_KEY = "tokenkey12345678";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader(TOKEN_NAME);
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        if (StringUtils.isEmpty(token)) {
            log.info("还没有登录，请登录后在操作！请求地址：[{}], 请求参数：[{}]", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()));
            R error = R.error("未登录！");
            response.getWriter().write(JSONObject.toJSONString(error));
            return false;
        }
        try {
            Map<String, String> stringMap = authToken.verify(token);
            String enBody = stringMap.get(TOKEN_NAME);
            String deJson = new String(DESUtils.decrypt(Base64.decodeBase64(enBody), Base64.decodeBase64(TOKEN_DES_KEY)));
            JSONObject jsonObject = JSONObject.parseObject(deJson);
            String userId = jsonObject.getString("userId");
            String userName = jsonObject.getString("userName");
            log.info("解析出userId为：[{}]", userId);
            request.setAttribute("userId", userId);
            request.setAttribute("userName", userName);
        } catch (Exception e) {
            log.info("token验证失败，过期或者不合法，请求地址：[{}], 请求参数：[{}]， token:[{}]", request.getRequestURI(), JSONObject.toJSONString(request.getParameterMap()), token);
            R error = R.error("token无效！");
            response.getWriter().write(JSONObject.toJSONString(error));
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("interceptor after");


    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("interceptor last");

    }
}
