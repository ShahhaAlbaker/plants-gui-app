/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

/**
 *
 * @author DELL
 */
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CareGuideDAO {



    private Connection conn;

    public CareGuideDAO() throws SQLException {
        this.conn = DatabaseConnection.getInstance().getConnection();
        if (this.conn == null) throw new SQLException("No database connection.");
    }

    // get all care guides
    public List<CareGuide> getAllCareGuides() throws SQLException {
        List<CareGuide> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Care_Guide");
        while (rs.next()) {
            list.add(new CareGuide(
                rs.getInt("Care_ID"),
                rs.getString("Watering_Frequency"),
                rs.getString("Sunlight_Needs"),
                rs.getString("Soil_Type"),
                rs.getString("Temperature"),
                rs.getInt("Plant_ID")
            ));
        }
        return list;
    }

    // get care guide by plant id
    public CareGuide getCareGuideByPlantId(int plantId) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM Care_Guide WHERE Plant_ID = ?");
        ps.setInt(1, plantId);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return new CareGuide(
                rs.getInt("Care_ID"),
                rs.getString("Watering_Frequency"),
                rs.getString("Sunlight_Needs"),
                rs.getString("Soil_Type"),
                rs.getString("Temperature"),
                rs.getInt("Plant_ID")
            );
        }
        return null;
    }

    // insert care guide
    public boolean insertCareGuide(CareGuide cg) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO Care_Guide (Watering_Frequency, Sunlight_Needs, Soil_Type, Temperature, Plant_ID) VALUES (?,?,?,?,?)");
        ps.setString(1, cg.getWateringFrequency());
        ps.setString(2, cg.getSunlightNeeds());
        ps.setString(3, cg.getSoilType());
        ps.setString(4, cg.getTemperature());
        ps.setInt(5, cg.getPlantId());
        return ps.executeUpdate() > 0;
    }

    // update care guide
    public boolean updateCareGuide(CareGuide cg) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE Care_Guide SET Watering_Frequency=?, Sunlight_Needs=?, Soil_Type=?, Temperature=? WHERE Care_ID=?");
        ps.setString(1, cg.getWateringFrequency());
        ps.setString(2, cg.getSunlightNeeds());
        ps.setString(3, cg.getSoilType());
        ps.setString(4, cg.getTemperature());
        ps.setInt(5, cg.getCareId());
        return ps.executeUpdate() > 0;
    }

    
}
