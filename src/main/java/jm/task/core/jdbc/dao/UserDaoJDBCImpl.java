package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection = Util.getConnection();

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS `Users` (\n"
                    + "  `ID` int NOT NULL AUTO_INCREMENT,\n"
                    + "  `NAME` varchar(100) NOT NULL,\n"
                    + "  `LASTNAME` varchar(100) NOT NULL,\n"
                    + "  `AGE` int DEFAULT 0,\n"
                    + "  PRIMARY KEY (`ID`)\n"
                    + ") ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=1;");
        } catch (SQLException e) {
            System.out.println("Cannot create user table!");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS mydbtest.users");
        } catch (SQLException e) {
            System.out.println("Cannot delete user table!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (Statement statement = connection.createStatement()) {
            String s = String.format("INSERT INTO users (name, lastname, age) VALUES ('%s', '%s', %d);", name, lastName, age);
            statement.executeUpdate(s);
            System.out.printf("User с именем %s добавлен в базу данных.\n", name);
        } catch (SQLException e) {
            System.out.println("Cannot insert data!");
        }
    }

    public void removeUserById(long id) {
        try (Statement statement = connection.createStatement()) {
            String s = String.format("DELETE FROM users WHERE id = %d;", id);
            statement.executeUpdate(s);
        } catch (SQLException e) {
            System.out.println("Cannot delete data!");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            String s = "SELECT id, name, lastname, age FROM users";
            ResultSet rs = statement.executeQuery(s);
            while (rs.next()) {
                users.add(new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4)));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Failed to get all users");
        }
        return null;
    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            String s = "DELETE FROM users;";
            statement.executeUpdate(s);
        } catch (SQLException e) {
            System.out.println("Cannot delete data!");
        }
    }
}
