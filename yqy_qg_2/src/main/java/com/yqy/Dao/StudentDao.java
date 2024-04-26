package com.yqy.Dao;

import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.User;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.util.ArrayList;

public interface StudentDao {
    //获取所有学生
    public ArrayList<Student> SelectAll();
    public Student SelectOneById(int id);
    //通过名字与密码查询
    public Student SelectOneByNameAndPassword(User user);
    //通过名字查询
    public Student SelectOneByName(String username);
    //添加学生
    public int insertStudent(Student student);
    //删除学生
    public void deleteStudentByName(String name);
    //修改学生信息
    public int updateStudent(Student student);
    //注册课程
    public int StudentRegisterLesson(RegisterStudent registerStudent);
    //注册课程的章节
    public int RegisterLessonChapter(CurrentStudentChapter currentStudentChapter);
    //注册章节的问题
    public int RegisterChapterQuestion(CurrentStudentQuestion currentStudentQuestion);
    //查询学生的课程
    public ArrayList<RegisterStudent> SelectMyLesson(String name);
    //查询学生课程的章节
    public ArrayList<CurrentStudentChapter> SelectMyChapter(RegisterStudent registerStudent);
    //查询学生章节的问题
    public ArrayList<CurrentStudentQuestion> SelectMyQuestion(CurrentStudentChapter currentStudentChapter);

    public int UpdateRegisterStudent(RegisterStudent registerStudent);

    public int UpdateCurrentChapter(CurrentStudentChapter currentStudentChapter);

    public int UpdateCurrentQuestion(CurrentStudentQuestion currentStudentQuestion);

    public Student SelectStudentByNumber(String Number);

    public RegisterStudent SelectRegisterStudent(RegisterStudent registerStudent);

    public CurrentStudentQuestion SelectMyQuestionById(CurrentStudentQuestion currentStudentQuestion);
    //查询注册学生通过课程Id
    public ArrayList<RegisterStudent> SelectRegisterStudentByLessonId(int id );

    public CurrentStudentChapter SelectMyChapterByNameAndId(CurrentStudentQuestion currentStudentQuestion);
    //删除学生
    public void deleteRegisterStudentById(RegisterStudent registerStudent);
    //删除章节
    public void deleteCurrentChapterById(CurrentStudentChapter currentStudentChapter);
    //删除问题
    public void deleteCurrentQuestionById(CurrentStudentQuestion currentStudentQuestion);
    public ArrayList<RegisterStudent> SelectRegisterStudentByName(String name);
}
