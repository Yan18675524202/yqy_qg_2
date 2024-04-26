package com.yqy.Service;

import com.yqy.pojo.yonghu.Person;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghu.User;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;

public interface PersonService {
    public ArrayList<RegisterStudent> GetGoodStudent(ArrayList<Student> students);
    public ArrayList<Student> SelectAll();
    public int updateStudent(Student student) throws IOException;
    public int updateTeacher(Teacher teacher) throws IOException;
    public int addPerson(Student student) throws IOException;

    public Person getPerson(User user) throws IOException;

    public Person getPersonByName(String username ,String role) throws IOException;

    public Student getStudentBtNumber(String Number) throws IOException;

    public RegisterStudent SelectRegisterStudent(RegisterStudent registerStudent) throws IOException;
}
