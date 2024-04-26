package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSON;
import com.yqy.Service.LessonService;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.LessonInformation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

@WebServlet("/GetLesson")
public class GetLessonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        LessonService getLessonService = new LessonServiceImpl();
        String sign = req.getParameter("sign");
        if (sign.equals("1")){




            ArrayList<Lesson> lessons = getLessonService.selectAllLesson();




            String jsonString = JSON.toJSONString(lessons);

            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else {
            ArrayList<Lesson> lessons = getLessonService.selectAllLesson();
            ArrayList<Lesson> lessons1 = getLessonService.JudgeGoodLesson(lessons);

            String jsonString = JSON.toJSONString(lessons1);

            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);


        }



        }



    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
