package com.yqy.pojo.yonghuStudy;

public class CurrentStudentQuestion {
    int id;

    String studentName;

    String information;

    String answer;

    String questionFinish;

    int questionId;

    int chapterId;

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

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getQuestionFinish() {
        return questionFinish;
    }

    public void setQuestionFinish(String questionFinish) {
        this.questionFinish = questionFinish;
    }

    public CurrentStudentQuestion(int id, String studentName, String information, String answer, String questionFinish, int questionId, int chapterId, int getScore, String imagePath) {
        this.id = id;
        this.studentName = studentName;
        this.information = information;
        this.answer = answer;
        this.questionFinish = questionFinish;
        this.questionId = questionId;
        this.chapterId = chapterId;
        this.getScore = getScore;
        this.imagePath = imagePath;
    }

    public CurrentStudentQuestion() {
    }

    @Override
    public String toString() {
        return "CurrentStudentQuestion{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", information='" + information + '\'' +
                ", answer='" + answer + '\'' +
                ", questionFinish='" + questionFinish + '\'' +
                ", questionId=" + questionId +
                ", chapterId=" + chapterId +
                ", getScore=" + getScore +
                ", imagePath='" + imagePath + '\'' +
                '}';
    }
}
