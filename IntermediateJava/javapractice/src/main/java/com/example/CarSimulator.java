package com.example;

public class CarSimulator {

    public static void main(String[] args) {

        System.out.println("Launching Car Simulator.....");

        Car myBenz = new Car("White","Lavish");
        Car myHonda = new Car();

        myBenz.drive();

    }

}
