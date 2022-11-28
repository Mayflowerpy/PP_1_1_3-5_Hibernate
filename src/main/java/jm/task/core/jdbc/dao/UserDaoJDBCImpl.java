//package jm.task.core.jdbc.dao;
//
//import jm.task.core.jdbc.model.User;
//import jm.task.core.jdbc.util.Util;
//
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//
//public class UserDaoJDBCImpl implements UserDao {
//    public UserDaoJDBCImpl() {}
//
//    public void createUsersTable() {
//        String sql = "CREATE TABLE IF NOT EXISTS users_table(" +
//                "id INT PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
//                "name VARCHAR(64) NOT NULL, " +
//                "lastName VARCHAR(64) NOT NULL, " +
//                "age INT NOT NULL);";
//
//        try (Statement statement = Util.getConnection().createStatement()) {
//            statement.executeUpdate(sql);
//            System.out.println("Была создана таблица users_table");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Ошибка создания таблицы");
//        }
//    }
//
//    public void dropUsersTable() {
//        String sql = "DROP TABLE IF EXISTS users_table";
//
//        try (Statement statement = Util.getConnection().createStatement()) {
//            statement.executeUpdate(sql);
//            System.out.println("Была удалена таблица users_table");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Ошибка удаления таблицы");
//        }
//    }
//
//    public void saveUser(String name, String lastName, byte age) {
//        String sql = "INSERT INTO users_table (name, lastName, age) VALUES(?,?,?)";
//
//        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
//            preparedStatement.setString(1, name);
//            preparedStatement.setString(2, lastName);
//            preparedStatement.setByte(3, age);
//            preparedStatement.executeUpdate();
//            System.out.println("Пользователь " + name + " добавлен в БД");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Не удалось добавить пользователя в БД");
//        }
//    }
//
//    public void removeUserById(long id) {
//        String sql = "DELETE FROM users WHERE idUsers = ?";
//
//        try (PreparedStatement preparedStatement = Util.getConnection().prepareStatement(sql)) {
//            preparedStatement.setLong(1, id);
//            preparedStatement.executeUpdate();
//            System.out.println("Пользователь с ID: " + id + " - удален из БД");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Не удалось удалить пользователя из БД");
//        }
//    }
//
//    public List<User> getAllUsers() {
//        List<User> list = new ArrayList<>();
//
//        String sql = "SELECT * FROM users_table";
//
//        try (Statement statement = Util.getConnection().createStatement()) {
//            ResultSet resultSet = statement.executeQuery(sql);
//            while (resultSet.next()) {
//                User user = new User(resultSet.getString("name"),
//                        resultSet.getString("lastName"),
//                        resultSet.getByte("age"));
//                list.add(user);
//            }
//            if (!list.isEmpty()) {
//                System.out.println("Список всех пользователей: \n" + list);
//            } else {
//                System.out.println("Список пуст");
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Не удалось получить лист пользователей с БД");
//        }
//        return list;
//    }
//
//    public void cleanUsersTable() {
//        String sql = "TRUNCATE TABLE  users_table";
//
//        try (Statement statement = Util.getConnection().createStatement()) {
//            statement.execute(sql);
//            System.out.println("Таблица users_table была очищенна");
//        } catch (SQLException e) {
//            e.printStackTrace();
//            System.err.println("Ошибка очистки таблицы");
//        }
//    }
//}
