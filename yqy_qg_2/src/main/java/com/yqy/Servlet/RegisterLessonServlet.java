package com.yqy.Servlet;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Service.Impl.RegisterLessonServiceImpl;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.yonghu.Person;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/RegisterLesson")
public class RegisterLessonServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String s = req.getReader().readLine();

        RegisterStudent registerStudent = JSONObject.parseObject(s, RegisterStudent.class);
        RegisterLessonServiceImpl registerLessonService = new RegisterLessonServiceImpl();

        PersonServiceImpl personService = new PersonServiceImpl();
        RegisterStudent registerStudent1 = personService.SelectRegisterStudent(registerStudent);
        if (registerStudent1 == null){
            registerStudent.setGetScore(0);
            int i = registerLessonService.RegisterLesson(registerStudent);
            LessonServiceImpl getLessonService = new LessonServiceImpl();

            Lesson lesson = getLessonService.GetLessonById(registerStudent.getLessonId());
            lesson.setCurrentEnrollment(lesson.getCurrentEnrollment()+1);
            int i1 = getLessonService.updateLesson(lesson);
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
