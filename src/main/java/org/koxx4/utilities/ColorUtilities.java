package org.koxx4.utilities;

import java.awt.*;
import java.util.Random;

public final class ColorUtilities {

    private static Random random = new Random();
    private static int nextColorId = 0;

    public static String rgbToHexString(int r, int g, int b){
        return String.format("#%02x%02x%02x", r, g, b);
    }

    public static String colorToHexString(Color color){
        return rgbToHexString(color.getRed(), color.getGreen(), color.getBlue());
    }

    public static Color getNextRandomColorFrom(Color thisColor){
        return new Color(
                (thisColor.getRed() + random.nextInt(200)) % 255,
                (thisColor.getGreen() + random.nextInt(200)) % 255,
                (thisColor.getBlue() + random.nextInt(200)) % 255);
    }

    public static Color getNextColor(){
        //https://stackoverflow.com/questions/1168260/algorithm-for-generating-unique-colors
        double phi = (1 + Math.sqrt(5))/2;
        int[] newRGB = new int[3];

        for (int i = 0; i < 3 ; i++){
            double n = nextColorId * phi - Math.floor(nextColorId * phi);
            nextColorId++;
            newRGB[i] = (int) Math.floor(255 * n);
        }
        return new Color(newRGB[0], newRGB[1], newRGB[2]);
    }

    public static Color getNextColorFrom(Color source, int rProgression, int gProgression, int bProgression){
        return new Color((source.getRed() + rProgression) % 255,
                (source.getGreen() + gProgression) % 255,
                (source.getBlue() + bProgression) % 255);
    }

    public static void resetColorProgression(){
        nextColorId = 0;
    }

}
