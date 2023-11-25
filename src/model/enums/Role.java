package model.enums;

/**
 * Role of a User
 * 
 * @author Chloie
 * @version 1.1.3
 * @since 2023-10-23
 */
public enum Role {
    STUDENT("Student"),
    STAFF("Staff"),
    COMMITTEE("Camp Committee");

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
