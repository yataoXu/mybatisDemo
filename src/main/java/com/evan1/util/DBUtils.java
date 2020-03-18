package com.evan1.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

/**
 * @Classname DBUtils
 * @Description
 * @Date 2020/3/18 13:47
 * @Created by Evan
 */
public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = DBUtils.class;

    public static SqlSessionFactory initSqlSessionFactory() {
        InputStream is = null;
        try {
            is = Resources.getResourceAsStream("mybatis-conf.xml");
        } catch (IOException e) {
            e.printStackTrace();
        }
        synchronized (CLASS_LOCK) {
            if (sqlSessionFactory == null) {
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
            }
        }
        return sqlSessionFactory;
    }

    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null)
            initSqlSessionFactory();
        return sqlSessionFactory.openSession();
    }
}