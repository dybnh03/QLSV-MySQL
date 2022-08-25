package com.duybinh.qlsvphake;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static com.duybinh.qlsv.Main.getConnection;

public class Main {
    private static String DB_URL = "jdbc:mysql://localhost:3306/sakila";
    private static String USER_NAME = "root";
    private static String PASSWORD = "duybinh";

    public static void main(String[] args) {

        try {
            // connnect to database 'sakila'
            Connection conn = getConnection(DB_URL, USER_NAME, PASSWORD);
            // create statement
            Statement stmt = conn.createStatement();
            // get data from table 'sinhvien'
            ResultSet rs = stmt.executeQuery("select * from sakila.film_text");
            // show data
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "  " + rs.getString(2)
                        + "  " + rs.getString(3));
            }
            // close connection
            conn.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }

    public static Connection getConnection(String dbURL, String userName,
                                           String password) {
        Connection conn = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(dbURL, userName, password);
            System.out.println("connect successfully!");
        } catch (Exception ex) {
            System.out.println("connect failure!");
            ex.printStackTrace();
        }
        return conn;

    }
}

