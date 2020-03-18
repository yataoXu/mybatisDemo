#### mybatis中几个基本的组件：
1. **SqlSessionFactoryBuilder**：这是一个SqlSessionFactory的构造器，它根据我们的xml配置文件或者Java代码来生成SqlSessionFactory。
2. **SqlSessionFactory**：这个有点类似于我们在JDBC中使用的Connection，我们到时候要根据SqlSessionFactory来生成是一个会话，也就是SqlSession。
3. **SqlSession**：它可以发送一条SQL语句去执行，并返回结果，从这个角度来说，它有点类似于PrepareStatement，当然，我们也可以利用SqlSession获取Mapper的接口，这个算是SqlSession的一个核心用法了。
4. **Mapper**：Mapper也可以发送一条SQL语句并返回执行结果，Mapper由两部分组成，一部分是Java接口，另一部分是XML配置文件或者注解。



#### 创建配置类并获取SqlSessionFactory

```
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

```

DBUtils工具类：
1. 首先该类一共两个工具方法，一个用来获取SqlSessionFactory，另一个用来获取SqlSession
2. 获取SqlSessionFactory的使用我们采用了单例模式，这是常见的单例写法，不用多说
3. 在获取SqlSessionFactory的方法中，我们通过PooledDataSource对象的实例来设置数据库驱动、数据库连接地址、数据库用户名和密码等。
4. 创建了数据库的运行环境，并将这个环境命名为development
5. 添加了一个映射器，这个映射器就是我们前文说的Mapper，用来执行一个SQL 语句。
6. 有了SqlSessionFactory之后接下来就可以通过SqlSessionFactory来构建一个SqlSession了。
