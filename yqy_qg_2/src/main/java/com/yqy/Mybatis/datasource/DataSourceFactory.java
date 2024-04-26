package com.yqy.Mybatis.datasource;

import javax.sql.DataSource;
import java.util.Properties;

//连接池工厂接口
public interface DataSourceFactory {

    void setProperties(Properties props);

    DataSource getDataSource();

}
