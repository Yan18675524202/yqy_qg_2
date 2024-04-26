package com.yqy.Mybatis.session;


import com.yqy.Mybatis.datasource.pooled.PooledDataSourceFactory;
import com.yqy.Mybatis.datasource.unpooled.UnpooledDataSourceFactory;
import com.yqy.Mybatis.executor.Executor;
import com.yqy.Mybatis.executor.SimpleExecutor;
import com.yqy.Mybatis.executor.resultset.ResultSetHandler;
import com.yqy.Mybatis.executor.statement.PreparedStatementHandler;
import com.yqy.Mybatis.executor.statement.StatementHandler;
import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.Environment;
import com.yqy.Mybatis.scripting.LanguageDriver;
import com.yqy.Mybatis.scripting.LanguageDriverRegistry;
import com.yqy.Mybatis.scripting.xmltags.XMLLanguageDriver;
import com.yqy.Mybatis.transaction.Transaction;
import com.yqy.Mybatis.transaction.jdbc.JdbcTransactionFactory;
import com.yqy.Mybatis.type.TypeAliasRegistry;
import com.yqy.Mybatis.type.TypeHandlerRegistry;
import com.yqy.Mybatis.binding.MapperRegistry;

import com.yqy.Mybatis.executor.parameter.ParameterHandler;
import com.yqy.Mybatis.executor.resultset.DefaultResultSetHandler;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.reflection.MetaObject;
import com.yqy.Mybatis.reflection.factory.DefaultObjectFactory;
import com.yqy.Mybatis.reflection.factory.ObjectFactory;
import com.yqy.Mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.yqy.Mybatis.reflection.wrapper.ObjectWrapperFactory;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


public class Configuration {
    //环境
    protected Environment environment;

    // 映射注册机
    protected MapperRegistry mapperRegistry = new MapperRegistry(this);

    // 映射的语句，存在Map里
    protected final Map<String, MappedStatement> mappedStatements = new HashMap<>();

    // 类型别名注册机
    protected final TypeAliasRegistry typeAliasRegistry = new TypeAliasRegistry();
    protected final LanguageDriverRegistry languageRegistry = new LanguageDriverRegistry();

    // 类型处理器注册机
    protected final TypeHandlerRegistry typeHandlerRegistry = new TypeHandlerRegistry();

    // 对象工厂和对象包装器工厂
    protected ObjectFactory objectFactory = new DefaultObjectFactory();
    protected ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();
   //加载资源
    protected final Set<String> loadedResources = new HashSet<>();

    protected String databaseId;

    public Configuration() {
        //注册
        typeAliasRegistry.registerAlias("JDBC", JdbcTransactionFactory.class);
        typeAliasRegistry.registerAlias("UNPOOLED", UnpooledDataSourceFactory.class);
        typeAliasRegistry.registerAlias("POOLED", PooledDataSourceFactory.class);

        languageRegistry.setDefaultDriverClass(XMLLanguageDriver.class);
    }

    public void addMappers(String packageName) {
        //mapperRegistry.addMappers(packageName);
    }

    public <T> void addMapper(Class<T> type) {
        mapperRegistry.addMapper(type);
    }

    public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        return mapperRegistry.getMapper(type, sqlSession);
    }

    public boolean hasMapper(Class<?> type) {
        return mapperRegistry.hasMapper(type);
    }

    public void addMappedStatement(MappedStatement ms) {
        mappedStatements.put(ms.getId(), ms);
    }

    public MappedStatement getMappedStatement(String id) {
        return mappedStatements.get(id);
    }

    public TypeAliasRegistry getTypeAliasRegistry() {
        return typeAliasRegistry;
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getDatabaseId() {
        return databaseId;
    }


    //创建结果集处理器

    public ResultSetHandler newResultSetHandler(Executor executor, MappedStatement mappedStatement, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new DefaultResultSetHandler(executor, mappedStatement, resultHandler, rowBounds, boundSql);
    }


    //生产执行器

    public Executor newExecutor(Transaction transaction) {
        return new SimpleExecutor(this, transaction);
    }


    //创建语句处理器

    public StatementHandler newStatementHandler(Executor executor, MappedStatement mappedStatement, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, BoundSql boundSql) {
        return new PreparedStatementHandler(executor, mappedStatement, parameter, rowBounds, resultHandler, boundSql);
    }

    // 创建元对象
    public MetaObject newMetaObject(Object object) {
        return MetaObject.forObject(object, objectFactory, objectWrapperFactory);
    }

    // 类型处理器注册机
    public TypeHandlerRegistry getTypeHandlerRegistry() {
        return typeHandlerRegistry;
    }

    public boolean isResourceLoaded(String resource) {
        return loadedResources.contains(resource);
    }

    public void addLoadedResource(String resource) {
        loadedResources.add(resource);
    }

    public LanguageDriverRegistry getLanguageRegistry() {
        return languageRegistry;
    }

    public ParameterHandler newParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        // 创建参数处理器
        ParameterHandler parameterHandler = mappedStatement.getLang().createParameterHandler(mappedStatement, parameterObject, boundSql);
        // 插件的一些参数，也是在这里处理，暂时不添加这部分内容 interceptorChain.pluginAll(parameterHandler);
        return parameterHandler;
    }

    public LanguageDriver getDefaultScriptingLanguageInstance() {
        return languageRegistry.getDefaultDriver();
    }

    public ObjectFactory getObjectFactory() {
        return objectFactory;
    }

}
