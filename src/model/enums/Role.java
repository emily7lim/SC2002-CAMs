package model.enums;

public enum Role {
    STUDENT ("Student"),
    STAFF ("Staff"),
    COMMITTEE ("Camp Committee");

    private final String roleStr;

    private Role(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getRole() {
        return roleStr;
    }
}
