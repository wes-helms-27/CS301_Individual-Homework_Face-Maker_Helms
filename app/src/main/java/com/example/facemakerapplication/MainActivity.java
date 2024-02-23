// @author Wes Helms

package com.example.facemakerapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
    private Spinner spinner;
    private RadioButton hairRadioButton, eyesRadioButton, skinRadioButton;
    private SeekBar redSeekBar, greenSeekBar, blueSeekBar;
    private Button randomFaceButton;
    private Face faceView;

    // Initialize color variables
    private int hairColor = Color.BLACK;
    private int eyesColor = Color.BLACK;
    private int skinColor = Color.BLACK;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        hairRadioButton = findViewById(R.id.hairRadioButton);
        eyesRadioButton = findViewById(R.id.eyesRadioButton);
        skinRadioButton = findViewById(R.id.skinRadioButton);
        redSeekBar = findViewById(R.id.redSeekBar);
        greenSeekBar = findViewById(R.id.greenSeekBar);
        blueSeekBar = findViewById(R.id.blueSeekBar);
        randomFaceButton = findViewById(R.id.randomButton);
        faceView = findViewById(R.id.faceView);

        // Initialize spinner
        Spinner spinner = (Spinner) findViewById(R.id.hairStyleSpinner);
        // Create an ArrayAdapter using the string array and a default spinner layout.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.hair_styles, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears.
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner.
        spinner.setAdapter(adapter);

        // Initialize listeners
        spinner.setOnItemSelectedListener(spinnerListener);
        hairRadioButton.setOnClickListener(radioButtonListener);
        eyesRadioButton.setOnClickListener(radioButtonListener);
        skinRadioButton.setOnClickListener(radioButtonListener);
        redSeekBar.setOnSeekBarChangeListener(seekBarListener);
        greenSeekBar.setOnSeekBarChangeListener(seekBarListener);
        blueSeekBar.setOnSeekBarChangeListener(seekBarListener);
        randomFaceButton.setOnClickListener(randomFaceButtonListener);


        // Generate and display random face
        generateRandomFace();
    }

    // Spinner listener
    private AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            // Change hair appearance
            String selectedStyle = (String) parent.getItemAtPosition(position);
            Toast.makeText(getApplicationContext(), "Selected hair style: " + selectedStyle, Toast.LENGTH_SHORT).show();
            // Code to change hair appearance
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    };

    // Radio button listener
    private View.OnClickListener radioButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // Move seekbars to reflect current color
            if (v.getId() == R.id.hairRadioButton) {
                redSeekBar.setProgress(Color.red(hairColor));
                greenSeekBar.setProgress(Color.green(hairColor));
                blueSeekBar.setProgress(Color.blue(hairColor));
            } else if (v.getId() == R.id.eyesRadioButton) {
                redSeekBar.setProgress(Color.red(eyesColor));
                greenSeekBar.setProgress(Color.green(eyesColor));
                blueSeekBar.setProgress(Color.blue(eyesColor));
            } else if (v.getId() == R.id.skinRadioButton) {
                redSeekBar.setProgress(Color.red(skinColor));
                greenSeekBar.setProgress(Color.green(skinColor));
                blueSeekBar.setProgress(Color.blue(skinColor));
            }
        }
    };

    // Seekbar listener
    private SeekBar.OnSeekBarChangeListener seekBarListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            // Change color on SurfaceView
            int red = redSeekBar.getProgress();
            int green = greenSeekBar.getProgress();
            int blue = blueSeekBar.getProgress();
            int color = Color.rgb(red, green, blue);

            if (hairRadioButton.isChecked()) {
                hairColor = color;
            } else if (eyesRadioButton.isChecked()) {
                eyesColor = color;
            } else if (skinRadioButton.isChecked()) {
                skinColor = color;
            }

            faceView.updateColor(hairColor, eyesColor, skinColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {
        }
    };

    // Random face button listener
    private View.OnClickListener randomFaceButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            generateRandomFace();
        }
    };


    // Generate random face
    private void generateRandomFace() {
        Random random = new Random();
        faceView.randomize();
        hairColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        eyesColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));
        skinColor = Color.rgb(random.nextInt(256), random.nextInt(256), random.nextInt(256));

        // Update UI
        redSeekBar.setProgress(Color.red(hairColor));
        greenSeekBar.setProgress(Color.green(hairColor));
        blueSeekBar.setProgress(Color.blue(hairColor));
        faceView.updateColor(hairColor, eyesColor, skinColor);
    }
}