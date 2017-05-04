package com.calebtravers.models;

/**
 * Created by adminbackup on 5/4/17.
 */
public enum UnitOfMeasure {

    CP ("Cup"),
    TBS ("Tablespoon"),
    TSP ("Teaspoon"),
    LB ("Pound"),
    OZ ("Ounce"),
    FLOZ ("Fluid Ounce"),
    PINCH ("Pinch"),
    WHOLE ("Whole");

    private String name;

    UnitOfMeasure (String name){
        this.name = name;
    }
}
