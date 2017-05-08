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
    FLZ ("Fluid Ounce"),
    PCH ("Pinch"),
    WH ("Whole"),
    NA ("None");

    private String name;

    UnitOfMeasure (String name){
        this.name = name;
    }
}
