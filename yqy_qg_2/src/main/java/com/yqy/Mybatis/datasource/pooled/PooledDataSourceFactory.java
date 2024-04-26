package com.yqy.Mybatis.datasource.pooled;


import com.yqy.Mybatis.datasource.unpooled.UnpooledDataSourceFactory;

//池化连接池工厂
public class PooledDataSourceFactory extends UnpooledDataSourceFactory {


    public PooledDataSourceFactory() {
        this.dataSource = new PooledDataSource();
    }


}
