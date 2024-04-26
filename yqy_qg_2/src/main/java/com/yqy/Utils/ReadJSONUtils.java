package com.yqy.Utils;

import com.alibaba.fastjson.JSON;


import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class ReadJSONUtils {
    //转换JSON为类
    public static <T> T parseJsonToClass(HttpServletRequest req, Class<T> clazz) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        String jsonString = jsonBuffer.toString();
        return JSON.parseObject(jsonString, clazz);
    }
    //转换JSON为集合
    public static <T> List<T> parseJsonToClassList(HttpServletRequest req, Class<T> clazz) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        String jsonString = jsonBuffer.toString();
        return JSON.parseArray(jsonString, clazz);
    }
    //转换JSON为字符串
    public static String parseJsonToString(HttpServletRequest req) throws IOException {
        BufferedReader reader = req.getReader();
        StringBuilder jsonBuffer = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            jsonBuffer.append(line);
        }
        return (jsonBuffer.toString());
    }


}
