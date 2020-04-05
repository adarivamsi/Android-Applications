package com.example;

public class BMICalculator {

    public static void main(String[] args) {

        System.out.println("Your BMI Value is: " +Calculator(75,160));

    }

    public static double Calculator(double weight, double height){

        double BMICalc = (100*100*weight)/(height*height);
//        double BMICalc = ((weight/(height * height))* 10000);
//        double BMICalc = ((weight * 703)/(height * height));

        if (BMICalc > 25){
            System.out.println("You are Overweight");
        }
        else if (BMICalc > 18.5 && BMICalc < 25 ){
            System.out.println("Your weight is Normal");
        }
        else {
            System.out.println("You are Underweight");
        }
        return BMICalc;
    }

}
