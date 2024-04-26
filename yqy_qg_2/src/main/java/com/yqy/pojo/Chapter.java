package com.yqy.pojo;

public class Chapter {

    int id;

    int lessonId;

    String name;

    String information;

    String imagePath;

    public Chapter() {
    }

    public Chapter(int id, int lessonId, String name, String information, String imagePath) {
        this.id = id;
        this.lessonId = lessonId;
        this.name = name;
        this.information = information;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    @Override
    public String toString() {
        return "Chapter{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", name='" + name + '\'' +
                ", information='" + information + '\'' +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
