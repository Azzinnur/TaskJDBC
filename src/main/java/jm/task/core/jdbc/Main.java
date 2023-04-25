package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Vasiliy", "Pupkin", (byte)24);
        userService.saveUser("Ilya", "Ivanov", (byte)45);
        userService.saveUser("Tatyana", "Grach", (byte)20);
        userService.saveUser("Alexey", "Graboff", (byte)64);
        userService.getAllUsers().stream().map(User::toString).forEach(System.out::println);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
