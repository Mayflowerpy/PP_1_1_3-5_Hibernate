package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("""
                    CREATE TABLE IF NOT EXISTS users_table(
                        id INT AUTO_INCREMENT PRIMARY KEY NOT NULL,
                        name VARCHAR(64) NOT NULL,
                        lastName VARCHAR(64) NOT NULL,
                        age  INT NOT NULL)
                    """).executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не удалось создать БД");
        }
    }

    @Override
    public void dropUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("DROP TABLE IF EXISTS users_table")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не удалось удалить БД");
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {

        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            User user = new User(name, lastName, age);
            session.beginTransaction();
            session.save(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не удалось добавить пользователя в БД");
        }
    }

    @Override
    public void removeUserById(long id) {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            User user = session.get(User.class, id);
            session.beginTransaction();
            session.delete(user);
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не удалось удалить пользователя из БД");
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.getCurrentSession()) {
            session.beginTransaction();
            List<User> userList = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
            return userList;
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не получить список пользователей из БД");
        }
        return null;
    }

    @Override
    public void cleanUsersTable() {
        try (SessionFactory sessionFactory = Util.getSessionFactory();
             Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.createSQLQuery("TRUNCATE TABLE users_table")
                    .executeUpdate();
            session.getTransaction().commit();
        } catch (RuntimeException e) {
            e.printStackTrace();
            System.err.println("Не удалось очистить строки БД");
        }
    }
}
