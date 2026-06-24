/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package javaproject;
import java.io.Serializable;

/**
 *
 * @author DELL
 */
public class HerbPlant extends Plant implements Serializable{



    private int    herbId;
    private String culinaryUse;
    private String medicinalProperty;
    private String harvestTime;

    public HerbPlant() { super(); }

    public HerbPlant(int plantId, String plantName, String scientificName,
                     String description, int adminId,
                     String culinaryUse, String medicinalProperty, String harvestTime) {
        super(plantId, plantName, scientificName, "Herb", description, adminId);
        this.culinaryUse       = culinaryUse;
        this.medicinalProperty = medicinalProperty;
        this.harvestTime       = harvestTime;
    }

    @Override
    public String getPlantCategory() {
        return "Herb";
    }

    @Override
    public String getSpecialCareNote() {
        return "Harvest leaves frequently to prevent flowering and keep flavor strong.";
    }

    @Override
    public String getCategoryDescription() {
        return "Herb plants are valued for their culinary and medicinal properties.";
    }

    public String getUsageInfo() {
        return getPlantName() + " used in: " + culinaryUse
             + ". Medicinal: " + medicinalProperty
             + ". Harvest: " + harvestTime;
    }

    public int    getHerbId()            { return herbId; }
    public String getCulinaryUse()       { return culinaryUse; }
    public String getMedicinalProperty() { return medicinalProperty; }
    public String getHarvestTime()       { return harvestTime; }

    public void setHerbId(int herbId)                { this.herbId            = herbId; }
    public void setCulinaryUse(String culinaryUse)   { this.culinaryUse       = culinaryUse; }
    public void setMedicinalProperty(String medProp) { this.medicinalProperty = medProp; }
    public void setHarvestTime(String harvestTime)   { this.harvestTime       = harvestTime; }
} 
    

