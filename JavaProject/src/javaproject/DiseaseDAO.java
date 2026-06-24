/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DiseaseDAO {



    private Connection conn;

    public DiseaseDAO() throws SQLException {
        this.conn = DatabaseConnection.getInstance().getConnection();
        if (this.conn == null) throw new SQLException("No database connection.");
    }

    // get all diseases
    public List<Disease> getAllDiseases() throws SQLException {
        List<Disease> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Disease");
        while (rs.next()) {
            list.add(new Disease(
                rs.getInt("Disease_ID"),
                rs.getString("Disease_Name"),
                rs.getString("Symptoms"),
                rs.getString("Treatment"),
                rs.getInt("Plant_ID")
            ));
        }
        return list;
    }

    // get diseases by plant id
    public List<Disease> getDiseasesByPlantId(int plantId) throws SQLException {
        List<Disease> list = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM Disease WHERE Plant_ID = ?");
        ps.setInt(1, plantId);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(new Disease(
                rs.getInt("Disease_ID"),
                rs.getString("Disease_Name"),
                rs.getString("Symptoms"),
                rs.getString("Treatment"),
                rs.getInt("Plant_ID")
            ));
        }
        return list;
    }

    // insert disease
    public boolean insertDisease(Disease d) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO Disease (Disease_Name, Symptoms, Treatment, Plant_ID) VALUES (?,?,?,?)");
        ps.setString(1, d.getDiseaseName());
        ps.setString(2, d.getSymptoms());
        ps.setString(3, d.getTreatment());
        ps.setInt(4, d.getPlantId());
        return ps.executeUpdate() > 0;
    }

    // delete disease
    public boolean deleteDisease(int diseaseId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM Disease WHERE Disease_ID = ?");
        ps.setInt(1, diseaseId);
        return ps.executeUpdate() > 0;
    }

    
}
