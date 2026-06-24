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

public class SucculentPlant extends Plant implements Serializable {


    private int    succulentId;
    private String waterStorageType;
    private int    droughtToleranceDays;// عدد ايام تحمل الجفاف
    private String nativeRegion;//المنطقه الاصليه

    public SucculentPlant() { super(); }

    public SucculentPlant(int plantId, String plantName, String scientificName,
                          String description, int adminId,
                          String waterStorageType, int droughtToleranceDays, String nativeRegion) {
        super(plantId, plantName, scientificName, "Succulent", description, adminId);
        this.waterStorageType     = waterStorageType;
        this.droughtToleranceDays = droughtToleranceDays;
        this.nativeRegion         = nativeRegion;
    }

    @Override
    public String getPlantCategory() {
        return "Succulent";
    }

    @Override
    public String getSpecialCareNote() {
        return "Water only when soil is completely dry. Overwatering causes death.";
    }

    @Override
    public String getCategoryDescription() {
        return "Succulents store water in their thick leaves and are drought-tolerant.";
    }

    public String getDroughtInfo() {
        return getPlantName() + " survives up to " + droughtToleranceDays + " days without water.";
    }

    public int    getSucculentId()          { return succulentId; }
    public String getWaterStorageType()     { return waterStorageType; }
    public int    getDroughtToleranceDays() { return droughtToleranceDays; }
    public String getNativeRegion()         { return nativeRegion; }

    public void setSucculentId(int id)            { this.succulentId          = id; }
    public void setWaterStorageType(String wst)   { this.waterStorageType     = wst; }
    public void setDroughtToleranceDays(int days) { this.droughtToleranceDays = days; }
    public void setNativeRegion(String region)    { this.nativeRegion         = region; }

    
}
