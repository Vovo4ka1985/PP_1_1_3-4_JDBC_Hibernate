package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private static final Connection conn = Util.getConnection();

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("CREATE TABLE if NOT EXISTS USER ("
                    + "   id INT NOT NULL AUTO_INCREMENT, name VARCHAR(30) NOT NULL, lastname VARCHAR(50) NOT NULL, "
                    + "   age INT NOT NULL, PRIMARY KEY (id) ); ");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dropUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS user");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try {
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO User (name, lastName, age)VALUES(?, ?, ?) ");
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = conn.prepareStatement("DELETE FROM User WHERE id = ?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = Util.getConnection();
        try {
            Statement statement = conn.createStatement();
            String SQL = " Select id, name, lastName, age FROM User"; //SELECT * FROM User
           ResultSet resultSet = statement.executeQuery(SQL);
           while (resultSet.next()) {
               User user = new User();
               user.setId(resultSet.getLong("id"));
               user.setName(resultSet.getString("name"));
               user.setLastName(resultSet.getString("lastName"));
               user.setAge(resultSet.getByte("age"));
               users.add(user);
           }

        } catch (SQLException e) {
            e.printStackTrace();
        }
      return users;

    }

    public void cleanUsersTable() {
        try (Statement statement = conn.createStatement()) {
            statement.executeUpdate("TRUNCATE TABLE USER"); //DELETE FROM  users
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
