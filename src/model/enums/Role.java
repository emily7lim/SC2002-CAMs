package model.enums;

public enum Role {
    STUDENT("Student"),
    STAFF("Staff");

    private final String roleStr;

    private Role(String roleStr) {
        this.roleStr = roleStr;
    }

    public String getRole() {
        return roleStr;
    }
}
