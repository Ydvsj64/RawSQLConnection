package com.example.dbapp;

import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
public class DbManager {

    public DbManager() throws SQLException {
        getConnection();
        createTable();
    }
    public Connection connection;

    public Connection getConnection() throws SQLException {
        if(connection == null){
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentdb","root","Sjy@1996");
        }
        return connection;
    }
    public void createTable() throws SQLException {

        String sql = "create table if not exists student_info(id INT primary key auto_increment,age INT,name VARCHAR(30))";
        Statement st = connection.createStatement();

        boolean return_val = st.execute(sql);
        System.out.println(return_val);
    }

    public void insert_info(Student s) throws SQLException {
        String sql = "insert into student_info(age,name) values("+s.getAge()+",'"+s.getName()+"')";
        Statement st = connection.createStatement();
        int rows = st.executeUpdate(sql);
        System.out.println("Number of rows affected = "+rows);
    }

    public void getAllStudents() throws SQLException {
        String sql = "select * from student_info";
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(sql);

        while (rs.next()){
            String name = rs.getString("name");
            String id = rs.getString("id");
            String age = rs.getString("age");

            System.out.println(name+" "+id+" "+age);
        }
    }
}
