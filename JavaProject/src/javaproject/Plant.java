/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;

/**
 *
 * @author DELL
 */
import java.io.Serializable;

public abstract class Plant implements Serializable{

    private int    plantId;
    private String plantName;
    private String scientificName;
    private String plantType;
    private String description;
    private int    adminId;

    public Plant() {}

    public Plant(int plantId, String plantName, String scientificName,
                 String plantType, String description, int adminId) {
        this.plantId        = plantId;
        this.plantName      = plantName;
        this.scientificName = scientificName;
        this.plantType      = plantType;
        this.description    = description;
        this.adminId        = adminId;
    }
 
    // abstract method
    public abstract String getPlantCategory();
    public abstract String getSpecialCareNote();
    public abstract String getCategoryDescription();

    //
    
    public String getPlantInfo() {
        return "Plant: "        + plantName
             + "\nScientific: " + scientificName
             + "\nType: "       + plantType
             + "\nCare Note: "  + getSpecialCareNote();
    }
 // check if the plant name is correct
    public boolean isValid() { 
        return plantName != null && !plantName.trim().isEmpty();
    }

    @Override
    public String toString() {
        return plantName + " (" + scientificName + ")";
    }

    public int    getPlantId()        { return plantId; }
    public String getPlantName()      { return plantName; }
    public String getScientificName() { return scientificName; }
    public String getPlantType()      { return plantType; }
    public String getDescription()    { return description; }
    public int    getAdminId()        { return adminId; }

    public void setPlantId(int plantId)                  { this.plantId        = plantId; }
    public void setPlantName(String plantName)           { this.plantName      = plantName; }
    public void setScientificName(String scientificName) { this.scientificName = scientificName; }
    public void setPlantType(String plantType)           { this.plantType      = plantType; }
    public void setDescription(String description)       { this.description    = description; }
    public void setAdminId(int adminId)                  { this.adminId        = adminId; }
}

