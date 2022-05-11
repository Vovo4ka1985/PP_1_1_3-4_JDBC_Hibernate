package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl{ //implements UserDao {


    private static Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection conn = Util.getConnection();
                Statement statement = conn.createStatement();) {
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS `user` (" +
                    "`id` BIGINT NOT NULL AUTO_INCREMENT," +
                    "`name` VARCHAR(45) NOT NULL," +
                    "`lastName` VARCHAR(45) NOT NULL," +
                    "`age` INT(3) NOT NULL," +
                    "PRIMARY KEY (`id`));");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void dropUsersTable() {
        try (Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection conn = Util.getConnection();
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO User (name, lastName, age)VALUES(?, ?, ?) ")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.println("User " + name + " was added to table");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (Connection conn = Util.getConnection();
                PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM User WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Connection conn = Util.getConnection();
            Statement statement = conn.createStatement()) {
            String SQL = "SELECT * FROM User";      // Select id, name, lastName, age FROM User
           ResultSet resultSet = statement.executeQuery(SQL);
           while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setName(resultSet.getString("name"));
               user.setLastName(resultSet.getString("lastName"));
               user.setAge(resultSet.getByte("age"));
               users.add(user);

           }
            for (User us: users
            ) { System.out.println(us);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

      return users;


    }

    public void cleanUsersTable() {
        try (Connection conn = Util.getConnection();
                Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE USER"); //DELETE FROM  users
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
