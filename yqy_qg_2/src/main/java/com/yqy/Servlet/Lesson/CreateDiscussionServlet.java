package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Discussion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CreateDiscussion")
public class CreateDiscussionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getReader().readLine();

        Discussion discussion = JSONObject.parseObject(s, Discussion.class);

        LessonServiceImpl lessonService = new LessonServiceImpl();
        int i = lessonService.CreateDiscussion(discussion);

        if (i==1){
            resp.getWriter().write("Success");
        }else {
            resp.getWriter().write("Error");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
