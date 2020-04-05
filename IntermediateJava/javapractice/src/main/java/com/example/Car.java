package com.example;

/**
 * Created by adari on 6/6/2017.
 */

public class Car {

    public String colour = "Red";
    private int nNumberOfSeats = 5;

    private String mInterior;

    public Car() {
        mInterior = "Leather";
    }

    public Car(String chosenColour, String chosenInterior){
        colour = chosenColour;
        mInterior = chosenInterior;
    }

    public int getnNumberOfSeats() {
        return nNumberOfSeats;
    }

    public String getInterior() {
        return mInterior;
    }

    public void drive() {
        System.out.println("Car is Moving");
    }
}
