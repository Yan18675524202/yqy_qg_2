package com.yqy.Mybatis.session.defaults;


import com.yqy.Mybatis.executor.Executor;
import com.yqy.Mybatis.mapping.Environment;
import com.yqy.Mybatis.session.Configuration;
import com.yqy.Mybatis.session.SqlSession;
import com.yqy.Mybatis.session.SqlSessionFactory;
import com.yqy.Mybatis.session.TransactionIsolationLevel;
import com.yqy.Mybatis.transaction.Transaction;
import com.yqy.Mybatis.transaction.TransactionFactory;

import java.sql.SQLException;

//SqlSessionFactory实现类
public class DefaultSqlSessionFactory implements SqlSessionFactory {



    private final Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }

    @Override
    public SqlSession openSession() {
        //事务
        Transaction tx = null;


        try {
            //环境信息
            final Environment environment = configuration.getEnvironment();
            TransactionFactory transactionFactory = environment.getTransactionFactory();
            //创建事务
            //(关闭自动提交)
            tx = transactionFactory.newTransaction(configuration.getEnvironment().getDataSource(), TransactionIsolationLevel.READ_COMMITTED, false);
            // 创建执行器
            final Executor executor = configuration.newExecutor(tx);
            // 创建DefaultSqlSession
            return new DefaultSqlSession(configuration, executor);
        } catch (Exception e) {
            try {
                assert tx != null;
                tx.close();
            } catch (SQLException ignore) {
            }
            throw new RuntimeException("Error opening session.  Cause: " + e);
        }
    }

}
