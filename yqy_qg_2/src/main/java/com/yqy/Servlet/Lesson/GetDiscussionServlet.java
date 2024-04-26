package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Discussion;
import com.yqy.pojo.Lesson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GetDiscussion")
public class GetDiscussionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        Lesson lesson = JSONObject.parseObject(s, Lesson.class);

        LessonServiceImpl getLessonService = new LessonServiceImpl();
        ArrayList<Discussion> discussions = getLessonService.GetDiscussion(lesson.getId());


        String jsonString = JSON.toJSONString(discussions);
        System.out.println(jsonString);



        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
