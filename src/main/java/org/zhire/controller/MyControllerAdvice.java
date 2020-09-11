package org.zhire.controller;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Enumeration;

/**
 * 控制器的通知
 */
@Slf4j
@ControllerAdvice(
        // 指定拦截的包
        basePackages = {"org.zhire.controller.*"},
        // 限定被标注为@Controller 的类才被拦截
        annotations = Controller.class)
public class MyControllerAdvice {

    // 绑定格式化、参数转换规则和增加验证器等
    @InitBinder
    public void initDataBinder(WebDataBinder binder) {
        // 自定义日期编辑器，限定格式为 yyyy-MM-dd，且参数不允许为空
        CustomDateEditor dateEditor =
                new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd"), false);
        // 注册自定义日期编辑器
        binder.registerCustomEditor(Date.class, dateEditor);
    }


    // 异常处理，使得被拦截的控制器方法发生异常时，都能用相同的视图响应
    @ExceptionHandler(value = Exception.class)
    public Object exception(HttpServletRequest request, Exception ex) {
        String content = ExceptionUtil.print(request, ex);
        log.error("异常：{}", content);
        return ex;
    }

    public static class ExceptionUtil {
        private final static char COLON = ':';
        private final static char NEWLINES = '\n';
        private static String HOSTNAME;

        static {
            try {
                HOSTNAME = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                HOSTNAME = "UnknownHost";
            }
        }

        @SuppressWarnings({"unchecked", "rawtypes"})
        static String print(HttpServletRequest request, Throwable e) {
            return getString(request, e, HOSTNAME, getRequestTime());
        }

        static String getString(HttpServletRequest request, Throwable e, String hostname, String requestTime) {
            StringBuffer sb = new StringBuffer();
            sb.append(ExceptionUtil.NEWLINES).append(ExceptionUtil.NEWLINES);
            sb.append("HostName").append(ExceptionUtil.COLON).append(hostname).append(ExceptionUtil.NEWLINES);
            sb.append("ErrorTime").append(ExceptionUtil.COLON).append(requestTime).append(ExceptionUtil.NEWLINES);
            sb.append("Error").append(ExceptionUtil.COLON).append(Throwables.getRootCause(e).getMessage()).append(ExceptionUtil.NEWLINES);
            sb.append("Resources").append(ExceptionUtil.COLON).append(request.getMethod()).append(" ").append(request.getRequestURI()).append(ExceptionUtil.NEWLINES);
            sb.append("RequestParameters {").append(ExceptionUtil.NEWLINES);
            request.getParameterMap().keySet().forEach(k -> sb.append("\t").append(k).append(ExceptionUtil.COLON).append(request.getParameter((String) k)).append(ExceptionUtil.NEWLINES));
            sb.append("}").append(ExceptionUtil.NEWLINES);
            sb.append("RequestHeaders {").append(ExceptionUtil.NEWLINES);
            Enumeration headers = request.getHeaderNames();
            while (headers.hasMoreElements()) {
                String key = headers.nextElement().toString();
                sb.append("\t").append(key).append(":").append(request.getHeader(key)).append(ExceptionUtil.NEWLINES);
            }
            sb.append("}").append(ExceptionUtil.NEWLINES);
            sb.append(ExceptionUtil.NEWLINES);
            sb.append(Throwables.getStackTraceAsString(e));
            return sb.toString();
        }

        private static String getRequestTime() {
            return LocalDateTime.now(ZoneOffset.systemDefault()).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
    }
}
