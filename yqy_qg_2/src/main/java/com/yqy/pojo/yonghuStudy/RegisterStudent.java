package com.yqy.pojo.yonghuStudy;

public class RegisterStudent {
    int id;


    String studentName;

    int lessonId;

    int getScore;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getGetScore() {
        return getScore;
    }

    public void setGetScore(int getScore) {
        this.getScore = getScore;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public RegisterStudent(int id, String studentName, int lessonId, int getScore) {
        this.id = id;
        this.studentName = studentName;
        this.lessonId = lessonId;
        this.getScore = getScore;
    }

    public RegisterStudent() {
    }

    @Override
    public String toString() {
        return "RegisterStudent{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", lessonId=" + lessonId +
                ", getScore=" + getScore +
                '}';
    }
}
