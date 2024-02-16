// @author Wes Helms

package com.example.facemakerapplication;

import android.graphics.Canvas;

import java.util.Random;

public class Face {
    // Public Instance Variables
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    // Public Static Final Hair Styles
    public static final int CREW_CUT = 0;
    public static final int BUZZ_CUT = 1;
    public static final int MULLET = 2;
    public static final int MOHAWK = 3;
    public static final int BALD = 4;

    // Default Constructor
    public Face() {
        randomize();
    }
    // Sets all of the instance variables to random valid values
    public void randomize() {
        // Creating a new random object to generate random valid values
        Random rand = new Random();
        // Generating random valid values for all instance variables
        skinColor = rand.nextInt(256);
        eyeColor = rand.nextInt(256);
        hairColor = rand.nextInt(256);
        hairStyle = rand.nextInt(5);
    }
    // onDraw Method
    public void onDraw(Canvas canvas) {

    }
    // Helper methods for onDraw
    // Gets the skinColor for this object
    public int getSkinColor() {
        return this.skinColor;
    }
    // Gets the eyeColor for this object
    public int getEyeColor() {
        return this.eyeColor;
    }
    // Gets the hairColor of this object
    public int getHairColor() {
        return this.hairColor;
    }
    // Gets the hairStyle for this object
    public int getHairStyle() {
        return this.hairStyle;
    }

    // Sets the skinColor for this object
    public void setSkinColor(int newSkinColor) {
        this.skinColor = newSkinColor;
    }
    // Sets the eyeColor for this object
    public void setEyeColor(int newEyeColor) {
        this.eyeColor = newEyeColor;
    }
    // Sets the hairColor of this object
    public void setHairColor(int newHairColor) {
        this.hairColor = newHairColor;
    }
    // Sets the hairStyle for this object
    public void setHairStyle(int newHairStyle) {
        this.hairStyle = newHairStyle;
    }

}
