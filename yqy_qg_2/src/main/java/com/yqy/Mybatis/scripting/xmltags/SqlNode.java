package com.yqy.Mybatis.scripting.xmltags;

//SQL 节点
public interface SqlNode {

    boolean apply(DynamicContext context);

}