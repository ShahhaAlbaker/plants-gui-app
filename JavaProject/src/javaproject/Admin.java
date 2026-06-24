
package javaproject;

import java.io.Serializable;

public class Admin extends Person implements Serializable {

    private int adminId;

    public Admin() { super(); }

    public Admin(int id, String username, String email, String password) {
        super(id, username, email, password);
        this.adminId = id;
    }

    @Override
    public String getRole() {
        return "Admin";
    }

    @Override
    public String getPermissions() {
        return "Add, Update, Delete plants and manage all system data.";
    }

    public boolean hasFullAccess() {
        return true;
    }

    public void logAction(String action) {
        System.out.println("[ADMIN LOG] " + getUsername() + " performed: " + action);
    }

    public int getAdminId()        { return adminId; }
    public void setAdminId(int id) { this.adminId = id; }

}
