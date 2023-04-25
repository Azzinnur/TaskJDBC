package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection = Util.getConnection();

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
            System.out.println("Ошибка при создании таблицы!");
        }
    }

    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS mydbtest.users");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении таблицы!");
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (name, lastname, age) VALUES (?, ?, ?);")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();
            System.out.printf("User с именем %s добавлен в базу данных.\n", name);
        } catch (SQLException e) {
            System.out.printf("Oшибка при добавлении User %s в базу данных!\n", name);
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM users WHERE id = ?;")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении данных!");
        }
    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT id, name, lastname, age FROM users");
            while (rs.next()) {
                users.add(new User(rs.getString(2), rs.getString(3), (byte) rs.getInt(4)));
            }
            return users;
        } catch (SQLException e) {
            System.out.println("Ошибка при создании списка Юзеров из базы данных!");
        }
        return null;

    }

    public void cleanUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users;");
        } catch (SQLException e) {
            System.out.println("Ошибка при удалении данных из таблицы!");
        }
    }
}
