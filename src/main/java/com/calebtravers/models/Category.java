package com.calebtravers.models;

/**
 * Created by adminbackup on 5/4/17.
 */
public enum Category {

    MEAT ("Meat"),
    DAIRY ("Dairy"),
    PRODUCE ("Produce"),
    FREEZER ("Refridgerator/Freezer"),
    BAKERY ("Bakery"),
    BAKING ("Baking"),
    CANNED ("Packaged/Canned");

    private final String name;

    Category (String name){
        this.name = name;
    }

}
