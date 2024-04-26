package com.yqy.Mybatis.scripting.xmltags;

import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.executor.parameter.ParameterHandler;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.mapping.SqlSource;
import com.yqy.Mybatis.scripting.LanguageDriver;
import com.yqy.Mybatis.scripting.defaults.DefaultParameterHandler;
import com.yqy.Mybatis.session.Configuration;
import org.dom4j.Element;

public class XMLLanguageDriver implements LanguageDriver {

    @Override
    public SqlSource createSqlSource(Configuration configuration, Element script, Class<?> parameterType) {
        // 用XML脚本构建器解析
        XMLScriptBuilder builder = new XMLScriptBuilder(configuration, script, parameterType);
        return builder.parseScriptNode();
    }
    @Override
    public ParameterHandler createParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        return new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
    }

}