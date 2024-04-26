package com.yqy.Utils;

import java.time.LocalDateTime;
import java.util.Date;

public class TimeUtils {


    public static String getTime(){

        LocalDateTime now = LocalDateTime.now();

        //获取时间
        int year = now.getYear();
        int monthValue = now.getMonthValue();
        int dayOfMonth = now.getDayOfMonth();
        int hour = now.getHour();
        int minute = now.getMinute();
        int second = now.getSecond();
        //拼接字符串
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(year);
        stringBuilder.append("-");
        stringBuilder.append(monthValue);
        stringBuilder.append("-");
        stringBuilder.append(dayOfMonth);



        return stringBuilder.toString();
    }

    public static String dealTime(String time){

        String[] split = time.split("-");
        int i1 = Integer.parseInt(split[1]);
        int i2 = Integer.parseInt(split[2]);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(split[0]);
        stringBuilder.append("-");
        stringBuilder.append(i1);
        stringBuilder.append("-");
        stringBuilder.append(i2);
        return stringBuilder.toString();
    }

    //比较时间是否在限定时间内
    public static boolean TimeIs(String beginTime , String endTime){

        String[] begin = beginTime.split("-");

        String[] end = endTime.split("-");

        String time = getTime();

        String[] Time = time.split("-");

        boolean next = true;

       //判断开课时间
        for (int i = 0; i < 3; i++) {
            if (i==0){
                if (Integer.parseInt(begin[i])>Integer.parseInt(Time[i])){
                    return false;
                } else if (Integer.parseInt(begin[i])<Integer.parseInt(Time[i])) {
                    next = false;
                }else {
                    next = true;
                }
            }else {
                if (next){
                    if (Integer.parseInt(begin[i])>Integer.parseInt(Time[i])){
                        return false;
                    } else if (Integer.parseInt(begin[i])<Integer.parseInt(Time[i])) {
                        next = false;
                    }else {
                        next = true;
                    }

                }
            }
        }

        //判断结课时间
        for (int i = 0; i < 3; i++) {
            if (i==0){
                if (Integer.parseInt(end[i])<Integer.parseInt(Time[i])){
                    return false;
                } else if (Integer.parseInt(end[i])>Integer.parseInt(Time[i])) {
                    next = false;
                }else {
                    next = true;
                }
            }else {
                if (next){
                    if (Integer.parseInt(end[i])<Integer.parseInt(Time[i])){
                        return false;
                    } else if (Integer.parseInt(end[i])>Integer.parseInt(Time[i])) {
                        next = false;
                    }else {
                        next = true;
                    }

                }
            }
        }




        return true;
    }




}
