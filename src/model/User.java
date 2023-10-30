package model;

import java.io.Serializable;

import model.enums.Faculty;
import model.enums.Role;

public class User implements Serializable {
    protected static final long serialVersionUID = 1L;
    protected String userId;
    protected String name;
    protected String password;
    protected Faculty faculty;
    protected Role role;

    public User(String userId, String name, String password, Faculty faculty, Role role) {
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.faculty = faculty;
        this.role = role;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
