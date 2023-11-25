package model;

import java.io.Serializable;

import model.enums.Faculty;
import model.enums.Role;

/**
 * User in the system
 * 
 * @author Chloie
 * @version 1.1.4
 * @since 2023-10-23
 */
public class User implements Serializable {
    protected static final long serialVersionUID = 1L; // Serializable Unique Identifier
    protected String userId; // ID of the User
    protected String name; // Name of the User
    protected String password; // Password of the User
    protected Faculty faculty; // Faculty of the User
    protected Role role; // Role of the User
    protected int loginCount; // Number of times User has logged in

    /**
     * Constructs and initializes a User object with User ID, Name, Password,
     * Faculty and Role
     * 
     * @param userId   The ID of the User
     * @param name     The Name of the User
     * @param password The Password of the User
     * @param faculty  The Faculty of the User
     * @param role     The Role of the User
     */
    public User(String userId, String name, String password, Faculty faculty, Role role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
        this.role = role;
        this.loginCount = 0;
    }

    /**
     * Constructs and initializes a User object with User ID, Name, Password,
     * Faculty, Role and Login Count
     * 
     * @param userId     The ID of the User
     * @param name       The Name of the User
     * @param password   The Password of the User
     * @param faculty    The Faculty of the User
     * @param role       The Role of the User
     * @param loginCount The number of times the User has logged in
     */
    public User(String userId, String name, String password, Faculty faculty, Role role, int loginCount) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
        this.role = role;
        this.loginCount = 0;
    }

    /**
     * Gets the ID of the User
     * 
     * @return String The ID of the User
     */
    public String getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the User
     * 
     * @param userId The ID of the User
     */
    public void setUserId(String userId) {
        this.userId = userId;
    }

    /**
     * Gets the Name of the User
     * 
     * @return String The Name of the User
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the Name of the User
     * 
     * @param name The Name of the User
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets the Password of the User
     * 
     * @param password The Password of the User
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the Faculty of the User
     * 
     * @return Faculty The Faculty of the User
     */
    public Faculty getFaculty() {
        return faculty;
    }

    /**
     * Sets the Faculty of the User
     * 
     * @param faculty The Faculty of the User
     */
    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    /**
     * Gets the Role of the User
     * 
     * @return Role The Role of the User
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets the Role of the User
     * 
     * @param role The Role of the User
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets the number of times the User has logged in
     * 
     * @return int The number of times the User has logged in
     */
    public int getLoginCount() {
        return loginCount;
    }

    /**
     * Sets the number of times the User has logged in
     * 
     * @param loginCount The number of times the User has logged in
     */
    public void setLoginCount(int loginCount) {
        this.loginCount = loginCount;
    }

    /**
     * Validates the password against the User Password
     * 
     * @param password The password entered by User
     * @return boolean Whether the password matches the User Password
     */
    public boolean validatePassword(String password) {
        return this.password.equals(password);
    }

    /**
     * Increment the number of times the User has logged in
     */
    public void login() {
        this.loginCount += 1;
    }
}
