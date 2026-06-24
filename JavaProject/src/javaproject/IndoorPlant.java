/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.io.Serializable;


public class  IndoorPlant extends Plant implements Serializable{



    private int     indoorId;
    private String  lightRequirement;
    private boolean airPurifying;
    private boolean petFriendly;

    public IndoorPlant() { super(); }

    public IndoorPlant(int plantId, String plantName, String scientificName,
                       String description, int adminId,
                       String lightRequirement, boolean airPurifying, boolean petFriendly) {
        super(plantId, plantName, scientificName, "Indoor", description, adminId);
        this.lightRequirement = lightRequirement;
        this.airPurifying     = airPurifying;
        this.petFriendly      = petFriendly;
    }

    @Override
    public String getPlantCategory() {
        return "Indoor";
    }

    @Override
    public String getSpecialCareNote() {
        return "Keep away from direct sunlight. Ensure pots have drainage holes.";
    }

    @Override
    public String getCategoryDescription() {
        return "Indoor plants thrive in home and office environments.";
    }

    public String getSuitabilityInfo() {
        return getPlantName()
             + (airPurifying ? " - purifies air" : "")
             + (petFriendly  ? " - safe for pets" : " - toxic to pets");
    }

    public int     getIndoorId()         { return indoorId; }
    public String  getLightRequirement() { return lightRequirement; }
    public boolean isAirPurifying()      { return airPurifying; }
    public boolean isPetFriendly()       { return petFriendly; }

    public void setIndoorId(int id)               { this.indoorId         = id; }
    public void setLightRequirement(String light) { this.lightRequirement = light; }
    public void setAirPurifying(boolean ap)       { this.airPurifying     = ap; }
    public void setPetFriendly(boolean pf)        { this.petFriendly      = pf; }

    
}
