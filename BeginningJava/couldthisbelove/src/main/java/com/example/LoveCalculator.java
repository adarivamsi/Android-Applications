package com.example;

import java.util.Random;

public class LoveCalculator {

    public static void main(String[] args) {

        System.out.println("Your Love Score is: "+ifYouHadMyLove("Vamsi", "Anushka  "));

    }

    public static int ifYouHadMyLove(String yourName, String theirName){

        Random randObject = new Random();
        int loveScore = randObject.nextInt(101);

        if (loveScore > 80) {

            System.out.println(yourName + " and " +theirName+ " love each other like Romeo and Juliet");

        }

        else if (loveScore > 40) {

            System.out.println(yourName + " and " +theirName+ " May there may happen miracle to be in love");
        }

        else  {
            System.out.println("No Love Possible, You'll be forever friends");
        }

        return loveScore;

    }

}
