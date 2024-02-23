// @author Wes Helms

package com.example.facemakerapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

import java.util.Random;

public class Face extends SurfaceView implements SurfaceHolder.Callback {
    // Public Instance Variables
    public int skinColor;
    public int eyeColor;
    public int hairColor;
    public int hairStyle;

    // Hair style constants
    public static final int CREW_CUT = 0;
    public static final int MOHAWK_SHORT = 1;
    public static final int MOHAWK_TALL = 2;
    public static int currentHairStyle = CREW_CUT; // Default hair style


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
    // Updates all colors

    // Sets the hairStyle for this object
    public void setHairStyle(int newHairStyle) {
        this.hairStyle = newHairStyle;
    }

    public void setCurrentHairStyle(int hairStyle) {
        this.currentHairStyle = hairStyle;
        drawFace();
    }

    private SurfaceHolder surfaceHolder;
    private Paint paint;

    // Define face components
    private int faceWidth, faceHeight;
    private int eyeRadius;

    public Face(Context context) {
        super(context);
        init();
    }

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Face(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        surfaceHolder = getHolder();
        surfaceHolder.addCallback(this);
        paint = new Paint();
        paint.setAntiAlias(true);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        drawFace();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        drawFace();
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Surface destroyed not needed
    }

    public void updateColor(int hairColor, int eyesColor, int skinColor) {
        this.hairColor = hairColor;
        this.eyeColor = eyesColor;
        this.skinColor = skinColor;
        drawFace();
    }

    private void drawFace() {
        if (surfaceHolder.getSurface().isValid()) {
            Canvas canvas = surfaceHolder.lockCanvas();
            canvas.drawColor(Color.WHITE); // Background color

            // Calculate face dimensions
            faceWidth = canvas.getWidth();
            faceHeight = canvas.getHeight();

            // Draw skin
            paint.setColor(skinColor);
            canvas.drawRect(0, 0, faceWidth, faceHeight, paint);

            // Draw hair
            if (hairColor != Color.TRANSPARENT) {
                paint.setColor(hairColor);
                int hairTop = faceHeight / 5; // Hair starts at 1/5th of face height
                int hairBottom = faceHeight * 3 / 10; // Hair ends at 3/10th of face height

                // Draw different hairstyles based on the current hair style
                switch (currentHairStyle) {
                    case CREW_CUT:
                        // Crew cut
                        canvas.drawRect(0, 0, faceWidth, hairBottom, paint);
                        break;
                    case MOHAWK_SHORT:
                        // Mohawk (short)
                        canvas.drawRect(faceWidth / 4, 0, faceWidth * 3 / 4, hairBottom, paint);
                        break;
                    case MOHAWK_TALL:
                        // Mohawk (tall)
                        canvas.drawRect(faceWidth / 4, 0, faceWidth * 3 / 4, hairBottom * 2 / 3, paint);
                        break;
                }
            }

            // Draw eyes
            paint.setColor(eyeColor);
            eyeRadius = faceWidth / 20; // Radius of eyes
            int eyeSpacing = faceWidth / 5; // Spacing between eyes
            int eyeY = faceHeight * 3 / 7; // Height of eyes

            // Left eye
            canvas.drawCircle(faceWidth / 3 - eyeSpacing, eyeY, eyeRadius, paint);
            // Right eye
            canvas.drawCircle(faceWidth * 2 / 3 + eyeSpacing, eyeY, eyeRadius, paint);

            surfaceHolder.unlockCanvasAndPost(canvas);
        }
    }
}
