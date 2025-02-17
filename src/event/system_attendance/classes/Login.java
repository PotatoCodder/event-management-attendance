package event.system_attendance.classes;

import java.sql.*;
import javax.swing.JOptionPane;

public class Login {

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT password, isLoggedIn FROM STAFF WHERE username = ?";
        boolean isAuthenticated = false;

        try (Connection conn = Connector.connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                boolean isLoggedIn = rs.getBoolean("isLoggedIn");

                if (storedPassword.equals(password)) {
                    // If the password matches, check if the user is already logged in
                    if (!isLoggedIn) {
                        // Update the `isLoggedIn` column for this user
                        setUserLoggedIn(username);
                        isAuthenticated = true;
                    } else {
                        JOptionPane.showMessageDialog(null, "User is already logged in.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return isAuthenticated;
    }

    // Set the `isLoggedIn` field to true for the current user and false for others
    private void setUserLoggedIn(String username) {
        String updateQuery = "UPDATE STAFF SET isLoggedIn = TRUE WHERE username = ?";
        String resetQuery = "UPDATE STAFF SET isLoggedIn = FALSE WHERE username != ?";

        try (Connection conn = Connector.connect(); 
             PreparedStatement resetStmt = conn.prepareStatement(resetQuery);
             PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {

            // Reset other users' isLoggedIn to false
            resetStmt.setString(1, username);
            resetStmt.executeUpdate();

            // Set this user's isLoggedIn to true
            updateStmt.setString(1, username);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error updating login status: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
