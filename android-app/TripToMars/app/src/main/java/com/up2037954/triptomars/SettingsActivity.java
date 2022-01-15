package com.up2037954.triptomars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.up2037954.triptomars.Models.UserSettings;

public class SettingsActivity extends AppCompatActivity {

    private UserSettings userSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        SwitchCompat imageAnimSwitch = findViewById(R.id.imageAnimSwitch);
        SwitchCompat textAnimSwitch = findViewById(R.id.textAnimSwitch);
        TextView progressDataText = findViewById(R.id.progressDataText);

        if (getIntent().getExtras() != null) {

            userSettings = (UserSettings)
                    getIntent().getSerializableExtra("UserSettings");

            imageAnimSwitch.setChecked(userSettings.allowImageAnimations);
            textAnimSwitch.setChecked(userSettings.allowTextAnimations);

            int totalNodesCount = getIntent().getIntExtra("NodeCount", 0);
            String progressData = userSettings.getExploredNodes().size() + " / " + totalNodesCount;
            progressDataText.setText(progressData);
        }

        imageAnimSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userSettings.allowImageAnimations = isChecked;
        });
        textAnimSwitch.setOnCheckedChangeListener((buttonView, isChecked) -> {
            userSettings.allowTextAnimations = isChecked;
        });
    }

    public void saveAndClose(View view) {
        Intent returnIntent = new Intent();
        returnIntent.putExtra("UserSettings", userSettings);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }
}