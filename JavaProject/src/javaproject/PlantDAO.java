/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlantDAO {



    private Connection conn;

    public PlantDAO() throws SQLException {
        this.conn = DatabaseConnection.getInstance().getConnection();
        if (this.conn == null) throw new SQLException("No database connection.");
    }

    // get all plants
    public List<Plant> getAllPlants() throws SQLException {
        List<Plant> list = new ArrayList<>();
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM Plant ORDER BY Plant_Name");
        while (rs.next()) {
            list.add(mapPlant(rs));
        }
        return list;
    }

    // search by name
    public List<Plant> searchByName(String name) throws SQLException {
        List<Plant> list = new ArrayList<>();
        PreparedStatement ps = conn.prepareStatement(
            "SELECT * FROM Plant WHERE Plant_Name LIKE ?");
        ps.setString(1, "%" + name + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            list.add(mapPlant(rs));
        }
        return list;
    }

    // insert new plant
    public int insertPlant(Plant p) throws SQLException {
        if (p.getPlantName() == null || p.getPlantName().trim().isEmpty()) {
            throw new IllegalArgumentException("Plant name cannot be empty.");
        }
        PreparedStatement ps = conn.prepareStatement(
            "INSERT INTO Plant (Plant_Name, Scientific_Name, Plant_Type, Description, Admin_ID) VALUES (?, ?, ?, ?, ?)",
            Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, p.getPlantName());
        ps.setString(2, p.getScientificName());
        ps.setString(3, p.getPlantType());
        ps.setString(4, p.getDescription());
        ps.setInt(5, p.getAdminId());
        ps.executeUpdate();
        ResultSet keys = ps.getGeneratedKeys(); // key 
        if (keys.next()
              ) return keys.getInt(1);
        return -1;
    }

    // update plant
    public boolean updatePlant(Plant p) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "UPDATE Plant SET Plant_Name=?, Scientific_Name=?, Plant_Type=?, Description=? WHERE Plant_ID=?");
        ps.setString(1, p.getPlantName());
        ps.setString(2, p.getScientificName());
        ps.setString(3, p.getPlantType());
        ps.setString(4, p.getDescription());
        ps.setInt(5, p.getPlantId());
        return ps.executeUpdate() > 0; //  their is row have updated
    }

    // delete plant
    public boolean deletePlant(int id) throws SQLException {
        PreparedStatement ps = conn.prepareStatement(
            "DELETE FROM Plant WHERE Plant_ID=?");
        ps.setInt(1, id);
        return ps.executeUpdate() > 0;
    }

    // delete plant + related records using transaction
    public boolean deleteWithTransaction(int plantId) throws SQLException {
        try {
            conn.setAutoCommit(false); // مب بكيفك تحفظ

            String[] tables = {"flower_plant", "herb_plant", "succulent_plant",
                               "indoor_plant", "Care_Guide", "Disease", "User_Plant"};
            for (String table : tables) {
                String col = (table.equals("Care_Guide") || table.equals("Disease")
                           || table.equals("User_Plant")) ? "Plant_ID" : "plant_id";
                PreparedStatement ps = conn.prepareStatement(
                    "DELETE FROM " + table + " WHERE " + col + "=?");
                ps.setInt(1, plantId);
                ps.executeUpdate();
            }

            PreparedStatement ps = conn.prepareStatement(
                "DELETE FROM Plant WHERE Plant_ID=?");
            ps.setInt(1, plantId);
            ps.executeUpdate();

            conn.commit();
            return true;

        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            conn.setAutoCommit(true);
        }
    }

    // map ResultSet to Plant object
    private Plant mapPlant(ResultSet rs) throws SQLException {
        int    id   = rs.getInt("Plant_ID");
        String name = rs.getString("Plant_Name");
        String sci  = rs.getString("Scientific_Name");
        String desc = rs.getString("Description");
        int    adm  = rs.getInt("Admin_ID");
        String type = rs.getString("Plant_Type");

        if (type == null) type = "Flower";

        switch (type) {
            case "Herb":      return new HerbPlant(id, name, sci, desc, adm, "", "", "");
            case "Succulent": return new SucculentPlant(id, name, sci, desc, adm, "", 0, "");
            case "Indoor":    return new IndoorPlant(id, name, sci, desc, adm, "", false, false);
            default:          return new FlowerPlant(id, name, sci, desc, adm, "", "", false); 
        }
    }

    
}
