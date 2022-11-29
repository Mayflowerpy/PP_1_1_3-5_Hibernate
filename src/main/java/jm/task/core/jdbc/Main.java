package jm.task.core.jdbc;


import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        Util.getSessionFactory();
        UserService userServ = new UserServiceImpl();

        userServ.createUsersTable();

        userServ.saveUser("Frodo", "Baggins", (byte) 50);
        userServ.saveUser("Samwise", "Gamgee", (byte) 38);
        userServ.saveUser("Merry", "Brandybuck", (byte) 36);
        userServ.saveUser("Pippin", "Took", (byte) 28);

        userServ.removeUserById(3);
//        userServ.cleanUsersTable();
//        userServ.dropUsersTable();
    }
}
