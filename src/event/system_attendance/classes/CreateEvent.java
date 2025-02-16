package event.system_attendance.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CreateEvent {
    
    public static boolean createEvent(String eventName, String venue, String dateString) {
        String sql = "INSERT INTO EVENT (event_name, venue, date) VALUES (?, ?, ?)";

        try (Connection conn = Connector.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, eventName);
            pstmt.setString(2, venue);
            
            // Convert String to java.sql.Date
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date utilDate = sdf.parse(dateString);  // Parse string to java.util.Date
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime()); // Convert to java.sql.Date
            
            pstmt.setDate(3, sqlDate);
            
            int rowsInserted = pstmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.out.println("❌ Error inserting event: " + e.getMessage());
            return false;
        } catch (ParseException e) {
            System.out.println("❌ Date format error: " + e.getMessage());
            return false;
        }
    }
}
