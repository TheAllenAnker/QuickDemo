package com.allen_anker.jdbc;

import com.mysql.jdbc.Driver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class JDBCDemo1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        Connection connection = null;
        Statement statement = null;
        ResultSet rs = null;

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("jdbc.properties")));

        Class.forName(properties.getProperty("driver"));
        try {
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();
            String query = "SELECT * FROM student";
            rs = statement.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("stu_name");
                int age = rs.getInt("age");
                System.out.println("Id: " + id + ", Name: " + name + ", Age: " + age + ".");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
