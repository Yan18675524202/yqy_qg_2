package com.yqy.pojo.yonghu;

public class Person {

    int  id;
    String username;

    String password;

    String personInformation;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person() {
    }

    public Person(int id,String username, String password, String personInformation) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.personInformation = personInformation;
    }

    public Person(String username, String password) {
        this.username = username;
        this.password = password;

    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPersonInformation() {
        return personInformation;
    }

    public void setPersonInformation(String personInformation) {
        this.personInformation = personInformation;
    }
}
