package com.yqy.Filter;

import com.yqy.Utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.Map;
@WebFilter("/*")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {


        HttpServletRequest req = (HttpServletRequest) request;




        String[] urls = {"/login.html","/imgs/","/css/","/Login","/register.html",
           "/js/"   ,  "/element-ui/","/Register"} ;

        String url = req.getRequestURL().toString();
        for (String u: urls) {
            if (url.contains(u)){
                chain.doFilter(request,response);
                return;
            }
        }
        String[] urls1 = {"/studentHome.html","/teacherHome.html",} ;
        for (String u: urls1) {
            if (url.contains(u)){

                String authorization = req.getParameter("Authorization");
                if (!authorization.isEmpty()){
                    chain.doFilter(request,response);
                    return;
                }

            }
        }
        String token = req.getHeader("Authorization");
        Map<String, Object> claims = JwtUtils.parseJWT(token);
        if (claims != null && claims.get("username") != null) {
            chain.doFilter(request, response);
        }


    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
