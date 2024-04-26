package com.yqy.Service.Impl;

import com.yqy.Dao.StudentDao;
import com.yqy.Dao.TeacherDao;
import com.yqy.Mybatis.io.Resources;
import com.yqy.Mybatis.session.SqlSession;
import com.yqy.Mybatis.session.SqlSessionFactory;
import com.yqy.Mybatis.session.SqlSessionFactoryBuilder;
import com.yqy.Service.PersonService;
import com.yqy.Utils.SaltMD5Util;
import com.yqy.pojo.yonghu.Person;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghu.User;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class PersonServiceImpl implements PersonService {

    static{
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatisConfig.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSession = sqlSessionFactory.openSession();
    }
    static SqlSession sqlSession;

    @Override
    public ArrayList<RegisterStudent> GetGoodStudent(ArrayList<Student> students) {

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        int count = 1 + students.size() / 3;

        if (count > 5) {
            count = 5;
        }
        ArrayList<RegisterStudent> registerStudents =new ArrayList<>();
        for (Student s: students) {
            ArrayList<RegisterStudent> registerStudents1 = mapper.SelectRegisterStudentByName(s.getUsername());
            RegisterStudent rs = new RegisterStudent();
            rs.setStudentName(s.getUsername());
            for (RegisterStudent r: registerStudents1) {
                rs.setGetScore(rs.getGetScore()+r.getGetScore());
            }
            registerStudents.add(rs);
        }
        registerStudents.sort(new Comparator<RegisterStudent>() {
            @Override
            public int compare(RegisterStudent o1, RegisterStudent o2) {
                return o2.getGetScore()-o1.getGetScore();
            }
        });

        ArrayList<RegisterStudent> goodStudent =new ArrayList<>();
        for (int i = 0; i < count; i++) {
            goodStudent.add(registerStudents.get(i));
        }
        return  goodStudent;

    }

    @Override
    public ArrayList<Student> SelectAll() {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        return mapper.SelectAll();
    }

    @Override
    public int updateStudent(Student student) throws IOException {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        int i = mapper.updateStudent(student);


        return i;
    }

    @Override
    public int updateTeacher(Teacher teacher) throws IOException {


        TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);
        int i = mapper.updateTeacher(teacher);
        return i;
    }


    @Override
    public int addPerson(Student student) throws IOException {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        student.setPersonInformation("无");

        String s = SaltMD5Util.generateSaltPassword(student.getPassword());
        student.setPassword(s);

        int i = mapper.insertStudent(student);

        return i;


    }

    @Override
    public Person getPerson(User user) throws IOException {



        if (user.getRadioValue().equals("1")) {
            StudentDao mapper = sqlSession.getMapper(StudentDao.class);

            return mapper.SelectOneByNameAndPassword(user);


        }else {

            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);

            return mapper.SelectOneByNameAndPassword(user);

        }


    }

    @Override
    public Person getPersonByName(String username,String role) throws IOException {


        if (role.equals("1")) {
            StudentDao mapper = sqlSession.getMapper(StudentDao.class);

            return mapper.SelectOneByName(username);


        }else {

            TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);

            return mapper.SelectOneByName(username);

        }


    }

    @Override
    public Student getStudentBtNumber(String Number) throws IOException {

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        Student student = mapper.SelectStudentByNumber(Number);
        return student;
    }

    @Override
    public RegisterStudent SelectRegisterStudent(RegisterStudent registerStudent) throws IOException {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        RegisterStudent registerStudent1 = mapper.SelectRegisterStudent(registerStudent);

        return registerStudent1;


    }
}
