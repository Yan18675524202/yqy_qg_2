package com.yqy.Mybatis.executor.result;

import com.yqy.Mybatis.session.ResultHandler;
import com.yqy.Mybatis.reflection.factory.ObjectFactory;
import com.yqy.Mybatis.session.ResultContext;

import java.util.ArrayList;
import java.util.List;

public class DefaultResultHandler implements ResultHandler {

    private final List<Object> list;

    public DefaultResultHandler() {
        this.list = new ArrayList<>();
    }


    //通过 ObjectFactory 反射工具类，产生特定的 List

    @SuppressWarnings("unchecked")
    public DefaultResultHandler(ObjectFactory objectFactory) {
        this.list = objectFactory.create(List.class);
    }

    @Override
    public void handleResult(ResultContext context) {
        list.add(context.getResultObject());
    }

    public List<Object> getResultList() {
        return list;
    }

}
