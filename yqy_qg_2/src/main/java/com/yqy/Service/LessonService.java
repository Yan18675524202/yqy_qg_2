package com.yqy.Service;

import com.yqy.Dao.LessonDao;
import com.yqy.pojo.*;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;

public interface LessonService {

    public ArrayList<Lesson> selectAllLesson() ;

    public Teacher selectTeacher(int id) ;

    public LessonInformation GetInformation(int id) ;

    public ArrayList<Discussion> GetDiscussion(int id) ;

    public ArrayList<Lesson> GetPersonalLesson(String name) ;

    public int updateLesson(Lesson lesson);

    public Lesson GetLessonById(int id);

    public ArrayList<Lesson> GetLessonByTeacherName(String name);

    public ArrayList<Chapter> GetChapterByLessonId(int id);

    public ArrayList<Question> GetQuestionByChapterId(int id);

    public ArrayList<CurrentStudentChapter> GetStudentChapter(RegisterStudent registerStudent);

    public ArrayList<CurrentStudentQuestion> GetStudentQuestion(CurrentStudentChapter currentStudentChapter);

    public Question GetQuestionById(int id);
    //创建课程
    public int CreateLesson(Lesson lesson);
    //创建课程信息
    public int CreateLessonInformation(LessonInformation lessonInformation);
    //创建章节
    public int CreateChapter(Chapter chapter);
    //创建问题
    public int CreateQuestion(Question question);
    //删除问题
    public void DeleteQuestion(int id);
    //删除章节
    public void DeleteChapter(Chapter chapter);
    //删除课程
    public void DeleteLesson(Lesson lesson);
    //创建评论
    public int CreateDiscussion(Discussion discussion);
    //判断热门课程
    public ArrayList<Lesson> JudgeGoodLesson(ArrayList<Lesson> lessons);
    //添加图片
    public int addImage(int id,String url);
}
