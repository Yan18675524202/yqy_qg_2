package com.yqy.Mybatis.executor;


import com.yqy.Mybatis.session.ResultHandler;
import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.session.RowBounds;
import com.yqy.Mybatis.transaction.Transaction;

import java.sql.SQLException;
import java.util.List;

public interface Executor {

    ResultHandler NO_RESULT_HANDLER = null;

    int update(MappedStatement ms, Object parameter) throws SQLException;

    <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException;

    Transaction getTransaction();
    //提交
    void commit(boolean required) throws SQLException;
    //回滚
    void rollback(boolean required) throws SQLException;
    //关闭
    void close(boolean forceRollback);

}