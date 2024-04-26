package com.yqy.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.RegisterLessonServiceImpl;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GetLessonStudent")
public class GetLessonStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        Lesson lesson = JSONObject.parseObject(s, Lesson.class);

        RegisterLessonServiceImpl registerLessonService = new RegisterLessonServiceImpl();
        ArrayList<RegisterStudent> registerStudents = registerLessonService.SelectRegisterStudentByLessonId(lesson.getId());

        String jsonString = JSON.toJSONString(registerStudents);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
