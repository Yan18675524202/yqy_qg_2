package com.yqy.Mybatis.mapping;


import com.yqy.Mybatis.session.Configuration;
import com.yqy.Mybatis.type.JdbcType;
import com.yqy.Mybatis.type.TypeHandler;

public class ResultMapping {

    private Configuration configuration;
    private String property;
    private String column;
    private Class<?> javaType;
    private JdbcType jdbcType;
    private TypeHandler<?> typeHandler;

    ResultMapping() {
    }

    public static class Builder {
        private ResultMapping resultMapping = new ResultMapping();


    }

}
