package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.*;
import jm.task.core.jdbc.model.User;

public class Main {
    public static void main(String[] args) {
        UserDao userDao = new UserDaoJDBCImpl();
        userDao.createUsersTable();
        userDao.saveUser("Vasiliy", "Pupkin", (byte)24);
        userDao.saveUser("Ilya", "Ivanov", (byte)45);
        userDao.saveUser("Tatyana", "Grach", (byte)20);
        userDao.saveUser("Alexey", "Graboff", (byte)64);
        userDao.getAllUsers().stream().map(User::toString).forEach(System.out::println);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
