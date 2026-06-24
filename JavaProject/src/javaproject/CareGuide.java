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
public class CareGuide implements Serializable {
    private int    careId;
    private String wateringFrequency;
    private String sunlightNeeds;
    private String soilType;
    private String temperature;
    private int    plantId;

    public CareGuide() {}

    public CareGuide(int careId, String wateringFrequency, String sunlightNeeds,
                     String soilType, String temperature, int plantId) {
        this.careId            = careId;
        this.wateringFrequency = wateringFrequency;
        this.sunlightNeeds     = sunlightNeeds;
        this.soilType          = soilType;
        this.temperature       = temperature;
        this.plantId           = plantId;
    }

    public String getCareSummary() {
        return "Watering: "    + wateringFrequency
             + " | Sunlight: " + sunlightNeeds
             + " | Soil: "     + soilType
             + " | Temp: "     + temperature;
    }

    @Override
    public String toString() {
        return "CareGuide { plantId=" + plantId + ", watering=" + wateringFrequency + " }";
    }

    public int    getCareId()            { return careId; }
    public String getWateringFrequency() { return wateringFrequency; }
    public String getSunlightNeeds()     { return sunlightNeeds; }
    public String getSoilType()          { return soilType; }
    public String getTemperature()       { return temperature; }
    public int    getPlantId()           { return plantId; }

    public void setCareId(int careId)           { this.careId            = careId; }
    public void setWateringFrequency(String wf) { this.wateringFrequency = wf; }
    public void setSunlightNeeds(String sn)     { this.sunlightNeeds     = sn; }
    public void setSoilType(String st)          { this.soilType          = st; }
    public void setTemperature(String temp)     { this.temperature       = temp; }
    public void setPlantId(int plantId)         { this.plantId           = plantId; }

    
}
