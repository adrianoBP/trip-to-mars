package com.up2037954.triptomars;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.up2037954.triptomars.Helpers.AndroidHelper;
import com.up2037954.triptomars.Helpers.AppSettings;
import com.up2037954.triptomars.Helpers.MapHelper;
import com.up2037954.triptomars.Helpers.MapNav;
import com.up2037954.triptomars.Helpers.TypeWriter;
import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.UserSettings;
import com.up2037954.triptomars.Models.Utils.NodeCollection;
import com.up2037954.triptomars.Models.Utils.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MapNav mapNavigation;
    private Vibrator vibratorService;
    private LottieAnimationView animationView;
    private FloatingActionButton settingsButton;
    private UserSettings userSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            vibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            animationView = findViewById(R.id.animation);
            settingsButton = findViewById(R.id.settingsButton);

            AppSettings.init(this);

            // Load settings
            if (userSettings == null)
                userSettings = new UserSettings(this);
            else  // When we complete the map and we restart the game, we want to start from the first node
                userSettings.setLastVisitedNode(null);

            NodeCollection nodeCollection = MapHelper.buildMap(this);
            mapNavigation = new MapNav(userSettings, nodeCollection);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void playGame(View view) {

        try {
            Step currentStep = mapNavigation.getStartingStep(userSettings);
            drawStep(currentStep);

            // Add animation
            int animationId = getResources()
                    .getIdentifier("idle_astronaut_0", "raw", getPackageName());
            if (currentStep.getNode().hasAnimation() && !currentStep.getNode().isBeginning()) // We want the default animation when the game (re)starts
                animationId = getResources().getIdentifier(currentStep.getNode().getAnimation(), "raw", getPackageName());

            animationView.setAnimation(animationId);

            if (userSettings.allowImageAnimations)
                animationView.playAnimation();

            ((ConstraintLayout) findViewById(R.id.mainLayout)).addView(settingsButton);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void drawStep(Step step) throws IOException {

        Node currentNode = step.getNode();

        // Update title
        ((TextView) findViewById(R.id.mainText)).setText(currentNode.getTitle());

        // Update description
        if (userSettings.allowTextAnimations)
            ((TypeWriter) findViewById(R.id.descriptionText)).animateText(currentNode.getDescription());
        else
            ((TypeWriter) findViewById(R.id.descriptionText)).setText(currentNode.getDescription());

        // Add button options
        AndroidHelper.createButtons(getOptions(step), findViewById(R.id.mainLayout), this);

        // Update animation
        if (currentNode.hasAnimation()) {
            int id = getResources().getIdentifier(currentNode.getAnimation(), "raw", getPackageName());
            animationView.setAnimation(id);
            userSettings.setLastAnimationId(currentNode.getAnimation(), this);

            if (currentNode.getAnimationLoops() != 0)
                animationView.setRepeatCount(currentNode.getAnimationLoops() - 1);
            else
                animationView.setRepeatCount(LottieDrawable.INFINITE);

            if (userSettings.allowImageAnimations)
                animationView.playAnimation();
        }
    }

    private List<MaterialButton> getOptions(Step currentStep) {

        List<MaterialButton> buttonOptions = new ArrayList<>();

        // Convert options to buttons
        currentStep.getUserOptions().forEach(optionNode -> {
            MaterialButton optionButton = AndroidHelper.newButton(optionNode.getTitle(), this);

            // When this button is clicked, make sure ot run the next action
            optionButton.setOnClickListener(view -> {
                Step nextStep = null;
                try {
                    nextStep = mapNavigation.selectNextStep(optionNode, currentStep.isUserChoice(), this);
                    drawStep(nextStep);
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

            buttonOptions.add(optionButton);
        });

        // If the end was reached and there are no options, add a restart button
        if (buttonOptions.size() == 0) {

            vibratorService.vibrate(300);

            MaterialButton optionButton = AndroidHelper.newButton("Restart", R.color.teal_700, this);
            optionButton.setOnClickListener(this::playGame);

            buttonOptions.add(optionButton);
        }

        return buttonOptions;
    }

    public void openSettings(View view) {

        Intent intent = new Intent(this, SettingsActivity.class);
        intent.putExtra("UserSettings", userSettings);
        intent.putExtra("NodeCount", mapNavigation.getCollectionCount());
        startSettingsActivity.launch(intent);
    }

    private final ActivityResultLauncher<Intent> startSettingsActivity = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null) {
                    userSettings = (UserSettings) result.getData().getSerializableExtra("UserSettings");
                    try {
                        userSettings.save(this);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    );
}