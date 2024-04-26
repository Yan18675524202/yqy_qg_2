package com.yqy.Utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Regex {
    //校验名字
    public static boolean validateName(String name) {
        String regex = "^[a-zA-Z0-9]{1,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(name);
        return matcher.matches();
    }

    //校验密码
    public static boolean validatePassword(String password) {
        String regex = "^[a-zA-Z0-9]{1,10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }
    //校验学号
    public static boolean validateStudentID(String studentID) {
        String regex = "^\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(studentID);
        return matcher.matches();
    }
}
