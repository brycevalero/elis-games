package com.brycevalero.www.elisgames.utilities;

/**
 * Created by bryce on 12/10/2015.
 */
public class Utilities {

    // Return an integer that ranges from min inclusive to max inclusive.
    public static int rndInt(int min, int max) {
        return (int) (min + Math.random() * (max - min + 1));
    }

    public static double rndDbl(double min, double max) {
        return min + (max - min) * Math.random();
    }

}
