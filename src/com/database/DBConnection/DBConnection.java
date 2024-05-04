package com.database.DBConnection;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class DBConnection {
    public static Connection getConnection() {
        String url = null;
        String user = null;
        String password = null;
        try {
            Properties properties = new Properties();
            FileInputStream fileInputStream = new FileInputStream("src/DBConnectionSettings.properties");
            properties.load(fileInputStream);
            url = properties.getProperty("db.url");
            user = properties.getProperty("db.user");
            password = properties.getProperty("db.password");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
