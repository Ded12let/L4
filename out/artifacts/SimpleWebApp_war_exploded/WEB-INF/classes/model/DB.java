import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DB {
    private static final String URL = "jdbc:mysql://localhost:3306/users";
    private static final String USER = "root";
    private static final String PASSWORD = "admin";

    // Метод для получения подключения к базе данных
    public static Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Метод для проверки существования пользователя
    public static boolean checkUser(String login, String password) {
        String query = "SELECT * FROM user WHERE login = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Если есть результат, пользователь существует
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для регистрации нового пользователя
    public static boolean registerUser(String login, String password) {
        String query = "INSERT INTO user (login, password) VALUES (?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            int rowsAffected = stmt.executeUpdate();
            return rowsAffected > 0; // Если добавлен хотя бы один ряд, регистрация успешна
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Метод для вывода таблицы users в консоль
    public static void printUsersTable() {
        String query = "SELECT * FROM user";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            System.out.println("Содержимое таблицы user:");
            while (rs.next()) {
                int id = rs.getInt("id"); // Изменено с id_user на id
                String login = rs.getString("login");
                String password = rs.getString("password");
                System.out.printf("ID: %d, Login: %s, Password: %s%n", id, login, password);
            }
        } catch (Exception e) {
            System.err.println("Ошибка при выводе таблицы user:");
            e.printStackTrace();
        }
    }
    public static String getUserRole(String login, String password) {
        String query = "SELECT role FROM user WHERE login = ? AND password = ?";
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, login);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getString("role");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}