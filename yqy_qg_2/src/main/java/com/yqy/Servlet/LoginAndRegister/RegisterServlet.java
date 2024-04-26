package com.yqy.Servlet.LoginAndRegister;

import com.alibaba.fastjson.JSONObject;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Utils.Regex;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.yonghu.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String s = req.getReader().readLine();


        Student student = JSONObject.parseObject(s, Student.class);

        if (Regex.validateName(student.getUsername())&&
            Regex.validatePassword(student.getPassword())&&
            Regex.validateStudentID(student.getStudentNumber())){

            PersonServiceImpl personService = new PersonServiceImpl();
            Student studentBtNumber = personService.getStudentBtNumber(student.getStudentNumber());

            if (studentBtNumber != null){
                resp.getWriter().write("Error");
            }else {
                int i = personService.addPerson(student);
                resp.getWriter().write(String.valueOf(i));
            }


        }else {
            resp.getWriter().write("Error");
        }





    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req,resp);
    }
}
