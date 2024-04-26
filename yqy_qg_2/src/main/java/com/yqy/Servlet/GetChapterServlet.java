package com.yqy.Servlet;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Service.Impl.RegisterLessonServiceImpl;
import com.yqy.Utils.TimeUtils;
import com.yqy.pojo.Chapter;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.LessonInformation;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GetChapter")
public class GetChapterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        RegisterStudent registerStudent = JSONObject.parseObject(s, RegisterStudent.class);
        System.out.println(registerStudent);
        LessonServiceImpl lessonService = new LessonServiceImpl();
        RegisterLessonServiceImpl registerLessonService = new RegisterLessonServiceImpl();
        //判断是否在学习时间
        Lesson lesson = lessonService.GetLessonById(registerStudent.getLessonId());
        LessonInformation lessonInformation = lessonService.GetInformation(lesson.getInformationId());

        if (TimeUtils.TimeIs(lessonInformation.getBeginTime(),lessonInformation.getEndTime())){
            //判断课程内容是否有更改
            registerLessonService.JudgeLessonUpdate(registerStudent);
            ArrayList<CurrentStudentChapter> currentStudentChapters = lessonService.GetStudentChapter(registerStudent);

            String jsonString = JSON.toJSONString(currentStudentChapters);


            resp.setContentType("text/json;charset=utf-8");
            resp.getWriter().write(jsonString);
        }else {
            resp.getWriter().write("Time");
        }




    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        this.doGet(req,resp);
    }
}
