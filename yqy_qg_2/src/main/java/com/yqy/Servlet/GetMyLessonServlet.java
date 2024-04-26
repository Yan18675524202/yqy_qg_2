package com.yqy.Servlet;

import com.alibaba.fastjson.JSON;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Utils.JwtUtils;
import com.yqy.pojo.Lesson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

@WebServlet("/GetMyLesson")
public class GetMyLessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String jwt = req.getReader().readLine();
        Map<String, Object> claims = JwtUtils.parseJWT(jwt);
        //获取学生的已报名课程或教师的个人课程

        if (claims.get("role").equals("1")) {


            LessonServiceImpl getLessonService = new LessonServiceImpl();
            ArrayList<Lesson> lessons = getLessonService.GetPersonalLesson((String) claims.get("username"));




            String jsonString = JSON.toJSONString(lessons);

            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else {

            LessonServiceImpl getLessonService = new LessonServiceImpl();
            ArrayList<Lesson> lessons = getLessonService.GetLessonByTeacherName((String) claims.get("username"));


            String jsonString = JSON.toJSONString(lessons);

            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
