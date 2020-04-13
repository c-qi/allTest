package org.zhire.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: chenqi
 * @Date: 2019.4.4 09:22
 */
public class ParamFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        String uri = request.getRequestURI();
        if (!uri.equals("/test/paramFilter")) {
            chain.doFilter(request, response);
        } else {
            String name = request.getParameter("name");
            if (name.equals("cnm")) {
                throw new RuntimeException("敏感词！");
            }
        }

    }

    @Override
    public void destroy() {

    }
}
