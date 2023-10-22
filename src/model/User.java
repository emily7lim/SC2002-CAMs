package model;

import java.io.Serializable;

import model.enums.Faculty;
import model.enums.Role;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private String userId;
    private String password;
    private Faculty faculty;
    private Role role;

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
