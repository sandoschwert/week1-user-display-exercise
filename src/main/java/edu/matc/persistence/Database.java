package edu.matc.persistence;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import org.apache.log4j.*;
/**
 * Provides access the database
 * Created on 8/31/16.
 *
 * @author pwaite
 */

public class Database {

    // create an object of the class Database
    private static Database instance = new Database();

    private Properties properties;

    private Connection connection;

    private final Logger logger = Logger.getLogger(this.getClass());

    // private constructor prevents instantiating this class anywhere else
    private Database() {
        loadProperties();

    }

    private void loadProperties() {
        properties = new Properties();
        try {
            properties.load (this.getClass().getResourceAsStream("/database.properties"));
        } catch (IOException ioe) {
            logger.error("Database.loadProperties()...Cannot load the properties file", ioe);
            //System.out.println("Database.loadProperties()...Cannot load the properties file");
            //ioe.printStackTrace();
        } catch (Exception e) {
            logger.error("Database.loadProperties()...", e);
            //System.out.println("Database.loadProperties()..." + e);
            //e.printStackTrace();
        }

    }

    // get the only Database object available
    public static Database getInstance() {
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void connect() throws Exception {
        if (connection != null)
            return;

        try {
            Class.forName(properties.getProperty("driver"));
        } catch (ClassNotFoundException e) {
            logger.error("Database.connect()...MySQL Driver not found", e);
            throw new Exception("Database.connect()... Error: MySQL Driver not found");
        }

        String url = properties.getProperty("url");
        connection = DriverManager.getConnection(url, properties.getProperty("username"),  properties.getProperty("password"));
    }

    public void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.error("Cannot close connection", e);
                //System.out.println("Cannot close connection" + e);
            }
        }

        connection = null;
    }

}