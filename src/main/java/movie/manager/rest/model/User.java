package movie.manager.rest.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
    private String username;
    private String password;
    private boolean isConnected;
    private Role role;
    private String cookie;
    
    public User() {
    	
    }
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.isConnected = false;
        this.role = Role.MEMBER; // Par défaut, le rôle est membre
    }

    public void connect() {
        isConnected = true;
    }

    public void disconnect() {
        isConnected = false;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
    
    public String getUsername() {
    	return this.username;
    }
    public void setUsername(String username) {
    	this.username = username;
    }
    
    public String getCookie() {
    	return cookie;
    }
    public void setCookie(String cookie) {
    	this.cookie = cookie;
    }
    
    public String getPassword() {
    	return password;
    }

    // Enumération pour les rôles des utilisateurs
    public enum Role {
        STAFF,
        PROVIDER,
        MEMBER
    }

}

