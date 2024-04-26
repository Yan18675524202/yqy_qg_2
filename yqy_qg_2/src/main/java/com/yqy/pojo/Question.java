package com.yqy.pojo;

public class Question {

    int id;

    int chapterId;

    String information;

    String name;

    int questionType;

    String rightAnswer;

    int score;

    String imagePath;

    public Question(int id, int chapterId, String information, String name, int questionType, String rightAnswer, int score, String imagePath) {
        this.id = id;
        this.chapterId = chapterId;
        this.information = information;
        this.name = name;
        this.questionType = questionType;
        this.rightAnswer = rightAnswer;
        this.score = score;
        this.imagePath = imagePath;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", chapterId=" + chapterId +
                ", information='" + information + '\'' +
                ", name='" + name + '\'' +
                ", questionType=" + questionType +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", score=" + score +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }

    public Question() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuestionType() {
        return questionType;
    }

    public void setQuestionType(int questionType) {
        this.questionType = questionType;
    }
}
