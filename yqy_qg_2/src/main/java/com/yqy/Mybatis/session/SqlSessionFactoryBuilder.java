package com.yqy.Mybatis.session;

import com.yqy.Mybatis.builder.xml.XMLConfigBuilder;
import com.yqy.Mybatis.session.defaults.DefaultSqlSessionFactory;

import java.io.Reader;

public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(Reader reader) {
        //建立xml解析器
        XMLConfigBuilder xmlConfigBuilder = new XMLConfigBuilder(reader);

        return build(xmlConfigBuilder.parse());
    }

    public SqlSessionFactory build(Configuration config) {
        return new DefaultSqlSessionFactory(config);
    }

}