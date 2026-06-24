/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class UserDAO {



    private Connection conn;

    public UserDAO() throws SQLException {
        this.conn = DatabaseConnection.getInstance().getConnection();
        if (this.conn == null) throw new SQLException("No database connection.");
    }

    // login as Admin
    public Admin loginAdmin(String email, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM Admin WHERE Email = ? AND Pass_word = ?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new Admin(
                rs.getInt("Admin_ID"),
                rs.getString("Username"),
                rs.getString("Email"),
                rs.getString("Pass_word")
            );
        }
        return null;
    }

    // login as RegularUser
    public RegularUser loginUser(String email, String password) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM User WHERE Email = ? AND Password = ?");
        ps.setString(1, email);
        ps.setString(2, password);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new RegularUser(
                rs.getInt("User_ID"),
                rs.getString("Username"),
                rs.getString("Email"),
                rs.getString("Password"),
                rs.getString("Role")
            );
        }
        return null;
    }

    // get all users
    public List<RegularUser> getAllUsers() throws SQLException {
        List<RegularUser> users = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM User");
        while (rs.next()) {
            users.add(new RegularUser(
                rs.getInt("User_ID"),
                rs.getString("Username"),
                rs.getString("Email"),
                rs.getString("Password"),
                rs.getString("Role")
            ));
        }
        return users;
    }

    // insert new user
    public boolean insertUser(RegularUser u) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO User (Username, Email, Password, Role) VALUES (?, ?, ?, ?)");
        ps.setString(1, u.getUsername());
        ps.setString(2, u.getEmail());
        ps.setString(3, u.getPassword());
        ps.setString(4, u.getUserRole());
        return ps.executeUpdate() > 0;
    }

    // delete user
    public boolean deleteUser(int userId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM User WHERE User_ID = ?");
        ps.setInt(1, userId);
        return ps.executeUpdate() > 0;
    }

    
}
