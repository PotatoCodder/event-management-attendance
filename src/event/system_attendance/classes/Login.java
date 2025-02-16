/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event.system_attendance.classes;

import java.sql.*;
import javax.swing.JOptionPane;

public class Login {

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT password FROM STAFF WHERE username = ?";
        
        try (Connection conn = Connector.connect(); 
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // Consider hashing for better security
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        return false;
    }
}
