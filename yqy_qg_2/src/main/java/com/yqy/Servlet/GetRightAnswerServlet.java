package com.yqy.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/GetRightAnswer")
public class GetRightAnswerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String s = req.getReader().readLine();

        CurrentStudentQuestion currentStudentQuestion = JSONObject.parseObject(s, CurrentStudentQuestion.class);

        LessonServiceImpl lessonService = new LessonServiceImpl();
        Question question = lessonService.GetQuestionById(currentStudentQuestion.getQuestionId());

        resp.getWriter().write(question.getRightAnswer());


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
