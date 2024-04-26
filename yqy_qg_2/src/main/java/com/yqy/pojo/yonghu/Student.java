package com.yqy.pojo.yonghu;

import com.yqy.pojo.yonghu.Person;

public class Student extends Person {

    String studentNumber;

    String grade;

    public Student() {
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", grade='" + grade + '\'' +
                ", id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", personInformation='" + personInformation + '\'' +
                '}';
    }




    public Student(int id ,String username, String password, String personInformation, String studentNumber, String grade) {
        super(id,username, password, personInformation);
        this.studentNumber = studentNumber;
        this.grade = grade;
    }



}
