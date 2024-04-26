package com.yqy.Mybatis.session.defaults;

import com.yqy.Mybatis.executor.Executor;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.session.Configuration;
import com.yqy.Mybatis.session.SqlSession;
import com.alibaba.fastjson.JSON;
import com.yqy.Mybatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.List;
//SqlSession实现类
public class DefaultSqlSession implements SqlSession {



    private Logger logger = LoggerFactory.getLogger(DefaultSqlSession.class);

    private Configuration configuration;
    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor) {
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement) throws SQLException {
        return this.selectOne(statement, null);
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) throws SQLException {
        List<T> list = this.<T>selectAll(statement, parameter);
        if (list.size() == 1) {
            return list.get(0);
        } else if (list.size() > 1) {
            throw new RuntimeException("Expected one result (or null) to be returned by selectOne(), but found: " + list.size());
        } else {
            return null;
        }
    }

    @Override
    public <E> List<E> selectAll(String statement, Object parameter) throws SQLException {
        logger.info("执行查询 statement：{} parameter：{}", statement, JSON.toJSONString(parameter));
        MappedStatement ms = configuration.getMappedStatement(statement);
        return executor.query(ms, parameter, RowBounds.DEFAULT, Executor.NO_RESULT_HANDLER, ms.getSqlSource().getBoundSql(parameter));
    }

    @Override
    public <T> List<T> selectAll(String statement) throws SQLException {
        return selectAll(statement, null);

    }

    @Override
    public int insert(String statement, Object parameter) throws SQLException {
        // 在 Mybatis 中 insert 调用的是 update
        return update(statement, parameter);
    }

    @Override
    public int update(String statement, Object parameter) throws SQLException {
        MappedStatement ms = configuration.getMappedStatement(statement);
        try {
            int update = executor.update(ms, parameter);

            return update;

        } catch (SQLException e) {
            executor.rollback(true);
            throw new RuntimeException("Error updating database.  Cause: " + e);
        }finally {
            executor.commit(true);
        }
    }

    @Override
    public Object delete(String statement, Object parameter) throws SQLException {
        return update(statement, parameter);
    }

    @Override
    public void commit() {
        try {
            executor.commit(true);
        } catch (SQLException e) {
            throw new RuntimeException("Error committing transaction.  Cause: " + e);
        }
    }

    @Override
    public <T> T getMapper(Class<T> type) {
        return configuration.getMapper(type, this);
    }

    @Override
    public Configuration getConfiguration() {
        return configuration;
    }


}
