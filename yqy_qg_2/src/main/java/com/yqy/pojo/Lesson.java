package com.yqy.pojo;

public class Lesson {
    int id;

    String name;

    String teacherName;

    int informationId;

    int registrationPopulation;

    int currentEnrollment;

    public Lesson() {
    }

    public Lesson(int id, String name, String teacherName, int informationId, int registrationPopulation, int currentEnrollment) {
        this.id = id;
        this.name = name;
        this.teacherName = teacherName;
        this.informationId = informationId;
        this.registrationPopulation = registrationPopulation;
        this.currentEnrollment = currentEnrollment;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", teacherName=" + teacherName +
                ", informationId=" + informationId +
                ", registrationPopulation=" + registrationPopulation +
                ", currentEnrollment=" + currentEnrollment +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getInformationId() {
        return informationId;
    }

    public void setInformationId(int informationId) {
        this.informationId = informationId;
    }

    public int getRegistrationPopulation() {
        return registrationPopulation;
    }

    public void setRegistrationPopulation(int registrationPopulation) {
        this.registrationPopulation = registrationPopulation;
    }

    public int getCurrentEnrollment() {
        return currentEnrollment;
    }

    public void setCurrentEnrollment(int currentEnrollment) {
        this.currentEnrollment = currentEnrollment;
    }
}
