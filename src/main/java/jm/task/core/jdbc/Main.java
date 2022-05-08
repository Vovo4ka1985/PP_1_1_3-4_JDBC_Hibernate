package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Aleksey", "Petrov", (byte) 20);
        userService.saveUser("Ivan", "Dorn", (byte) 25);
        userService.saveUser("Vladimir", "Sidorov", (byte) 36);
        userService.saveUser("Darya", "Sidorova", (byte) 28);

        userService.getAllUsers();
        //userService.removeUserById(1);
        userService.cleanUsersTable();
        userService.dropUsersTable();


        // реализуйте алгоритм здесь
    }
}
