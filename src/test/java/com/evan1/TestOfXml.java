package com.evan1;

import com.evan1.mapper.UserMapper;
import com.evan1.pojo.User;
import com.evan1.util.DBUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

/**
 * @Classname TestOfXml
 * @Description
 * @Date 2020/3/18 13:13
 * @Created by Evan
 */
public class TestOfXml {

    @Test
    public void Test01() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(1l);
            System.out.println(user);
            sqlSession.commit();
        } catch (Exception e) {
            e.printStackTrace();
            sqlSession.rollback();
        } finally {
            if (sqlSession != null) {
                sqlSession.close();
            }
        }
    }

}
