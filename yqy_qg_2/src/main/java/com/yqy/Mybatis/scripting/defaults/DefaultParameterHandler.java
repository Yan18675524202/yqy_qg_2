package com.yqy.Mybatis.scripting.defaults;


import com.yqy.Mybatis.executor.parameter.ParameterHandler;
import com.yqy.Mybatis.mapping.BoundSql;
import com.yqy.Mybatis.mapping.MappedStatement;
import com.yqy.Mybatis.mapping.ParameterMapping;
import com.yqy.Mybatis.session.Configuration;
import com.yqy.Mybatis.type.JdbcType;
import com.yqy.Mybatis.type.TypeHandler;
import com.yqy.Mybatis.type.TypeHandlerRegistry;
import com.alibaba.fastjson.JSON;

import com.yqy.Mybatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


public class DefaultParameterHandler implements ParameterHandler {

    private Logger logger = LoggerFactory.getLogger(DefaultParameterHandler.class);

    private final TypeHandlerRegistry typeHandlerRegistry;

    private final MappedStatement mappedStatement;
    private final Object parameterObject;
    private BoundSql boundSql;
    private Configuration configuration;

    public DefaultParameterHandler(MappedStatement mappedStatement, Object parameterObject, BoundSql boundSql) {
        this.mappedStatement = mappedStatement;
        this.configuration = mappedStatement.getConfiguration();
        this.typeHandlerRegistry = mappedStatement.getConfiguration().getTypeHandlerRegistry();
        this.parameterObject = parameterObject;
        this.boundSql = boundSql;
    }

    @Override
    public Object getParameterObject() {
        return parameterObject;
    }
    //设置参数
    @Override
    public void setParameters(PreparedStatement ps) throws SQLException {
        //获取参数映射集合
        List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
        if (null != parameterMappings) {
            //遍历集合,给sql语句中每个?设置参数
            for (int i = 0; i < parameterMappings.size(); i++) {
                ParameterMapping parameterMapping = parameterMappings.get(i);
                //获取属性名
                String propertyName = parameterMapping.getProperty();
                Object value;
                //如果参数类型在typeHandlerRegistry存在,则直接赋值
                if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
                    value = parameterObject;
                } else {
                    // 不存在
                    // 通过 MetaObject.getValue 反射取得值设进去
                    MetaObject metaObject = configuration.newMetaObject(parameterObject);
                    value = metaObject.getValue(propertyName);
                }
                //获取Jdbc类型
                JdbcType jdbcType = parameterMapping.getJdbcType();

                // 设置参数
                logger.info("根据每个ParameterMapping中的TypeHandler设置对应的参数信息 value：{}", JSON.toJSONString(value));
                TypeHandler typeHandler = parameterMapping.getTypeHandler();
                typeHandler.setParameter(ps, i + 1, value, jdbcType);
            }
        }
    }

}
