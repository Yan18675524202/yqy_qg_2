package com.yqy;

import com.yqy.Dao.LessonDao;
import com.yqy.Dao.TeacherDao;
import com.yqy.Mybatis.io.Resources;
import com.yqy.Mybatis.session.SqlSession;
import com.yqy.Mybatis.session.SqlSessionFactory;
import com.yqy.Mybatis.session.SqlSessionFactoryBuilder;
import com.yqy.Service.Impl.LessonServiceImpl;
import com.yqy.Service.Impl.PersonServiceImpl;
import com.yqy.Service.Impl.RegisterLessonServiceImpl;
import com.yqy.Utils.SaltMD5Util;
import com.yqy.Utils.TimeUtils;
import com.yqy.pojo.Lesson;
import com.yqy.pojo.Question;
import com.yqy.pojo.yonghu.Student;
import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghuStudy.CurrentStudentQuestion;
import com.yqy.pojo.yonghuStudy.RegisterStudent;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class test {

    private Logger logger = LoggerFactory.getLogger(test.class);


    @Test
    public void test1() throws IOException {


    }



    @Test
    public void mapped() throws IOException {

            String s1 = "2023-4-3";
            String s2 = "2024-5-5";
        System.out.println(TimeUtils.TimeIs(s1, s2));


    }


    private SqlSession sqlSession;







}
