package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.pojo.Chapter;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.LessonInformation;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/CreateLesson")
public class CreateLessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String sign = req.getParameter("sign");
        String s = req.getReader().readLine();
        LessonServiceImpl lessonService = new LessonServiceImpl();

        if (sign.equals("1")){
            //创建新课程
            Lesson lesson = JSONObject.parseObject(s, Lesson.class);
            lesson.setCurrentEnrollment(0);

            LessonInformation lessonInformation = JSONObject.parseObject(s, LessonInformation.class);



            int i1 = lessonService.CreateLessonInformation(lessonInformation);
            int i = lessonService.CreateLesson(lesson);


            if (i==1 && i1 ==1){
                resp.getWriter().write("Success");
            }else {
                resp.getWriter().write("Error");
            }


        } else if (sign.equals("2")) {
            //创建新章节
            Chapter chapter = JSONObject.parseObject(s, Chapter.class);
            int i = lessonService.CreateChapter(chapter);
            if (i==1){
                resp.getWriter().write("Success");
            }else {
                resp.getWriter().write("Error");
            }


        }else {
            //创建新问题
            Question question = JSONObject.parseObject(s, Question.class);
            int i = lessonService.CreateQuestion(question);
            if (i==1){
                resp.getWriter().write("Success");
            }else {
                resp.getWriter().write("Error");
            }


        }





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
