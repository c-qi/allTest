package org.zhire.controller;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 控制器的通知
 */
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
    public Object exception(Exception ex) {
        System.out.println("异常：" + ex.getMessage());
        // 返回异常视图
        return ex;
    }
}
