package model.enums;

/**
 * Role of a User
 * 
 * @author Chloie
 * @version 1.1.3
 * @since 2023-10-23
 */
public enum Role {
    /** Student Role */
    STUDENT("Student"),
    /** Staff Role */
    STAFF("Staff"),
    /** Camp Committee Role */
    COMMITTEE("Camp Committee");

    /** The display name of the User Role */
    private final String roleStr;

    /**
     * Constructor of the Role Enum
     * 
     * @param roleStr The Role of a User
     */
    private Role(String roleStr) {
        this.roleStr = roleStr;
    }

    /**
     * Gets the display name of the User Role
     * 
     * @return The display name of the User Role
     */
    public String getRole() {
        return roleStr;
    }
}
