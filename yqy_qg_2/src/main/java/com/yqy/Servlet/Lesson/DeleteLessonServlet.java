package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Chapter;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/DeleteLesson")
public class DeleteLessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getReader().readLine();
        String sign = req.getParameter("sign");
        LessonServiceImpl lessonService = new LessonServiceImpl();

        if (sign.equals("1")){
            //删除课程

            Lesson lesson = JSONObject.parseObject(s, Lesson.class);
            lessonService.DeleteLesson(lesson);

            resp.getWriter().write("Success");

        } else if (sign.equals("2")) {
            //删除章节
            Chapter chapter = JSONObject.parseObject(s, Chapter.class);
            lessonService.DeleteChapter(chapter);
            resp.getWriter().write("Success");
        }else {
            //删除问题
            Question question = JSONObject.parseObject(s, Question.class);
            lessonService.DeleteQuestion(question.getId());
            resp.getWriter().write("Success");
        }


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
