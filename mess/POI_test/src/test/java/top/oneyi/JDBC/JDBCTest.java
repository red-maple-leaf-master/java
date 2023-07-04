package top.oneyi.JDBC;

import org.junit.jupiter.api.Test;

import java.sql.*;

public class JDBCTest {

    public Connection getConnection(){
        Connection conn = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/blog?useSSL=false";
            String username = "root";
            String password = "root";
             conn = DriverManager.getConnection(url, username, password);
        }catch (Exception e){
            e.printStackTrace();
        }
        return conn;
    }

    @Test
    public void test01() throws Exception {
        Connection connection = getConnection();
        String sql = "select * from account";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()){
            //6.2 获取数据  getXxx()
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double money = resultSet.getDouble("money");

            System.out.println(id);
            System.out.println(name);
            System.out.println(money);

            System.out.println("--------------");

        }
        // 释放资源
        statement.close();
        connection.close();
        statement.close();
    }
}
