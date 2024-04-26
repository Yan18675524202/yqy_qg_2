package com.yqy.pojo;

public class Discussion {
    int id;

    String information;

    String discussName;

    String time;

    int lessonId;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getDiscussName() {
        return discussName;
    }

    public void setDiscussName(String discussName) {
        this.discussName = discussName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public Discussion() {
    }

    public Discussion(int id, String information, String discussName, String time, int lessonId) {
        this.id = id;
        this.information = information;
        this.discussName = discussName;
        this.time = time;
        this.lessonId = lessonId;
    }

    @Override
    public String toString() {
        return "Discussion{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", discussName='" + discussName + '\'' +
                ", time='" + time + '\'' +
                ", lessonId=" + lessonId +
                '}';
    }
}
