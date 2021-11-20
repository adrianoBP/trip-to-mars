package com.up2037954.triptomars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.up2037954.triptomars.Helpers.AndroidHelper;
import com.up2037954.triptomars.Helpers.AppSettings;
import com.up2037954.triptomars.Helpers.MapHelper;
import com.up2037954.triptomars.Helpers.MapNav;
import com.up2037954.triptomars.Models.NodeData.Node;
import com.up2037954.triptomars.Models.UserSettings;
import com.up2037954.triptomars.Models.Utils.Step;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Button> displayedButtons;
    private MapNav mapNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            AppSettings.init(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void startApp(View view) throws Exception {

        displayedButtons = Collections.singletonList((Button) findViewById(R.id.testButton));

        UserSettings userSettings = new UserSettings();

        MapHelper.buildMap(this);

        mapNavigation = new MapNav(userSettings, this);
        Step currentStep = mapNavigation.getStartingStep();
        drawStep(currentStep);
    }

    private void drawStep(Step step) throws Exception {
        ((TextView) findViewById(R.id.mainText)).setText(step.getNode().getTitle());
        addButtons(getOptions(step));
    }


    private void addButtons(List<MaterialButton> buttons) {

        ConstraintLayout mainLayout = (ConstraintLayout) findViewById(R.id.mainLayout);

        for (Button buttonToRemove : displayedButtons) {
            mainLayout.removeView(buttonToRemove);
        }

        displayedButtons = new ArrayList<>();
        ConstraintSet set = new ConstraintSet();

        // Make sure all the buttons are in the layout
        buttons.forEach(button -> {
            mainLayout.addView(button);
            displayedButtons.add(button);
        });

        // Make buttons look nice
        for (int i = 0; i < buttons.size(); i++) {
            set.clone(mainLayout);
            // First button should be pinned to the bottom of the screen, everything else to the previous button
            if (i == 0) {
                set.connect(buttons.get(i).getId(), ConstraintSet.BOTTOM,
                        mainLayout.getId(), ConstraintSet.BOTTOM,
                        AndroidHelper.convertDpToPx(this, 32));
            } else {
                set.connect(buttons.get(i).getId(), ConstraintSet.BOTTOM,
                        buttons.get(i - 1).getId(), ConstraintSet.TOP,
                        AndroidHelper.convertDpToPx(this, 12));
            }

            // Center horizontally
            set.connect(buttons.get(i).getId(), ConstraintSet.LEFT,
                    mainLayout.getId(), ConstraintSet.LEFT,
                    AndroidHelper.convertDpToPx(this, 64));
            set.connect(buttons.get(i).getId(), ConstraintSet.RIGHT,
                    mainLayout.getId(), ConstraintSet.RIGHT,
                    AndroidHelper.convertDpToPx(this, 64));

            set.applyTo(mainLayout);
        }
    }

    private List<MaterialButton> getOptions(Step currentStep) throws Exception {

        List<MaterialButton> buttonOptions = new ArrayList<>();

        for (Node node : currentStep.getUserOptions()) {
            MaterialButton optionButton = AndroidHelper.createButton(node.getTitle(), this);

            optionButton.setOnClickListener(view -> {
                try {
                    mapNavigation.saveUserOption(node);
                    Step nextStep = mapNavigation.selectNextStep(currentStep.getUserOptions().size() > 1);
                    drawStep(nextStep);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            buttonOptions.add(optionButton);
        }

        return buttonOptions;
    }
}