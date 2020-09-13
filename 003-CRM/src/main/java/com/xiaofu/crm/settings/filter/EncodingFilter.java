package com.xiaofu.crm.settings.filter;

import javax.servlet.*;
import java.io.IOException;
// 这是一个过滤器
// 凡是http://localhost:8080/xxx.do的URL地址就会先通过该过滤器
public class EncodingFilter implements Filter {

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        // 设置字符集
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/json;charset=utf-8");
        // 然后需要将请求放行
        chain.doFilter(req, resp);
    }



}
