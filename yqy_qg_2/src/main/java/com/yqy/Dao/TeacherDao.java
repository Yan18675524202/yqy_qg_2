package com.yqy.Dao;

import com.yqy.pojo.yonghu.Teacher;
import com.yqy.pojo.yonghu.User;

public interface TeacherDao {
  public Teacher SelectOneById(int id);

  public Teacher SelectOneByNameAndPassword(User user);

  public Teacher SelectOneByName(String username);
  public void insertTeacher(Teacher teacher);
  public void deleteStudentByName(String username);

  public int updateTeacher(Teacher teacher);


}
