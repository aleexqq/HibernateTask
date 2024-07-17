package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;


public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        userService.createUsersTable();

        userService.saveUser("Alex", "Ss", (byte) 18);
        userService.saveUser("John", "Smith", (byte) 22);
        userService.saveUser("Andrew", "WW", (byte) 15);
        userService.saveUser("Michael", "Jones", (byte) 27);

        userService.getAllUsers();

        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}