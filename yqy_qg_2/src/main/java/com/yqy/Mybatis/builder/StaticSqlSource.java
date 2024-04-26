package com.yqy.Mybatis.builder;


import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.SqlSource;
import com.yqy.Mybatis.mapping.ParameterMapping;
import com.yqy.Mybatis.session.Configuration;

import java.util.List;

public class StaticSqlSource implements SqlSource {

    private String sql;
    private List<ParameterMapping> parameterMappings;
    private Configuration configuration;

    public StaticSqlSource(Configuration configuration, String sql) {
        this(configuration, sql, null);
    }

    public StaticSqlSource(Configuration configuration, String sql, List<ParameterMapping> parameterMappings) {
        this.sql = sql;
        this.parameterMappings = parameterMappings;
        this.configuration = configuration;
    }

    @Override
    public BoundSql getBoundSql(Object parameterObject) {
        return new BoundSql(configuration, sql, parameterMappings, parameterObject);
    }

}
