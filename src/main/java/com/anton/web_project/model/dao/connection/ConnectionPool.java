package com.anton.web_project.model.dao.connection;

import com.anton.web_project.model.exception.DaoException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.ResourceBundle;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger logger = LogManager.getLogger();
    private static final String RESOURCE_BUNDLE = "property.database";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String URL = "url";
    private static final String DRIVER = "driver";
    private static final int POOL_SIZE = 4;
    private static ConnectionPool instance = new ConnectionPool();
    private LinkedBlockingQueue<ProxyConnection> freeConnections;
    private Queue<ProxyConnection> busyConnections;

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool() {
        try {
            ResourceBundle rb = ResourceBundle.getBundle(RESOURCE_BUNDLE);
            String driver = rb.getString(DRIVER);
            Class.forName(driver);
            Connection connection;
            String url = rb.getString(URL);
            String username = rb.getString(USERNAME);
            String password = rb.getString(PASSWORD);
            freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
            busyConnections = new ArrayDeque<>(POOL_SIZE);
            for (int i = 0; i < POOL_SIZE; i++) {
                connection = DriverManager.getConnection(url, username, password);
                freeConnections.offer(new ProxyConnection(connection));
            }
        } catch (ClassNotFoundException e) {
            logger.log(Level.FATAL, "Can't create connection pool", e);//todo make it elegant (in lesson good explanation)
            throw new RuntimeException("Can't create connection pool", e);
        } catch (SQLException e) {
            logger.log(Level.FATAL, "Can't create connection pool", e);
            throw new RuntimeException("Can't create connection pool", e);
        }
    }

    public Connection getConnection() throws DaoException {
        ProxyConnection connection;
        try {
            connection = freeConnections.take();
            busyConnections.offer(connection);
        } catch (InterruptedException e) {
            throw new DaoException("Can't get connection", e);
        }
        return connection;
    }

    public void releaseConnection(Connection connection){
        if (connection instanceof ProxyConnection) {
            if (busyConnections.remove(connection)){
                freeConnections.offer((ProxyConnection) connection);
            }
        } else {
            logger.log(Level.WARN, "Invalid connection to realizing");
        }
    }

    public void deactivatePool(){
        try {
            for (int i = 0; i < freeConnections.size(); i++) {
                freeConnections.take().fullyClose();
            }
            deactivateDriver();
        } catch (SQLException | InterruptedException e) {
            logger.log(Level.ERROR, "Can't destroy pool");
        }
    }

    private void deactivateDriver() throws SQLException {
        while (DriverManager.getDrivers().hasMoreElements()) {
            DriverManager.deregisterDriver(DriverManager.getDrivers().nextElement());
        }
    }
}
