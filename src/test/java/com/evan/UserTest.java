package com.evan;


import com.evan.bean.User;
import com.evan.mapper.UserMapper;
import com.evan.utils.DBUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

public class UserTest {

    @Test
    public void setId() {
        SqlSession sqlSession = null;
        try {
            sqlSession = DBUtils.openSqlSession();
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.getUser(1L);
            System.out.println(user.toString());
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
