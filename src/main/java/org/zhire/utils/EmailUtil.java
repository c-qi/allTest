package org.zhire.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * java发送邮件
 */
@Slf4j
public class EmailUtil {
    private final static String SMTP = "smtp.xx.com";
    private final static String USERNAME = "null@xx.com";
    private final static String PASSWORD = "xxxxx";
    private final static String FROM = "null@xx.com";

    public static void send(String subject, String content, List<String> to, List<String> cc) {
        try {
            Email email = new SimpleEmail();
            email.setHostName(SMTP);
            email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
            email.setSSLOnConnect(true);
            email.setSubject(subject);
            email.setFrom(FROM);
            email.setMsg(content);
            for (String c : cc) {
                email.addCc(c);
            }
            for (String t : to) {
                email.addTo(t);
            }
            String sendResult = email.send();
            log.info("邮件发送结果：{}", sendResult);
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public static void sendHtml(String subject, String content, List<String> to, List<String> cc) {

        try {
            Email email = new SimpleEmail();
            email.setHostName(SMTP);
            email.setAuthenticator(new DefaultAuthenticator(USERNAME, PASSWORD));
            email.setSSLOnConnect(true);
            email.setSubject(subject);
            email.setFrom(FROM);
            //email.setMsg(content);
            email.setContent(content, "text/html;charset=utf-8");
            for (String c : cc) {
                email.addCc(c);
            }
            for (String t : to) {
                email.addTo(t);
            }
            email.send();
        } catch (Exception ex) {
            log.error(ex.getMessage(), ex);
        }
    }

    public static void main(String[] args) {
        List<String> list = Collections.singletonList("xxx@xx.com");
        send("验证码", "您的验证是：" + 3124 + " 验证码有效期为5分钟", list, new ArrayList<>());
    }
}
