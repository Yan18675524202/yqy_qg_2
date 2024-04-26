package com.yqy.Servlet.Person;

import com.alibaba.fastjson.JSON;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Utils.ReadJSONUtils;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghu.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UpdatePerson")
public class UpdatePersonServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String sign = req.getParameter("sign");

       // System.out.println(sign);
        if (sign.equals("1")){
            Teacher teacher = ReadJSONUtils.parseJsonToClass(req, Teacher.class);
            PersonServiceImpl personService = new PersonServiceImpl();
            int i = personService.updateTeacher(teacher);
            resp.getWriter().write(String.valueOf(i));
        }else {
            Student student = ReadJSONUtils.parseJsonToClass(req, Student.class);
            PersonServiceImpl personService = new PersonServiceImpl();
            personService.updateStudent(student);
        }



    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
