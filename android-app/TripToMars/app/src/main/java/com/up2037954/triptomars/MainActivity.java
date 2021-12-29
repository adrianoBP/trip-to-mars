package com.up2037954.triptomars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;
import com.google.android.material.button.MaterialButton;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            vibratorService = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            animationView = findViewById(R.id.animation);

            AppSettings.init(this);
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void startApp(View view) {

        UserSettings userSettings = new UserSettings();

        try {

            NodeCollection nodeCollection = MapHelper.buildMap(this);
            mapNavigation = new MapNav(userSettings, nodeCollection, this);

            Step currentStep = mapNavigation.getStartingStep();
            drawStep(currentStep);

            int id = getResources().getIdentifier("idle_astronaut_0", "raw", getPackageName());
            animationView.setAnimation(id);

        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void drawStep(Step step) {

        Node currentNode = step.getNode();

        // Update title
        ((TextView) findViewById(R.id.mainText)).setText(currentNode.getTitle());

        // Update description
        ((TypeWriter) findViewById(R.id.descriptionText)).animateText(currentNode.getDescription());

        // Add button options
        AndroidHelper.createButtons(getOptions(step), (ConstraintLayout) findViewById(R.id.mainLayout), this);

        // Update animation
        if (currentNode.hasAnimation()) {
            int id = getResources().getIdentifier(currentNode.getAnimation(), "raw", getPackageName());
            animationView.setAnimation(id);

            if (currentNode.getAnimationLoops() != 0)
                animationView.setRepeatCount(currentNode.getAnimationLoops() - 1);
            else
                animationView.setRepeatCount(LottieDrawable.INFINITE);

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
                    nextStep = mapNavigation.selectNextStep(optionNode, currentStep.isUserChoice());
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
            optionButton.setOnClickListener(this::startApp);

            buttonOptions.add(optionButton);
        }

        return buttonOptions;
    }
}