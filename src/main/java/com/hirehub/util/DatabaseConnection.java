package com.hirehub.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

//contains helper classes, code to connect to database. 

public class DatabaseConnection {
    private static Connection connection = null;

    // check connection is established
    public static Connection getConnection() { // static so we can directly access the class without creating an object
        if (connection != null) {
            System.out.println("Returning existing database connection");
            return connection;
        } else {
            System.out.println("Creating new database connection...");
            try {
                Properties prop = new Properties();
                InputStream inputStream = DatabaseConnection.class.getClassLoader()
                        .getResourceAsStream("database.properties");

                if (inputStream == null) {
                    System.out.println("ERROR: database.properties file not found!");
                    return null;
                }

                prop.load(inputStream);

                String dbDriver = "com.mysql.cj.jdbc.Driver";
                String dbURL = prop.getProperty("db.url");
                String dbUser = prop.getProperty("db.user");
                String dbPassword = prop.getProperty("db.password");

                System.out.println("Database URL: " + dbURL);
                System.out.println("Database User: " + dbUser);

                Class.forName(dbDriver); // loads class with name specified in dbdriver
                System.out.println("Database driver loaded successfully");

                connection = DriverManager.getConnection(dbURL, dbUser, dbPassword);
                System.out.println("Database connection established successfully");
            } catch (ClassNotFoundException e) {
                System.out.println("Database driver not found: " + e.getMessage());
                e.printStackTrace();
            } catch (SQLException e) {
                System.out.println("Database connection error: " + e.getMessage());
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Error loading database properties: " + e.getMessage());
                e.printStackTrace();
            }
            return connection;
        }
    }
}
