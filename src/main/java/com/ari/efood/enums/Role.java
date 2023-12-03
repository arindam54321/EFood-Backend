package com.ari.efood.enums;

public enum Role {
    CUSTOMER("customer");

    String role;

    Role(String role) {
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}
