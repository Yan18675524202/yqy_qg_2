package com.yqy.Mybatis.executor.resultset;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
//返回结果处理
public interface ResultSetHandler {
    //处理返回结果
    <E> List<E> handleResultSets(Statement stmt) throws SQLException;

}
