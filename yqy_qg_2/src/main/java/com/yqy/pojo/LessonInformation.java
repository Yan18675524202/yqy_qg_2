package com.yqy.pojo;

public class LessonInformation {
    int id;

    String information;

    String beginTime;

    String endTime;

    int registrationPopulation;
    String name;

    public LessonInformation(int id, String information, String beginTime, String endTime, int registrationPopulation, String name) {
        this.id = id;
        this.information = information;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.registrationPopulation = registrationPopulation;
        this.name = name;
    }

    public LessonInformation() {
    }

    @Override
    public String toString() {
        return "LessonInformation{" +
                "id=" + id +
                ", information='" + information + '\'' +
                ", beginTime='" + beginTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", registrationPopulation=" + registrationPopulation +
                ", name='" + name + '\'' +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(String beginTime) {
        this.beginTime = beginTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getRegistrationPopulation() {
        return registrationPopulation;
    }

    public void setRegistrationPopulation(int registrationPopulation) {
        this.registrationPopulation = registrationPopulation;
    }
}
