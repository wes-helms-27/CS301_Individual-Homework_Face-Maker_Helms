package com.example.facemakerapplication;

/*
  @Author Wes Helms
 */

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import com.example.facemakerapplication.R;

import java.text.ParseException;
import java.util.Random;

public class Face extends SurfaceView implements SeekBar.OnSeekBarChangeListener,
        RadioGroup.OnCheckedChangeListener, View.OnClickListener,
        AdapterView.OnItemSelectedListener {

    //instance variable for what color is being edited 1-3 for skin, eyes, hair
    public int facialFeature;
    public static int SKIN = 1;
    public static int EYES = 2;
    public static int HAIR = 3;


    //instance variable for skin
    Paint skinColor = new Paint();
    public int skinRedColor;
    public int skinGreenColor;
    public int skinBlueColor;

    //instance variables for eyes
    Paint eyeColor = new Paint();
    public int eyeRedColor;
    public int eyeGreenColor;
    public int eyeBlueColor;

    //instance variables for hair
    Paint hairColor = new Paint();
    public int hairRedColor;
    public int hairGreenColor;
    public int hairBlueColor;
    //instance variable for what hairstyle is chosen 1-3 for crew cut, side burns, mohawk, and bald
    public int hairStyle = CREW_CUT; // Default hair style
    public static final int CREW_CUT = 0;
    public static final int SIDE_BURNS = 1;
    public static final int MOHAWK = 2;
    public static final int BALD = 3;

    public Face(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.randomize();
        setWillNotDraw(false);
    }

    //randomizes instance variables to make a random face
    public void randomize() {
        Random rand = new Random();
        //randomizes skin
        this.skinRedColor = rand.nextInt(255);
        this.skinGreenColor = rand.nextInt(255);
        this.skinBlueColor = rand.nextInt(255);
        int skinHex = getColor(skinRedColor, skinGreenColor, skinBlueColor);
        this.skinColor.setColor(skinHex);
        this.skinColor.setStyle(Paint.Style.FILL);

        //randomizes eyes
        this.eyeRedColor = rand.nextInt(255);
        this.eyeGreenColor = rand.nextInt(255);
        this.eyeBlueColor = rand.nextInt(255);
        int eyeHex = getColor(eyeRedColor, eyeGreenColor, eyeBlueColor);
        this.eyeColor.setColor(eyeHex);

        //randomizes hair
        this.hairRedColor = rand.nextInt(255);
        this.hairGreenColor = rand.nextInt(255);
        this.hairBlueColor = rand.nextInt(255);
        int hairHex = getColor(hairRedColor, hairGreenColor, hairBlueColor);
        this.hairColor.setColor(hairHex);
        this.hairStyle = rand.nextInt(4);

        this.invalidate();
    }

    //returns the hex of an rgb
    public int getColor(int r, int g, int b) {
        String rgb = String.format("%02x%02x%02x%02x", 255, r, g, b);
        return (int) Long.parseLong(rgb, 16);
    }

    //draws a face using instance variables
    public void onDraw(Canvas c) {
        drawHead(c);
        drawHair(c);
        drawEyes(c);
        invalidate();
    }

    //helper methods for onDraw method
    //draws specified hairStyle in specified HairColor
    public void drawHair(Canvas c) {
        // Calculate face dimensions
        int faceWidth = c.getWidth();
        int faceHeight = c.getHeight();

        int hairTop = faceHeight / 5; // Hair starts at 1/5th of face height
        int hairBottom = faceHeight * 3 / 10; // Hair ends at 3/10th of face height

        // Draw different hairstyles based on the current hair style
        switch (hairStyle) {
            case CREW_CUT:
                // Crew cut
                c.drawRect(0, 0, faceWidth, hairBottom, hairColor);
                break;
            case SIDE_BURNS:
                // Crew cut
                c.drawRect(0, 0, faceWidth, hairBottom, hairColor);
                c.drawRect(0, 0, (float) (faceWidth / 12), (float) faceHeight / 2, hairColor);
                c.drawRect((float) ((11 * faceWidth) / 12), 0, faceWidth, (float) faceHeight / 2, hairColor);
                break;
            case MOHAWK:
                // Mohawk
                c.drawRect((float) faceWidth / 4, 0, (float) (faceWidth * 3) / 4, hairBottom, hairColor);
                break;
            case BALD:
                // Bald
                c.drawRect(0, 0, faceWidth, hairBottom, skinColor);
                break;
        }
    }

    //draws the eyes in specified color in set locations
    public void drawEyes(Canvas c) {
        // Calculate face dimensions
        int faceWidth = c.getWidth();
        int faceHeight = c.getHeight();

        int eyeRadius = faceWidth / 20; // Radius of eyes
        int eyeSpacing = faceWidth / 5; // Spacing between eyes
        int eyeY = faceHeight * 3 / 7; // Height of eyes

        // Left eye
        c.drawCircle((float) faceWidth / 3 - eyeSpacing, eyeY, eyeRadius, eyeColor);
        // Right eye
        c.drawCircle((float) (faceWidth * 2) / 3 + eyeSpacing, eyeY, eyeRadius, eyeColor);

        invalidate();
    }

    //draws the head in specified color
    public void drawHead(Canvas c) {
        // Calculate face dimensions
        int faceWidth = c.getWidth();
        int faceHeight = c.getHeight();

        // Draw skin
        c.drawRect(0, 0, faceWidth, faceHeight, skinColor);

        invalidate();
    }


    /**
     * checks if the seekbar being messed with is one of the above, and
     * changes the red green and blue values defined above to match the bar's progress
     */
    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        // For changing hair color
        if (facialFeature == HAIR) {
            if (seekBar.getId() == R.id.redSeekBar) {
                this.hairRedColor = progress;
            } else if (seekBar.getId() == R.id.greenSeekBar) {
                this.hairGreenColor = progress;
            } else {
                this.hairBlueColor = progress;
            }
            // update RGB values
            this.hairColor.setColor(getColor(this.hairRedColor, this.hairGreenColor, this.hairBlueColor));
        } else if (facialFeature == EYES) { // For changing eye color
            if (seekBar.getId() == R.id.redSeekBar) {
                this.eyeRedColor = progress;
            } else if (seekBar.getId() == R.id.greenSeekBar) {
                this.eyeGreenColor = progress;
            } else {
                this.eyeBlueColor = progress;
            }
            // update RGB values
            this.eyeColor.setColor(getColor(this.eyeRedColor, this.eyeGreenColor, this.eyeBlueColor));
        } else if (facialFeature == SKIN) { // For changing skin color
            if (seekBar.getId() == R.id.redSeekBar) {
                this.skinRedColor = progress;
            } else if (seekBar.getId() == R.id.greenSeekBar) {
                this.skinGreenColor = progress;
            } else {
                this.skinBlueColor = progress;
            }
            // update RGB values
            this.skinColor.setColor(getColor(this.skinRedColor, this.skinGreenColor, this.skinBlueColor));
        }
        invalidate();
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        //not used
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        //not used
    }

    // sets what color is being changed via an integer corresponding to the choices
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        MainActivity myApp = (MainActivity) getContext();
        if (checkedId == R.id.skinRadioButton) {
            this.facialFeature = SKIN;
            //sets the seekbars to the skin values
            SeekBar seekRed = myApp.findViewById(R.id.redSeekBar);
            seekRed.setProgress(skinRedColor);
            SeekBar seekGreen = myApp.findViewById(R.id.greenSeekBar);
            seekGreen.setProgress(skinGreenColor);
            SeekBar seekBlue = myApp.findViewById(R.id.blueSeekBar);
            seekBlue.setProgress(skinBlueColor);
        } else if (checkedId == R.id.hairRadioButton) {
            this.facialFeature = HAIR;
            //sets seekbars to hair values
            SeekBar seekRed = myApp.findViewById(R.id.redSeekBar);
            seekRed.setProgress(hairRedColor);
            SeekBar seekGreen = myApp.findViewById(R.id.greenSeekBar);
            seekGreen.setProgress(hairGreenColor);
            SeekBar seekBlue = myApp.findViewById(R.id.blueSeekBar);
            seekBlue.setProgress(hairBlueColor);
        } else if (checkedId == R.id.eyesRadioButton) {
            this.facialFeature = EYES;
            //sets seekbars to eye values
            SeekBar seekRed = myApp.findViewById(R.id.redSeekBar);
            seekRed.setProgress(eyeRedColor);
            SeekBar seekGreen = myApp.findViewById(R.id.greenSeekBar);
            seekGreen.setProgress(eyeGreenColor);
            SeekBar seekBlue = myApp.findViewById(R.id.blueSeekBar);
            seekBlue.setProgress(eyeBlueColor);
        }
    }

    //one-line method for the randomizer button, which uses a previously made method
    @Override
    public void onClick(View v) {
        randomize();
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        hairStyle = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //not used
    }

}
