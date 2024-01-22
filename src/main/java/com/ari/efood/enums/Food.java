package com.ari.efood.enums;

public enum Food {
    PIZZA("pizza"),
    BURGER("burger"),
    BIRYANI("biryani"),
    CHINESE("chinese"),
    ICECREAM("icecream"),
    MOMOS("momos");

    private final String TYPE;

    Food(String TYPE) {
        this.TYPE = TYPE;
    }

    public String getTYPE() {
        return TYPE;
    }
}
