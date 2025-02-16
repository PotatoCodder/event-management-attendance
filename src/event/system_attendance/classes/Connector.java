package event.system_attendance.classes;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = "jdbc:mysql://localhost:3308/attendance_system"; // Replace with your DB name
    private static final String USER = "root"; 
    private static final String PASSWORD = ""; 

    public static Connection connect() {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("✅ Connected to MySQL successfully!");
            return conn;
        } catch (SQLException e) {
            System.out.println("❌ Connection failed: " + e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        connect(); 
    }
}
