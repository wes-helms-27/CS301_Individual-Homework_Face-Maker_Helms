package com.example.facemakerapplication;

/*
  @Author Wes Helms
 */

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initializes spinner to populate
        Spinner spinner = (Spinner) findViewById(R.id.hairStyleSpinner);

        // creates a charsequence using a string array resource
        ArrayAdapter<CharSequence> setUp = ArrayAdapter.createFromResource(
                this, R.array.hair_styles, android.R.layout.simple_spinner_item);

        // sets how the arrayadapter will display
        setUp.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // puts everything in the spinner
        spinner.setAdapter(setUp);

        Face faceView = findViewById(R.id.faceView);

        SeekBar redSeekBar = findViewById(R.id.redSeekBar);
        SeekBar greenSeekBar = findViewById(R.id.greenSeekBar);
        SeekBar blueSeekBar = findViewById(R.id.blueSeekBar);


        Button randomButton = findViewById(R.id.randomButton);

        RadioGroup facialFeatures = findViewById(R.id.facialFeaturesRadioGroup);

        // specifies which facial feature the user is trying to change
        facialFeatures.setOnCheckedChangeListener(faceView);

        // sets the color selectors to choose RGB values
        redSeekBar.setOnSeekBarChangeListener(faceView);
        greenSeekBar.setOnSeekBarChangeListener(faceView);
        blueSeekBar.setOnSeekBarChangeListener(faceView);


        // sets randomizer button
        randomButton.setOnClickListener(faceView);

        // sets spinner to receive input from the spinner for hair style
        spinner.setOnItemSelectedListener(faceView);

    }

}