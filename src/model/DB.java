package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    // Подключение к базе данных users
    private static final String USERS_DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERS_DB_USER = "root";
    private static final String USERS_DB_PASSWORD = "admin";

    // Подключение к базе данных university
    private static final String UNIVERSITY_DB_URL = "jdbc:mysql://localhost:3306/university";
    private static final String UNIVERSITY_DB_USER = "root";
    private static final String UNIVERSITY_DB_PASSWORD = "admin";

    // Получить подключение к БД users
    public static Connection getUsersDBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(USERS_DB_URL, USERS_DB_USER, USERS_DB_PASSWORD);
    }

    // Получить подключение к БД university
    public static Connection getUniversityDBConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(UNIVERSITY_DB_URL, UNIVERSITY_DB_USER, UNIVERSITY_DB_PASSWORD);
    }

    // Проверить пользователя и получить роль
    public static String checkUserAndGetRole(String login, String password) {
        System.out.println("Проверка пользователя: " + login);

        String query = "SELECT role FROM user WHERE login = ? AND password = ?";
        try (Connection conn = getUsersDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");

                return role;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    // Регистрация нового пользователя с ролью "user"
    public static boolean registerUser(String login, String password) {
        String query = "INSERT INTO user (login, password, role) VALUES (?, ?, 'user')";
        try (Connection conn = getUsersDBConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Получить список профессоров (для admin)
    public static ResultSet getAllProfessors() throws Exception {
        String query = "SELECT * FROM professors";
        Connection conn = getUniversityDBConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }

    // Получить список предметов (для user)
    public static ResultSet getAllSubjects() throws Exception {
        String query = "SELECT * FROM subjects";
        Connection conn = getUniversityDBConnection();
        PreparedStatement stmt = conn.prepareStatement(query);
        return stmt.executeQuery();
    }
}
