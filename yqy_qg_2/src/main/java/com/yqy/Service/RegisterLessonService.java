package com.yqy.Service;

import com.yqy.Mybatis.session.SqlSession;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;

public interface RegisterLessonService {

    public int RegisterLesson(RegisterStudent registerStudent) throws IOException;

    public int RegisterChapter(CurrentStudentChapter currentStudentChapter, SqlSession sqlSession);

    public int RegisterQuestion(CurrentStudentQuestion currentStudentQuestion, SqlSession sqlSession);


    public CurrentStudentQuestion SelectChapterById(CurrentStudentQuestion currentStudentQuestion);

    public int UpdateCurrentStudentQuestion(CurrentStudentQuestion currentStudentQuestion);

    public ArrayList<RegisterStudent> SelectRegisterStudentByLessonId(int id);

    public String JudgeChapter(CurrentStudentQuestion currentStudentQuestion);
    public String JudgeLesson(CurrentStudentChapter currentStudentChapter);
    //判断课程修改
    public void JudgeLessonUpdate(RegisterStudent registerStudent);
    //判断章节修改
    public void JudgeChapterUpdate(CurrentStudentChapter currentStudentChapter);
    //删除学生
    public void DeleteRegisterStudent(RegisterStudent registerStudent);
    //删除章节
    public void DeleteCurrentChapter(CurrentStudentChapter currentStudentChapter);
    //删除问题
    public void DeleteCurrentQuestion(CurrentStudentQuestion currentStudentQuestion);

}