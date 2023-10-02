package firegruppen.security.entity;

public enum Role {

    USER,

    ADMIN;

    public static Role fromString(String roleString) {
        return Role.valueOf(roleString.trim().toUpperCase());
    }
}