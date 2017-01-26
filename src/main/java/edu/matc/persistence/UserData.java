package edu.matc.persistence;

import edu.matc.entity.User;
import org.apache.log4j.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Access users in the user table.
 *
 * @author pwaite
 */
public class UserData {
    //private List<User> users;
    private final Logger logger = Logger.getLogger(this.getClass());

    /*
    public List<User> getAllUsers() {
        String sql = "SELECT * FROM users";

        users = getUserData(sql);
        return users;
    }
    */

    public List<User> getUser(String lastName) {
        List<User> users = new ArrayList<User>();
        String sql = "";
        if (lastName == null || lastName == "") {
            sql = "SELECT * FROM users";
        } else {
            sql = "SELECT * FROM users WHERE last_name LIKE '" + lastName + "'";
            logger.info("Last Name is " + lastName);
        }

        users = getUserData(sql);
        return users;
    }

    public List<User> getUserData(String sql) {
        logger.debug("In the getUserData method");

        List<User> users = new ArrayList<User>();
        Database database = Database.getInstance();
        Connection connection = null;

        try {
            database.connect();
            connection = database.getConnection();
            Statement selectStatement = connection.createStatement();
            ResultSet results = selectStatement.executeQuery(sql);
            while (results.next()) {
                User employee = createUserFromResults(results);
                users.add(employee);
            }
            database.disconnect();
        } catch (SQLException e) {
            logger.error("SearchUser.getAllUsers()...SQL Exception: ", e);
        } catch (Exception e) {
            logger.error("SearchUser.getAllUsers()...Exception: ", e);
        }
        return users;
    }


    private User createUserFromResults(ResultSet results) throws SQLException {
        logger.debug("In the createUserFromResults method");
        User user = new User();
        user.setLastName(results.getString("last_name"));
        user.setFirstName(results.getString("first_name"));
        user.setUserid(results.getString("id"));
        user.setBirthdate(LocalDate.parse(results.getString( "date_of_birth")));
        return user;
    }

}