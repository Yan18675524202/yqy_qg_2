package com.yqy.Servlet.Person;

import com.alibaba.fastjson.JSON;
import com.yqy.Service.PersonService;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Utils.JwtUtils;
import com.yqy.pojo.yonghu.Person;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

@WebServlet("/GetPerson")
public class GetPersonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取表单参数
        String jwt = req.getReader().readLine();
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);


        Object username = null;
        Object role= null;
        if (claims != null) {
            username = claims.get("username");
            role = claims.get("role");
        }


        PersonService getPersonService = new PersonServiceImpl();



        //调用数据库查询
        Person person = getPersonService.getPersonByName((String) username, (String) role);
        System.out.println(person);

        String jsonString = JSON.toJSONString(person);



        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
