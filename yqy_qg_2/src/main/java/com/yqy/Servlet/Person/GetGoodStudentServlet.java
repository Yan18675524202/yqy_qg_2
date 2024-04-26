package com.yqy.Servlet.Person;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/GetGoodStudent")
public class GetGoodStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        PersonServiceImpl personService = new PersonServiceImpl();

        ArrayList<Student> students = personService.SelectAll();

        ArrayList<RegisterStudent> registerStudents = personService.GetGoodStudent(students);

        String jsonString = JSON.toJSONString(registerStudents);



        resp.setContentType("text/json;charset=utf-8");
        resp.getWriter().write(jsonString);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
