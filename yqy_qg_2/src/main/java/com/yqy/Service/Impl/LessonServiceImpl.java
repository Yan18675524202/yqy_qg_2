package com.yqy.Service.Impl;

import com.yqy.Dao.DiscussDao;
import com.yqy.Dao.LessonDao;
import com.yqy.Dao.StudentDao;
import com.yqy.Dao.TeacherDao;
import com.yqy.Mybatis.io.Resources;
import com.yqy.Mybatis.session.SqlSession;
import com.yqy.Mybatis.session.SqlSessionFactory;
import com.yqy.Mybatis.session.SqlSessionFactoryBuilder;
import com.yqy.Service.LessonService;
import com.yqy.Utils.TimeUtils;
import com.yqy.pojo.*;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;

public class LessonServiceImpl implements LessonService {

    static {
        SqlSessionFactory sqlSessionFactory = null;
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsReader("mybatisConfig.xml"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        sqlSession = sqlSessionFactory.openSession();
    }

    static SqlSession sqlSession;

    //查询所有课程
    @Override
    public ArrayList<Lesson> selectAllLesson() {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);

        ArrayList<Lesson> lessons = mapper.SelectAllLesson();


        return lessons;


    }

    //查询教师
    @Override
    public Teacher selectTeacher(int id) {

        TeacherDao mapper = sqlSession.getMapper(TeacherDao.class);

        Teacher teacher = mapper.SelectOneById(id);

        return teacher;


    }

    //查询课程详细信息
    @Override
    public LessonInformation GetInformation(int id) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);

        return mapper.SelectInformationById(id);


    }

    //查询讨论区
    @Override
    public ArrayList<Discussion> GetDiscussion(int id) {

        DiscussDao mapper = sqlSession.getMapper(DiscussDao.class);
        ArrayList<Discussion> discussions = mapper.SelectByLessonId(id);
        return discussions;

    }

    //查询个人课程
    @Override
    public ArrayList<Lesson> GetPersonalLesson(String name) {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        ArrayList<RegisterStudent> registerStudents = mapper.SelectMyLesson(name);

        ArrayList<Lesson> lessons = new ArrayList<>();
        ;
        for (RegisterStudent s : registerStudents) {
            LessonDao mapper1 = sqlSession.getMapper(LessonDao.class);
            Lesson lesson = mapper1.SelectLessonById(s.getLessonId());
            if (lesson == null) {
                mapper.deleteRegisterStudentById(s);
            } else {
                lessons.add(lesson);
            }


        }


        return lessons;

    }

    //修改课程
    @Override
    public int updateLesson(Lesson lesson) {

        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        int i = mapper.updateLesson(lesson);
        return i;

    }

    //通过Id查询课程
    @Override
    public Lesson GetLessonById(int id) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        Lesson lesson = mapper.SelectLessonById(id);
        return lesson;
    }

    //通过教师名查询课程
    @Override
    public ArrayList<Lesson> GetLessonByTeacherName(String name) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        ArrayList<Lesson> lessons = mapper.SelectByTeacherName(name);

        return lessons;

    }

    @Override
    public ArrayList<Chapter> GetChapterByLessonId(int id) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        ArrayList<Chapter> chapters = mapper.SelectByLessonId(id);
        return chapters;

    }

    @Override
    public ArrayList<Question> GetQuestionByChapterId(int id) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        ArrayList<Question> questions = mapper.SelectByChapterId(id);

        return questions;
    }

    @Override
    public ArrayList<CurrentStudentChapter> GetStudentChapter(RegisterStudent registerStudent) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        ArrayList<CurrentStudentChapter> currentStudentChapters = mapper.SelectMyChapter(registerStudent);

        return currentStudentChapters;


    }

    @Override
    public ArrayList<CurrentStudentQuestion> GetStudentQuestion(CurrentStudentChapter currentStudentChapter) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        ArrayList<CurrentStudentQuestion> currentStudentQuestions = mapper.SelectMyQuestion(currentStudentChapter);

        return currentStudentQuestions;
    }

    @Override
    public Question GetQuestionById(int id) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        Question question = mapper.SelectQuestionById(id);
        return question;

    }

    @Override
    public int CreateLesson(Lesson lesson) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        LessonInformation lessonInformation = mapper.SelectInformationByName(lesson.getName());
        lesson.setInformationId(lessonInformation.getId());
        int i = mapper.insertLesson(lesson);
        return i;
    }

    @Override
    public int CreateLessonInformation(LessonInformation lessonInformation) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        //处理时间
        String beginTime = lessonInformation.getBeginTime();
        String endTime = lessonInformation.getEndTime();
        String[] split = beginTime.split("T");
        String[] split1 = endTime.split("T");
        lessonInformation.setBeginTime(TimeUtils.dealTime(split[0]));
        lessonInformation.setEndTime(TimeUtils.dealTime(split1[0]));
        int i = mapper.insertLessonInformation(lessonInformation);

        return i;


    }

    @Override
    public int CreateChapter(Chapter chapter) {

        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        int i = mapper.insertChapter(chapter);
        return i;
    }

    @Override
    public int CreateQuestion(Question question) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        int i = mapper.insertQuestion(question);
        return i;
    }

    @Override
    public void DeleteQuestion(int id) {


        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        mapper.deleteQuestionByChapterId(id);

    }

    @Override
    public void DeleteChapter(Chapter chapter) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        ArrayList<Question> questions = mapper.SelectByChapterId(chapter.getId());
        if (!questions.isEmpty()) {
            mapper.deleteQuestionByChapterId(chapter.getId());
        }


    }

    @Override
    public void DeleteLesson(Lesson lesson) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        //删除课程详细信息
        LessonInformation lessonInformation = mapper.SelectInformationByName(lesson.getName());
        mapper.deleteLessonInformation(lessonInformation.getId());
        //删除评论区
        DiscussDao mapper1 = sqlSession.getMapper(DiscussDao.class);
        for (Discussion discussion : mapper1.SelectByLessonId(lesson.getId())) {
            mapper.deleteDiscussion(discussion.getId());
        }

        //删除章节
        ArrayList<Chapter> chapters = mapper.SelectByLessonId(lesson.getId());
        if (!chapters.isEmpty()) {
            for (Chapter c : chapters) {
                DeleteChapter(c);
                mapper.deleteChapterById(c.getId());
            }


        }
        mapper.deleteLessonById(lesson.getId());


    }

    @Override
    public int CreateDiscussion(Discussion discussion) {

        String time = TimeUtils.getTime();

        discussion.setTime(time);
        DiscussDao mapper = sqlSession.getMapper(DiscussDao.class);
        int i = mapper.insertDiscussion(discussion);
        return i;


    }

    @Override
    public ArrayList<Lesson> JudgeGoodLesson(ArrayList<Lesson> lessons) {
        if (lessons.isEmpty()) {
                //无课程直接返回NULL
            return null;
        } else {
            //先得到报名人数最多的课程
            int count = 1 + lessons.size() / 5;

            if (count > 10) {
                count = 10;
            }
            lessons.sort(new Comparator<Lesson>() {
                @Override
                public int compare(Lesson o1, Lesson o2) {
                    return o2.getCurrentEnrollment()-o1.getCurrentEnrollment();
                }
            });
            ArrayList<Lesson> goodLessons = new ArrayList<>();
            for (int i = 0; i < count; i++) {
                goodLessons.add(lessons.get(i));
            }
            return goodLessons;



        }

    }

    @Override
    public int addImage(int id, String url,String sign) {
        LessonDao mapper = sqlSession.getMapper(LessonDao.class);
        int i =0;
        if (sign.equals("1")){
            Chapter chapter = mapper.SelectChapterById(id);
            chapter.setImagePath(url);
            i = mapper.updateChapter(chapter);
        }else {
            Question question = mapper.SelectQuestionById(id);
            question.setImagePath(url);
            i = mapper.updateQuestion(question);
        }



        return i;
    }


}
