package com.yqy.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Service.Impl.RegisterLessonServiceImpl;
import com.yqy.pojo.Chapter;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;


@WebServlet("/Submit")
public class SubmitAnswerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        CurrentStudentQuestion currentStudentQuestion = JSONObject.parseObject(s, CurrentStudentQuestion.class);
        //获取学生的回答问题记录
        RegisterLessonServiceImpl registerLessonService = new RegisterLessonServiceImpl();
        CurrentStudentQuestion currentStudentQuestion1 = registerLessonService.SelectChapterById(currentStudentQuestion);
        //获取答案
        LessonServiceImpl lessonService = new LessonServiceImpl();
        Question question = lessonService.GetQuestionById(currentStudentQuestion1.getQuestionId());
        String Sign;
        //判断答案是否正确
        if (question.getRightAnswer().equals(currentStudentQuestion.getAnswer())){
             currentStudentQuestion1.setAnswer(currentStudentQuestion.getAnswer());
             //获取得分
            currentStudentQuestion1.setGetScore(question.getScore());
             currentStudentQuestion1.setQuestionFinish("回答正确");

             Sign = "Success";
            //更改回答记录
            registerLessonService.UpdateCurrentStudentQuestion(currentStudentQuestion1);

        }else {

            currentStudentQuestion1.setAnswer(currentStudentQuestion.getAnswer());
            currentStudentQuestion1.setQuestionFinish("回答错误");

            currentStudentQuestion1.setGetScore(0);

            Sign = "Error";
            //更改回答记录
            registerLessonService.UpdateCurrentStudentQuestion(currentStudentQuestion1);
        }


        //判断章节进度
        String s1 = registerLessonService.JudgeChapter(currentStudentQuestion1);




        resp.getWriter().write(Sign);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
