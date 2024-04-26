package com.yqy.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Chapter;
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
import java.util.ArrayList;

@WebServlet("/GetQuestion")
public class GetQuestionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        CurrentStudentChapter currentStudentChapter = JSONObject.parseObject(s, CurrentStudentChapter.class);

        LessonServiceImpl lessonService = new LessonServiceImpl();
        ArrayList<CurrentStudentQuestion> currentStudentQuestions = lessonService.GetStudentQuestion(currentStudentChapter);

        String jsonString = JSON.toJSONString(currentStudentQuestions);


        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
