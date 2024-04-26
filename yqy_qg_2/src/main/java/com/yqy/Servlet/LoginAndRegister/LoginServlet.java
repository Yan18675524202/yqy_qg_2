package com.yqy.Servlet.LoginAndRegister;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.PersonService;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Utils.JwtUtils;
import com.yqy.Utils.ReadJSONUtils;
import com.yqy.Utils.SaltMD5Util;
import com.yqy.pojo.yonghu.Person;
import com.yqy.pojo.yonghu.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;



@WebServlet("/Login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        User user = ReadJSONUtils.parseJsonToClass(req, User.class);

        System.out.println(user);

        PersonService getPersonService = new PersonServiceImpl();



        //调用数据库查询
        Person person = getPersonService.getPersonByName(user.getLoginName(),user.getRadioValue());



        if (person != null&& SaltMD5Util.verifySaltPassword(user.getLoginPassword(),person.getPassword())) {
            user.setLoginPassword(person.getPassword());
            //生成jwt令牌
            Map<String,Object> claims = new HashMap<>();
            claims.put(new String("username"), person.getUsername());
            claims.put(new String("role"), user.getRadioValue());
            String jwtToken = JwtUtils.generateJwt(claims);


            // 创建JSON对象，将JWT令牌添加到其中
            JSONObject jsonResponse = new JSONObject();
            jsonResponse.put("user", user);
            jsonResponse.put("jwtToken", jwtToken);

            System.out.println("登录成功:用户+"+user.getLoginName());

            // 返回成功响应
            resp.getWriter().write(JSON.toJSONString(jsonResponse));
        } else {
            // 登录失败，返回错误信息给前端
            // 处理异常情况
            // 验证失败，返回错误响应
            resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            resp.getWriter().write("Invalid username or password");
        }






    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
