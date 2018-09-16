package com.nickhe.reciperescue;

public enum Type {
    ASIAN("Asian"), WESTERN("Western"), MEXICAN("Mexican"), INDIAN("Indian");
    private String typeString;

    private Type(String typeString){
        this.typeString = typeString;
    }
}
