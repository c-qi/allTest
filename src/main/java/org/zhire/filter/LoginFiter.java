//package fun.chenqi.filter;
//
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
///**
// * @Author: chenqi
// * @Date: 2019.3.21 15:07
// */
//public class LoginFiter implements Filter
//{
//
//    String NO_LOGIN = "您还未登录";
//
//    //不需要登录就可以访问的路径(比如:注册登录等)
//    String[] includeUrls = new String[]{"/users/login","/login.html"};
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//
//    }
//
//    @Override
//    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        HttpServletResponse response = (HttpServletResponse) servletResponse;
//        HttpSession session = request.getSession(false);
//        String uri = request.getRequestURI();
//        System.out.println("filter url:"+uri);
//        //是否需要过滤
//        boolean needFilter = isNeedFilter(uri);
//        if (!needFilter) { //不需要过滤直接传给下一个过滤器
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else { //需要过滤器
//            // session中包含user对象,则是登录状态
//            if(session!=null&&session.getAttribute("user") != null){
//                // System.out.println("user:"+session.getAttribute("user"));
//                filterChain.doFilter(request, response);
//            }else{
//                String requestType = request.getHeader("X-Requested-With");
//                //判断是否是ajax请求（因为ajax页面不跳转，所以这块需要判断一下）
//                if(requestType!=null && "XMLHttpRequest".equals(requestType)){
//                    response.getWriter().write(this.NO_LOGIN);
//                }else{
//                    //重定向到登录页(需要在static文件夹下建立此html文件)
//                    response.sendRedirect(request.getContextPath()+"/login.html");
//                }
//                return;
//            }
//        }
//    }
//
//    /**
//     * @Description: 是否需要过滤
//     */
//    public boolean isNeedFilter(String uri) {
//        for (String includeUrl : includeUrls) {
//            if(includeUrl.equals(uri)) {
//                return false;
//            }
//        }
//        return true;
//
//    }
//
//    @Override
//    public void destroy() {
//
//    }
//}
