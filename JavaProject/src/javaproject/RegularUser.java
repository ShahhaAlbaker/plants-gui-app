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
import java.util.ArrayList;
import java.util.List;
public class RegularUser extends Person implements Serializable {


    private String       role;
    private List<Integer> viewedPlantIds;

    public RegularUser() {
        super();
        this.role           = "user";
        this.viewedPlantIds = new ArrayList<>();
    }

    public RegularUser(int id, String username, String email, String password, String role) {
        super(id, username, email, password);
        this.role           = role;
        this.viewedPlantIds = new ArrayList<>();
    }

    @Override
    public String getRole() {
        return "User";
    }

    @Override
    public String getPermissions() {
        return "View plant information, search plants, read care guides and disease info.";
    }

    public void recordPlantView(int plantId) {
        if (!viewedPlantIds.contains(plantId)) {
            viewedPlantIds.add(plantId);
        }
    }

    public int getViewCount() {
        return viewedPlantIds.size();
    }

    public boolean canSearch() {
        return true;
    }

    public String        getUserRole()       { return role; }
    public List<Integer> getViewedPlantIds() { return viewedPlantIds; }
    public void          setUserRole(String role) { this.role = role; }

    
}
