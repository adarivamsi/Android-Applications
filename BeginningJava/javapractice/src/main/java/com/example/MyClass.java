package com.example;

public class MyClass {

    public static void main(String[] args) {

        int change = getMilk(2,100);
        System.out.println("Hello Vamsi, your change is " + change + " rupees");

    }

//    public static void getMilk() {
//
//        System.out.println("Open Door");
//        System.out.println("Walk to Store");
//        System.out.println("Buy Milk on the Ground Floor");
//        System.out.println("Return home with milk galore");
//
//    }

//        public static void getMilk(int nrOfCartonsToBuy) {
//
//        int priceToPay = nrOfCartonsToBuy*15;
//
//        System.out.println("Open Door");
//        System.out.println("Walk to Store");
//        System.out.println("Buy " +nrOfCartonsToBuy+ " Milk on the Ground Floor");
//        System.out.println("Pay " +priceToPay+ " Rupees, Not more than that");
//        System.out.println("Return home with milk galore");
//
//    }
//
//}

    public static int getMilk(int nrOfCartonsToBuy, int  startingAmount) {

        int priceToPay = nrOfCartonsToBuy*15;

        System.out.println("Open Door");
        System.out.println("Walk to Store");
        System.out.println("Buy " +nrOfCartonsToBuy+ " Milk on the Ground Floor");
        System.out.println("Pay " +priceToPay+ " Rupees, Not more than that");
        System.out.println("Return home with milk galore");

        return startingAmount - priceToPay;

    }

}