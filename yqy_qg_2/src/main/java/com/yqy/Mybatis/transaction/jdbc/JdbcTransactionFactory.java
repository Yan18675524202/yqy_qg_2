package com.yqy.Mybatis.transaction.jdbc;


import com.yqy.Mybatis.session.TransactionIsolationLevel;
import com.yqy.Mybatis.transaction.Transaction;
import com.yqy.Mybatis.transaction.TransactionFactory;

import javax.sql.DataSource;
import java.sql.Connection;

public class JdbcTransactionFactory implements TransactionFactory {

    @Override
    public Transaction newTransaction(Connection conn) {
        return new JdbcTransaction(conn);
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit) {
        return new JdbcTransaction(dataSource, level, autoCommit);
    }

}
