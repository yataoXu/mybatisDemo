package com.jdbc;

import java.sql.*;

/**
 * @Classname JdbcTest
 * @Description
 * @Date 2020/3/18 12:40
 * @Created by Evan
 */
public class JdbcTest {

    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            // 获取连接
            String url = "jdbc:mysql://10.2.192.31:3306/ssmdemo";
            String user = "root";
            String password = "anyRobot123";
            Connection connection1 = DriverManager.getConnection(url, user, password);
            //

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
