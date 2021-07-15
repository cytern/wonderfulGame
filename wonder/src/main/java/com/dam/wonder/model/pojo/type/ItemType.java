package com.dam.wonder.model.pojo.type;

public enum ItemType {
    /**
     * 深水
     */
    DEEP_WATER(100,"water","deepWater"),
    /**
     * 农田土 长草的
     */
    GRASS_FARMLAND(110,"farmland","grassLand"),

    BOOM_ANIMATION(201,"animation","boom"),

    USER_PLANE(301,"user","plane")
    ;
    private final Integer code;
    private final String type;
    private final String name;

    ItemType(Integer code, String type, String name) {
        this.code = code;
        this.type = type;
        this.name = name;
    }
}
