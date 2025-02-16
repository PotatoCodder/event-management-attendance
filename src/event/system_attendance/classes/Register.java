/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package event.system_attendance.classes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Register {
    public static boolean registerUser(String firstName, String middleName, String lastName, String suffix, String username, String password) {
        String query = "INSERT INTO STAFF (first_name, middle_name, last_name, suffix, username, password) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conn = Connector.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
             
            stmt.setString(1, firstName);
            stmt.setString(2, middleName);
            stmt.setString(3, lastName);
            stmt.setString(4, suffix);
            stmt.setString(5, username);
            stmt.setString(6, password);
            
            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;
            
        } catch (SQLException e) {
            System.out.println("❌ Registration failed: " + e.getMessage());
            return false;
        }
    }
}
