package com.yqy.Service.Impl;

import com.yqy.Dao.LessonDao;
import com.yqy.Dao.StudentDao;
import com.yqy.Mybatis.io.Resources;
import com.yqy.Mybatis.session.SqlSession;
import com.yqy.Mybatis.session.SqlSessionFactory;
import com.yqy.Mybatis.session.SqlSessionFactoryBuilder;
import com.yqy.Service.RegisterLessonService;
import com.yqy.pojo.Chapter;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghuStudy.CurrentStudentChapter;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;

import java.io.IOException;
import java.util.ArrayList;

public class RegisterLessonServiceImpl implements RegisterLessonService {




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


    //注册课程
    @Override
    public int RegisterLesson(RegisterStudent registerStudent) throws IOException {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);

        int i = mapper.StudentRegisterLesson(registerStudent);


        LessonDao mapper1 = sqlSession.getMapper(LessonDao.class);
        ArrayList<Chapter> chapters = mapper1.SelectByLessonId(registerStudent.getLessonId());

        for (Chapter c :chapters) {
              RegisterChapter(new CurrentStudentChapter
                      (0,registerStudent.getStudentName(),c.getId(),
                              c.getInformation(),"未完成",registerStudent.getLessonId(),0,""),
                      sqlSession);

        };
        return i;

    }

    //注册章节
    @Override
    public int RegisterChapter(CurrentStudentChapter currentStudentChapter ,SqlSession sqlSession){

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        int i = mapper.RegisterLessonChapter(currentStudentChapter);

        LessonDao mapper1 = sqlSession.getMapper(LessonDao.class);
        ArrayList<Question> questions = mapper1.SelectByChapterId(currentStudentChapter.getChapterId());
        for (Question q: questions) {

            RegisterQuestion(new CurrentStudentQuestion(0,currentStudentChapter.getStudentName(),q.getInformation(),"", "未完成",q.getId(),currentStudentChapter.getChapterId(),0,q.getImagePath(),q.getQuestionType()),sqlSession);
        }
        return i;
    }
    //注册问题
    @Override
    public int RegisterQuestion(CurrentStudentQuestion currentStudentQuestion, SqlSession sqlSession ){
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        int i = mapper.RegisterChapterQuestion(currentStudentQuestion);
        return i;
    }

    @Override
    public CurrentStudentQuestion SelectChapterById(CurrentStudentQuestion currentStudentQuestion) {

        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        CurrentStudentQuestion currentStudentQuestion1 = mapper.SelectMyQuestionById(currentStudentQuestion);


        return currentStudentQuestion1;

    }

    @Override
    public int UpdateCurrentStudentQuestion(CurrentStudentQuestion currentStudentQuestion) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        int i = mapper.UpdateCurrentQuestion(currentStudentQuestion);
        return i;
    }

    @Override
    public ArrayList<RegisterStudent> SelectRegisterStudentByLessonId(int id) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        ArrayList<RegisterStudent> registerStudents = mapper.SelectRegisterStudentByLessonId(id);
        return registerStudents;
    }

    @Override
    public String JudgeChapter(CurrentStudentQuestion currentStudentQuestion) {


        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        CurrentStudentChapter currentStudentChapter = new CurrentStudentChapter();
        currentStudentChapter.setChapterId(currentStudentQuestion.getChapterId());
        currentStudentChapter.setStudentName(currentStudentQuestion.getStudentName());
        ArrayList<CurrentStudentQuestion> currentStudentQuestions = mapper.SelectMyQuestion(currentStudentChapter);

        int count = 0;
        int Score = 0;
        for (CurrentStudentQuestion c:currentStudentQuestions) {
            if (c.getQuestionFinish().equals("回答正确")){
                count++;

            }
            Score+=c.getGetScore();
        }
        double b = (double) count /currentStudentQuestions.size();
        b*=100;
        String string = Double.toString(b);

        String[] split = string.split("\\.");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(split[0]);
        stringBuilder.append("%");
        String string1 = stringBuilder.toString();

        //更改章节得分和完成率
        CurrentStudentChapter currentStudentChapter1 = mapper.SelectMyChapterByNameAndId(currentStudentQuestion);
        currentStudentChapter1.setChapterFinish(string1);
        currentStudentChapter1.setGetScore(Score);

        int i = mapper.UpdateCurrentChapter(currentStudentChapter1);

        //更改课程得分
        String s = JudgeLesson(currentStudentChapter1);

        return  null;
    }

    @Override
    public String JudgeLesson(CurrentStudentChapter currentStudentChapter) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        RegisterStudent registerStudent = new RegisterStudent();
        registerStudent.setLessonId(currentStudentChapter.getLessonId());
        registerStudent.setStudentName(currentStudentChapter.getStudentName());
        RegisterStudent registerStudent1 = mapper.SelectRegisterStudent(registerStudent);
        ArrayList<CurrentStudentChapter> currentStudentChapters = mapper.SelectMyChapter(registerStudent1);
        int score = 0;
        for (CurrentStudentChapter c: currentStudentChapters) {
            score+=currentStudentChapter.getGetScore();
        }
        registerStudent1.setGetScore(score);
        int i = mapper.UpdateRegisterStudent(registerStudent1);
        return null;
    }

    @Override
    public void JudgeLessonUpdate(RegisterStudent registerStudent) {

      LessonServiceImpl lessonService = new LessonServiceImpl();

        ArrayList<CurrentStudentChapter> currentStudentChapters = lessonService.GetStudentChapter(registerStudent);
        ArrayList<Chapter> chapters = lessonService.GetChapterByLessonId(registerStudent.getLessonId());



        for (CurrentStudentChapter c: currentStudentChapters) {
            int sign =0;
            for (Chapter chapter: chapters) {
                if (c.getChapterId() == chapter.getId()){
                    sign=1;
                }
            }
            //章节已被删除
            if (sign == 0){
                DeleteCurrentChapter(c);
                currentStudentChapters.remove(c);
            }
        }
        for (Chapter chapter: chapters) {
            int sign =0;
             for (CurrentStudentChapter c: currentStudentChapters) {

            if (chapter.getId()==c.getChapterId()) {
                sign = 1;
            }
         }
          //添加新章节
            if (sign == 0) {
                CurrentStudentChapter currentStudentChapter = new CurrentStudentChapter();
                currentStudentChapter.setImagePath(chapter.getImagePath());
                currentStudentChapter.setChapterId(chapter.getId());
                currentStudentChapter.setChapterFinish("未完成");
                currentStudentChapter.setGetScore(0);
                currentStudentChapter.setLessonId(chapter.getLessonId());
                currentStudentChapter.setStudentName(currentStudentChapters.get(0).getStudentName());
                currentStudentChapter.setInformation(chapter.getInformation());
                int i = RegisterChapter(currentStudentChapter, sqlSession);

            }


        }


        for (CurrentStudentChapter c: currentStudentChapters) {
            JudgeChapterUpdate(c);
        }

    }

    @Override
    public void JudgeChapterUpdate(CurrentStudentChapter currentStudentChapter) {
        LessonServiceImpl lessonService = new LessonServiceImpl();

        ArrayList<Question> questions = lessonService.GetQuestionByChapterId(currentStudentChapter.getChapterId());
        ArrayList<CurrentStudentQuestion> currentStudentQuestions = lessonService.GetStudentQuestion(currentStudentChapter);
if (!currentStudentQuestions.isEmpty()){
    for (CurrentStudentQuestion c: currentStudentQuestions) {
        int sign =0;
        for (Question question: questions) {
            if (c.getQuestionId() == question.getId()){
                sign=1;
            }
        }
        //问题已被删除
        if (sign == 0){
            DeleteCurrentQuestion(c);
            currentStudentQuestions.remove(c);

        }
    }
    for (Question question: questions) {
        int sign =0;
        for (CurrentStudentQuestion c: currentStudentQuestions) {

            if (question.getId()==c.getQuestionId()) {
                sign = 1;
            }
        }
        //添加新问题
        if (sign == 0) {
            CurrentStudentQuestion currentStudentQuestion = new CurrentStudentQuestion();
            currentStudentQuestion.setStudentName(currentStudentQuestions.get(0).getStudentName());
            currentStudentQuestion.setQuestionFinish("未完成");
            currentStudentQuestion.setAnswer("无");
            currentStudentQuestion.setChapterId(question.getChapterId());
            currentStudentQuestion.setGetScore(0);
            currentStudentQuestion.setInformation(question.getInformation());
            currentStudentQuestion.setQuestionId(question.getId());
            currentStudentQuestion.setImagePath(question.getImagePath());
            currentStudentQuestion.setQuestionType(question.getQuestionType());
            int i = RegisterQuestion(currentStudentQuestion, sqlSession);


        }
        }



        }else {

             for (Question question: questions) {


                 //添加新问题

            CurrentStudentQuestion currentStudentQuestion = new CurrentStudentQuestion();
            currentStudentQuestion.setStudentName(currentStudentQuestions.get(0).getStudentName());
            currentStudentQuestion.setQuestionFinish("未完成");
            currentStudentQuestion.setAnswer("无");
            currentStudentQuestion.setChapterId(question.getChapterId());
            currentStudentQuestion.setGetScore(0);
            currentStudentQuestion.setInformation(question.getInformation());
            currentStudentQuestion.setQuestionId(question.getId());
            currentStudentQuestion.setImagePath(question.getImagePath());
            int i = RegisterQuestion(currentStudentQuestion, sqlSession);


             }

        }

    }

    @Override
    public void DeleteRegisterStudent(RegisterStudent registerStudent) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        mapper.deleteRegisterStudentById(registerStudent);
    }

    @Override
    public void DeleteCurrentChapter(CurrentStudentChapter currentStudentChapter) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        mapper.deleteCurrentChapterById(currentStudentChapter);
    }

    @Override
    public void DeleteCurrentQuestion(CurrentStudentQuestion currentStudentQuestion) {
        StudentDao mapper = sqlSession.getMapper(StudentDao.class);
        mapper.deleteCurrentQuestionById(currentStudentQuestion);
    }


}
