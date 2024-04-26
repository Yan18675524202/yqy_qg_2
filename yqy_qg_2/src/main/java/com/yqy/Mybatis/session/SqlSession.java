package com.yqy.Mybatis.session;

import java.sql.SQLException;
import java.util.List;

public interface SqlSession {

    <T> T selectOne(String statement) throws SQLException;

    <T> T selectOne(String statement, Object parameter) throws SQLException;
    <T> List<T> selectAll(String statement,Object parameter) throws SQLException;
    <T> List<T>  selectAll(String statement) throws SQLException;
    /**
     * Execute an insert statement with the given parameter object. Any generated
     * autoincrement values or selectKey entries will modify the given parameter
     * object properties. Only the number of rows affected will be returned.
     * 插入记录，容许传入参数。
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the insert. 注意返回的是受影响的行数
     */
    int insert(String statement, Object parameter) throws SQLException;

    /**
     * Execute an update statement. The number of rows affected will be returned.
     * 更新记录
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the update. 返回的是受影响的行数
     */
    int update(String statement, Object parameter) throws SQLException;

    /**
     * Execute a delete statement. The number of rows affected will be returned.
     * 删除记录
     *
     * @param statement Unique identifier matching the statement to execute.
     * @param parameter A parameter object to pass to the statement.
     * @return int The number of rows affected by the delete. 返回的是受影响的行数
     */
    Object delete(String statement, Object parameter) throws SQLException;
    Configuration getConfiguration();
  //得到映射器
    <T> T getMapper(Class<T> type);
    void commit();
}
