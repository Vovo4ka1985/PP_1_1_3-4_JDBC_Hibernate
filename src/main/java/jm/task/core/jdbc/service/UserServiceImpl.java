package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    //UserDao userDao = new UserDaoJDBCImpl();
    UserDaoHibernateImpl userDaoHibernate = new UserDaoHibernateImpl();

    public void createUsersTable() {
        userDaoHibernate.createUsersTable();
   // userDao.createUsersTable();
    }

    public void dropUsersTable() {
        userDaoHibernate.dropUsersTable();
        //userDao.dropUsersTable();

    }

    public void saveUser(String name, String lastName, byte age) {

        userDaoHibernate.saveUser(name, lastName, age);
        //userDao.saveUser(name, lastName, age);
    }

    public void removeUserById(long id) {
        userDaoHibernate.removeUserById(id);
    //userDao.removeUserById(id);
    }

    public List<User> getAllUsers() {

       return userDaoHibernate.getAllUsers();


    }

    public void cleanUsersTable() {
        userDaoHibernate.cleanUsersTable();
    }

}
