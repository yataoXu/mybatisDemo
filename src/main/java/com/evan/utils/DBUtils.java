package com.evan.utils;

import com.evan.mapper.UserMapper;
import org.apache.ibatis.datasource.pooled.PooledDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;


public class DBUtils {
    private static SqlSessionFactory sqlSessionFactory = null;
    private static final Class CLASS_LOCK = DBUtils.class;

    public static SqlSessionFactory initSqlSessionFactory() {
        if (sqlSessionFactory == null) {
            synchronized (CLASS_LOCK) {
                if (sqlSessionFactory == null) {
                    PooledDataSource dataSource = new PooledDataSource();
                    dataSource.setDriver("com.mysql.jdbc.Driver");
                    dataSource.setUrl("jdbc:mysql://10.2.192.31:3306/mybatis");
                    dataSource.setUsername("root");
                    dataSource.setPassword("anyrobot123");
                    TransactionFactory transactionFactory = new JdbcTransactionFactory();
                    Environment environment = new Environment("development", transactionFactory, dataSource);
                    Configuration configuration = new Configuration(environment);
                    configuration.addMapper(UserMapper.class);
                    sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
                }
            }
        }

        return sqlSessionFactory;
    }


    public static SqlSession openSqlSession() {
        if (sqlSessionFactory == null) {
            synchronized (CLASS_LOCK) {
                if (sqlSessionFactory == null) {
                    initSqlSessionFactory();
                }
            }
        }
        return sqlSessionFactory.openSession();
    }
}
