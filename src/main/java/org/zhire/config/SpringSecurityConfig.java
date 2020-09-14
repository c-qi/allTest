package org.zhire.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    // 使用用户名称查询密码
    String pwdQuery = " select user_name, pass_word, available "
            + " from t_user where user_name = ? ";
    // 使用用户名称查询角色信息
    String roleQuery = " select u.user_name, r.role_name "
            + " from t_user u, t_user_role ur, t_role r "
            + "where u.id = ur.user_id and r.id = ur.role_id"
            + " and u.user_name = ?";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         密码编码器
//        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        // 使用内存存储
//        auth.inMemoryAuthentication()
//                // 设置密码编码器
//                .passwordEncoder(passwordEncoder)
//                // 注册用户 admin，密码为 abc,并赋予 USER 和 ADMIN 的角色权限
//                .withUser("admin")
//                // 可通过 passwordEncoder.encode("abc")得到加密后的密码
//                .password("$2a$10$5OpFvQlTIbM9Bx2pfbKVzurdQXL9zndm1SrAjEkPyIuCcZ7CqR6je")
//                .roles("USER", "ADMIN")
//                // 连接方法 and
//                .and()
//                // 注册用户 myuser，密码为 123456,并赋予 USER 的角色权限
//                .withUser("myuser")
//                // 可通过 passwordEncoder.encode("123456")得到加密后的密码
//                .password("$2a$10$ezW1uns4ZV63FgCLiFHJqOI6oR6jaaPYn33jNrxnkHZ.ayAFmfzLS")
//                .roles("USER");
        // 密码编码器
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        auth.jdbcAuthentication()
                // 密码编码器
                .passwordEncoder(passwordEncoder)
                // 数据源
                .dataSource(dataSource)
                // 查询用户，自动判断密码是否一致
                .usersByUsernameQuery(pwdQuery)
                // 赋予权限
                .authoritiesByUsernameQuery(roleQuery);

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 限定签名后的权限
        http.
                /* ########第一段######## */
                        authorizeRequests()
                // 限定"/user/welcome"请求赋予角色 ROLE_USER 或者 ROLE_ADMIN
                //.antMatchers("/users/**", "/user/**").hasAnyRole("USER", "ADMIN")
                // 限定"/admin/"下所有请求权限赋予角色 ROLE_ADMIN
                .antMatchers("/interceptor/**").hasAuthority("ROLE_ADMIN")
                // 其他路径允许签名后访问
                .anyRequest().permitAll()
                /* ######## 第二段 ######## */
                /** and 代表连接词 **/
                // 对于没有配置权限的其他请求允许匿名访问
                .and().anonymous()
                /* ######## 第三段 ######## */
                // 使用 Spring Security 默认的登录页面
                .and().formLogin()
                // 启动 HTTP 基础验证
                .and().httpBasic();

//        http
//                // 访问/admin 下的请求需要管理员权限
//                .authorizeRequests().antMatchers("/pdf/**").access("hasRole('ADMIN')")
//                // 启用 remember me 功能
//                .and().rememberMe().tokenValiditySeconds(86400).key("remember-me-key")
//                // 启用 HTTP Batic 功能
//                .and().httpBasic()
//                // 通过签名后可以访问任何请求
//                .and().authorizeRequests().antMatchers("/**").permitAll()
//                // 设置登录页和默认的跳转路径
//                .and().formLogin().loginPage("/login/page")
//                .defaultSuccessUrl("/admin/welcome1");

    }

}
