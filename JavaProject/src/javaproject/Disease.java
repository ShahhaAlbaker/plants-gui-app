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

public class Disease implements Serializable {
    


    private int    diseaseId;
    private String diseaseName;
    private String symptoms;
    private String treatment;
    private int    plantId;

    public Disease() {}

    public Disease(int diseaseId, String diseaseName, String symptoms,
                   String treatment, int plantId) {
        this.diseaseId   = diseaseId;
        this.diseaseName = diseaseName;
        this.symptoms    = symptoms;
        this.treatment   = treatment;
        this.plantId     = plantId;
    }

    public String getDiseaseInfo() {
        return "Disease: "     + diseaseName
             + "\nSymptoms: "  + symptoms
             + "\nTreatment: " + treatment;
    }

    @Override
    public String toString() {
        return diseaseName;
    }

    public int    getDiseaseId()   { return diseaseId; }
    public String getDiseaseName() { return diseaseName; }
    public String getSymptoms()    { return symptoms; }
    public String getTreatment()   { return treatment; }
    public int    getPlantId()     { return plantId; }

    public void setDiseaseId(int diseaseId)        { this.diseaseId   = diseaseId; }
    public void setDiseaseName(String diseaseName) { this.diseaseName = diseaseName; }
    public void setSymptoms(String symptoms)       { this.symptoms    = symptoms; }
    public void setTreatment(String treatment)     { this.treatment   = treatment; }
    public void setPlantId(int plantId)            { this.plantId     = plantId; }

    
}
