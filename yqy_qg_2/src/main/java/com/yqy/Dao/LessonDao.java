package com.yqy.Dao;

import com.yqy.pojo.Chapter;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.LessonInformation;
import com.yqy.pojo.Question;

import java.util.ArrayList;
import java.util.List;

public interface LessonDao {
    //查询课室通过教师名字
    public ArrayList<Lesson> SelectByTeacherName(String name);
    //查询所有课程
    public ArrayList<Lesson> SelectAllLesson();
    //查询课程信息通过课程id
    public LessonInformation SelectInformationById(int id);
    //添加课程
    public int insertLesson(Lesson lesson);
    //添加章节
    public int insertChapter(Chapter chapter);
    //添加问题
    public int insertQuestion(Question question);
    //查询问题通过章节id
    public ArrayList<Question> SelectByChapterId(int id);
    //查询章节通过课程id
    public ArrayList<Chapter> SelectByLessonId(int id);
    //查询课程通过id
    public Lesson SelectLessonById(int id);
    //修改课程
    public int updateLesson(Lesson lesson);

    public Question SelectQuestionById(int id);

    public int insertLessonInformation(LessonInformation lessonInformation);

    public LessonInformation SelectInformationByName(String name);
    //删除课程
    public void deleteLessonById(int id);
    //删除章节(删除一个)
    public void deleteChapterById(int id);
    //删除问题(删除一个)
    public void deleteQuestionById(int id);
    //删除问题(删除章节的所有问题)
    public void deleteQuestionByChapterId(int id);

    public void deleteDiscussion(int id);

    public void deleteLessonInformation(int id);
    //修改问题
    public int updateQuestion(Question question);
}
