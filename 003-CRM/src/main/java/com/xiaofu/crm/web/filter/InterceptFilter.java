package com.xiaofu.crm.web.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
// 该过滤器是用来拦截请求的
// 用来做权限管理
public class InterceptFilter implements Filter {


    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        System.out.println("拦截器开始拦截");
        HttpServletRequest request= (HttpServletRequest) req;
        HttpServletResponse response=(HttpServletResponse)resp;
        String path = request.getServletPath();
        // 这里需要给登录页面和即将前往登录页面的URL地址放行，不做拦截
        // 不然会引起无限重定向的问题
        if("/setting/user/login.do".equals(path) || "/login.jsp".equals(path)){
            chain.doFilter(req, resp);
        }else{
            HttpSession session = request.getSession();
            // 如果session域中保留由user的信息，那么就放行
            if(session.getAttribute("user") != null){
                chain.doFilter(req, resp);
            }else{
                // 如果session域中没有保存user的信息，那么就使用重定向跳转到登录页面
                response.sendRedirect(request.getContextPath()+"/login.jsp");
            }


        }


    }



}
