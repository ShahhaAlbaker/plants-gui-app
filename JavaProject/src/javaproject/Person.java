
package javaproject;


import java.io.Serializable;

public abstract class Person implements Serializable{



    private int    id;
    private String username;
    private String email;
    private String password;

    public Person() {}

    public Person(int id, String username, String email, String password) {
        this.id       = id;
        this.username = username;
        this.email    = email;
        this.password = password;
    }
 //abstract method
    public abstract String getRole();
    public abstract String getPermissions();
//
    public boolean validateCredentials(String inputEmail, String inputPassword) {
        return this.email.equalsIgnoreCase(inputEmail.trim())
            && this.password.equals(inputPassword);
    }

    public String getDisplayInfo() {
        return "ID: " + id + " | Username: " + username + " | Role: " + getRole();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + " - " + username;
    }

    public int    getId()       { return id; }
    public String getUsername() { return username; }
    public String getEmail()    { return email; }
    public String getPassword() { return password; }

    public void setId(int id)         { this.id       = id; }
    public void setUsername(String u) { this.username = u; }
    public void setEmail(String e)    { this.email    = e; }
    public void setPassword(String p) { this.password = p; }

    
}
