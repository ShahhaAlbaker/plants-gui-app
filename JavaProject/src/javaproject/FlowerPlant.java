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

public class FlowerPlant extends Plant implements Serializable {

    private int flowerId;
    private String flowerColor;
    private String bloomSeason;
    private boolean fragrant;

    public FlowerPlant() {
        super();
    }

    public FlowerPlant(int plantId, String plantName, String scientificName,
            String description, int adminId,
            String flowerColor, String bloomSeason, boolean fragrant) {
        super(plantId, plantName, scientificName, "Flower", description, adminId);
        this.flowerColor = flowerColor;
        this.bloomSeason = bloomSeason;
        this.fragrant = fragrant;
    }

    @Override
    public String getPlantCategory() {
        return "Flower";
    }

    @Override
    public String getSpecialCareNote() {
        return "Prune dead flowers regularly to encourage new blooms.";
    }

    @Override
    public String getCategoryDescription() {
        return "Flowering plants are grown for their beautiful blossoms.";
    }

    public String getBloomInfo() {
        return getPlantName() + " blooms in " + bloomSeason
                + " with " + flowerColor + " flowers"
                + (fragrant ? " and is fragrant." : ".");
    }

    public int getFlowerId() {
        return flowerId;
    }

    public String getFlowerColor() {
        return flowerColor;
    }

    public String getBloomSeason() {
        return bloomSeason;
    }

    public boolean isFragrant() {
        return fragrant;
    }

    public void setFlowerId(int flowerId) {
        this.flowerId = flowerId;
    }

    public void setFlowerColor(String color) {
        this.flowerColor = color;
    }

    public void setBloomSeason(String season) {
        this.bloomSeason = season;
    }

    public void setFragrant(boolean fragrant) {
        this.fragrant = fragrant;
    }

}
