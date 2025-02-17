package event.system_attendance.classes;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class Event {

    public static void loadEvents(DefaultTableModel model) {
        String sql = "SELECT event_name, venue, date FROM EVENT";

        try (Connection conn = Connector.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("event_name");
                String venue = rs.getString("venue");
                String date = rs.getString("date");

                model.addRow(new Object[]{name, venue, date}); // Add row to table
            }

        } catch (SQLException e) {
            System.out.println("❌ Error loading events: " + e.getMessage());
        }
    }
}
