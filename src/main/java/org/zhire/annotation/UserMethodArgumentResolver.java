package org.zhire.annotation;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.zhire.demo.spring.ioc.IOCUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 给@User注解赋值
 **/
public class UserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.hasParameterAnnotation(User.class);
    }

    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest webRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        String userId = (String) servletRequest.getAttribute("userId");
        String userName = (String) servletRequest.getAttribute("userName");
        // 在这里可以根据前端传的用户ID，查库获取用户信息，获取解析token获取用户信息，其他地方使用@User注解获取用户信息
        // String token = webRequest.getParameter("token");
        // String token = webRequest.getHeader("token");
        IOCUser user = new IOCUser();
        user.setId(Integer.parseInt(userId + ""));
        user.setName(userName);
        return user;
    }
}
