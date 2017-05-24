package com.calebtravers.models;

/**
 * Created by adminbackup on 5/4/17.
 */
public enum Category {

    PRODUCE ("Produce"),
    MEAT ("Meat"),
    DAIRY ("Dairy"),
    FREEZER ("Refridgerator/Freezer"),
    SPICES ("Spices"),
    BAKING ("Baking"),
    CANNED ("Packaged/Canned"),
    BAKERY ("Bakery");
    ;

    private final String name;

    Category (String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
