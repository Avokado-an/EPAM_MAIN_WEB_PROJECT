package com.anton.web_project.model.dao.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConnectionProvider {
    private ConnectionProvider() {

    }

    public static Connection provideConnection() throws SQLException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection;
            ResourceBundle rb = ResourceBundle.getBundle("property.database");
            String url = rb.getString("url");
            String username = rb.getString("username");
            String password = rb.getString("password");
            connection = DriverManager.getConnection(url, username, password);
            return connection;
        } catch (ClassNotFoundException e) {
            throw new SQLException("Can't get Class.forName()");
        }
    }
}
