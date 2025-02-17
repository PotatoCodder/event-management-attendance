package event.system_attendance.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentDatabaseHandler {
    
    public static boolean insertStudentIntoDatabase(String firstName, String middleName, String lastName, String suffix, int age, String gradeLevel, String section) {
        String sql = "INSERT INTO STUDENT (first_name, middle_name, last_name, suffix, age, grade_level, section) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Connector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, firstName);
            pstmt.setString(2, middleName);
            pstmt.setString(3, lastName);
            pstmt.setString(4, suffix);
            pstmt.setInt(5, age);
            pstmt.setString(6, gradeLevel);
            pstmt.setString(7, section);

            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error inserting student: " + e.getMessage());
            return false;
        }
    }
}
