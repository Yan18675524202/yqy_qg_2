package com.yqy.Mybatis.executor;


import com.yqy.Mybatis.executor.statement.StatementHandler;
import com.yqy.Mybatis.session.ResultHandler;
import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.session.Configuration;
import com.yqy.Mybatis.session.RowBounds;
import com.yqy.Mybatis.transaction.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
//执行器
public class SimpleExecutor extends BaseExecutor {

    public SimpleExecutor(Configuration configuration, Transaction transaction) {
        super(configuration, transaction);
    }

    @Override
    protected int doUpdate(MappedStatement ms, Object parameter) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            // 新建一个 StatementHandler
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, RowBounds.DEFAULT, null, null);
            // 准备语句
            stmt = prepareStatement(handler);
            // StatementHandler.update
            return handler.update(stmt);
        } finally {
            closeStatement(stmt);

        }
    }

    @Override
    protected <E> List<E> doQuery(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) throws SQLException {
        Statement stmt = null;
        try {
            Configuration configuration = ms.getConfiguration();
            // 新建一个 StatementHandler
            StatementHandler handler = configuration.newStatementHandler(this, ms, parameter, rowBounds, resultHandler, boundSql);
            // 准备语句
            stmt = prepareStatement(handler);
            // 返回结果
            return handler.query(stmt, resultHandler);
        } finally {
            closeStatement(stmt);
        }
    }

    private Statement prepareStatement(StatementHandler handler) throws SQLException {
        Statement stmt;
        Connection connection = transaction.getConnection();
        // 准备语句
        stmt = handler.prepare(connection);
        handler.parameterize(stmt);
        return stmt;
    }



}
