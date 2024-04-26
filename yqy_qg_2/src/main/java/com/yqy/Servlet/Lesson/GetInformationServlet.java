package com.yqy.Servlet.Lesson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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

@WebServlet("/GetInformation")
public class GetInformationServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getReader().readLine();

        Lesson lesson = JSONObject.parseObject(s, Lesson.class);



        //Lesson lesson = JSON.parseObject(s, Lesson.class);
      //  System.out.println(lesson);

       // int informationId = lesson.getInformationId();
       LessonService getLessonService = new LessonServiceImpl();


       LessonInformation lessonInformation = getLessonService.GetInformation(lesson.getInformationId());




       // String jsonString = JSON.toJSONString(lessonInformation);


        String jsonString = JSON.toJSONString(lessonInformation);



        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);









    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }


}
