package com.yqy.pojo.yonghuStudy;

public class CurrentStudentChapter {
    int id;


    String studentName;

    int chapterId;

    String information;

    String chapterFinish;

    int lessonId;
    int getScore;

    String imagePath;

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getGetScore() {
        return getScore;
    }

    public void setGetScore(int getScore) {
        this.getScore = getScore;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getChapterFinish() {
        return chapterFinish;
    }

    public void setChapterFinish(String chapterFinish) {
        this.chapterFinish = chapterFinish;
    }

    public CurrentStudentChapter(int id, String studentName, int chapterId, String information, String chapterFinish, int lessonId, int getScore, String imagePath) {
        this.id = id;
        this.studentName = studentName;
        this.chapterId = chapterId;
        this.information = information;
        this.chapterFinish = chapterFinish;
        this.lessonId = lessonId;
        this.getScore = getScore;
        this.imagePath = imagePath;
    }

    public CurrentStudentChapter() {
    }

    @Override
    public String toString() {
        return "CurrentStudentChapter{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", chapterId=" + chapterId +
                ", information='" + information + '\'' +
                ", chapterFinish='" + chapterFinish + '\'' +
                ", lessonId=" + lessonId +
                ", getScore=" + getScore +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
