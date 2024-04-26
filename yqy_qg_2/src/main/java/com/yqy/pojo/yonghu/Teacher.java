package com.yqy.pojo.yonghu;

import com.yqy.pojo.yonghu.Person;

public class Teacher extends Person {

    String email;

    String qq;

    public String getEmail() {
        return email;
    }

    public String getQq() {
        return qq;
    }

    public Teacher(int id, String username, String password, String personInformation, String email, String qq) {
        super(id,username, password, personInformation);
        this.email = email;
        this.qq = qq;
    }

    public Teacher() {
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }
}
