package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Util {
    // реализуйте настройку соеденения с БД

    public static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                SessionFactory sessionFactory = new Configuration()
                        .configure("hibernate.cfg.xml")
                        .addAnnotatedClass(User.class)
                        .buildSessionFactory();
                System.out.println("Подключение успешно!");
            } catch (RuntimeException e) {
                e.printStackTrace();
                System.err.println("Нет подключения!");
            }
        }
        return sessionFactory;
    }
}
