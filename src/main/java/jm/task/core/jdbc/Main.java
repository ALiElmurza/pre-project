package jm.task.core.jdbc;


import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.Iterator;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        UserDao createTable = new UserDaoJDBCImpl();
        createTable.createUsersTable();
        createTable.saveUser("Али", "Ельмурзаев", (byte) 31);
        createTable.saveUser("Генадий", "Котов", (byte) 56);
        createTable.saveUser("Иван", "Иванов", (byte) 67);


        List<User> userList = createTable.getAllUsers();
        Iterator<User> iterator = userList.listIterator();
        while (iterator.hasNext()) {
            User user = iterator.next();
            System.out.println("----------------------");
            System.out.println(user.getId() + " " +
                    user.getName() + " " +
                    user.getLastName() + " " +
                    user.getAge());
        }

        createTable.cleanUsersTable();

        createTable.removeUserById(1);

        createTable.dropUsersTable();

    }
}


