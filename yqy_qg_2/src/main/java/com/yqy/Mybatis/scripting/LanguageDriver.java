package com.yqy.Mybatis.scripting;


import com.yqy.Mybatis.executor.parameter.ParameterHandler;
import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.mapping.SqlSource;
import com.yqy.Mybatis.session.Configuration;
import org.dom4j.Element;

public interface LanguageDriver {

   // 创建SQL源码(mapper xml方式)

    SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType);


   //创建参数处理器

    ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql);

}
