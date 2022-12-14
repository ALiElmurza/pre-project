package jm.task.core.jdbc.dao;


import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement()) {
            String sql = "CREATE TABLE user (id BIGINT UNSIGNED NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY, name TEXT, lastName TEXT, age TINYINT)";
            statement.executeUpdate(sql);
        } catch (SQLSyntaxErrorException ignore) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void dropUsersTable() {
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();) {
            String sql = "DROP TABLE user";
            statement.executeUpdate(sql);
        } catch (SQLSyntaxErrorException ignore) {

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) throws SQLException {
        Connection connection = Util.getMySQLConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = "INSERT INTO user (name, lastName, age) values (" + "'" +
                    name + "'" + "," + "'" + lastName + "'" + "," + age + ");";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    public void removeUserById(long id) throws SQLException {
        Connection connection = Util.getMySQLConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM user WHERE id = " + id;
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }

    public List<User> getAllUsers() {
        List<User> usersList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = Util.getMySQLConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            long index = 1;
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastName");
                byte age = resultSet.getByte("age");
                User user = new User(name, lastName, age);
                user.setId(index++);
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usersList;
    }

    public void cleanUsersTable() throws SQLException {
        Connection connection = Util.getMySQLConnection();
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            String sql = "DELETE FROM user";
            statement.executeUpdate(sql);
            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
        } finally {
            connection.close();
        }
    }
}
