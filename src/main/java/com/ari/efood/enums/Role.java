package com.ari.efood.enums;

public enum Role {
    ADMIN("admin"),
    CUSTOMER("customer");

    private final String TYPE;

    Role(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }
}
