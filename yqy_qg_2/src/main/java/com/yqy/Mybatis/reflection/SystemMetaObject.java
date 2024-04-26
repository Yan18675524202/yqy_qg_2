package com.yqy.Mybatis.reflection;


import com.yqy.Mybatis.reflection.factory.DefaultObjectFactory;
import com.yqy.Mybatis.reflection.factory.ObjectFactory;
import com.yqy.Mybatis.reflection.wrapper.DefaultObjectWrapperFactory;
import com.yqy.Mybatis.reflection.wrapper.ObjectWrapperFactory;

public class SystemMetaObject {
    // 对象工厂
    public static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    //对象包装器工厂
    public static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    public static final MetaObject NULL_META_OBJECT = MetaObject.forObject(NullObject.class, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);

    private SystemMetaObject() {
        // Prevent Instantiation of Static Class
    }

    /**
     * 空对象
     */
    private static class NullObject {
    }

    public static MetaObject forObject(Object object) {
        return MetaObject.forObject(object, DEFAULT_OBJECT_FACTORY, DEFAULT_OBJECT_WRAPPER_FACTORY);
    }

}
