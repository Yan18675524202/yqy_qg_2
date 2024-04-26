package com.yqy.Servlet.Person;

import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Utils.ReadJSONUtils;
import com.yqy.Utils.SaltMD5Util;
import com.yqy.pojo.yonghu.Person;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.Teacher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/UpdatePassword")
public class UpdatePasswordServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        String sign = req.getParameter("sign");

        // System.out.println(sign);
        if (sign.equals("1")){
            Teacher teacher = ReadJSONUtils.parseJsonToClass(req, Teacher.class);
            PersonServiceImpl personService = new PersonServiceImpl();
            Person personByName = personService.getPersonByName(teacher.getUsername(), "2");


            String s = SaltMD5Util.generateSaltPassword(teacher.getPassword());

            personByName.setPassword(s);
            int i = personService.updateTeacher((Teacher) personByName);


            if (i==1){
                resp.getWriter().write("Success");
            }else {
                resp.getWriter().write("Error");
            }
        }else {
            Student student = ReadJSONUtils.parseJsonToClass(req, Student.class);
            PersonServiceImpl personService = new PersonServiceImpl();
            Person personByName = personService.getPersonByName(student.getUsername(), "1");
            String s = SaltMD5Util.generateSaltPassword(student.getPassword());

            personByName.setPassword(s);
            int i = personService.updateStudent((Student) personByName);

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
