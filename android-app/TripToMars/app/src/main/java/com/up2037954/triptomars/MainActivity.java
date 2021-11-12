package com.up2037954.triptomars;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;
import com.up2037954.triptomars.Helpers.AndroidHelper;
import com.up2037954.triptomars.Helpers.AppSettings;
import com.up2037954.triptomars.Helpers.FSHelper;
import com.up2037954.triptomars.Helpers.LogicHelper;
import com.up2037954.triptomars.Helpers.MapHelper;
import com.up2037954.triptomars.Models.Android.NodeScreenContent;
import com.up2037954.triptomars.Models.Node;
import com.up2037954.triptomars.Models.UserSettings;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private List<Button> displayedButtons;

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

        UserSettings userSettings = new UserSettings();  // TODO: Load if exist

        FSHelper fsHelper = MapHelper.buildMap(this);
        MapHelper.MapData mapData = MapHelper.validateMap(userSettings, fsHelper);

        Node startingNode = mapData.getStartingNode();

        drawScreen(getNodeScreenContent(startingNode, false, userSettings, mapData));
    }

    private void drawScreen(NodeScreenContent screen) {
        ((TextView) findViewById(R.id.mainText)).setText(screen.getNode().getTitle());
        addButtons(screen.getButtons());
    }

    public void addButtons(List<MaterialButton> buttons) {

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

    public NodeScreenContent getNodeScreenContent(Node currentNode, boolean skipOptionScreen, UserSettings userSettings, MapHelper.MapData mapData) throws Exception {

        if (!TextUtils.isEmpty((currentNode.getItemToSave())))
            LogicHelper.SaveResult(currentNode.getItemToSave(), userSettings);

        List<Node> availableOptions = LogicHelper.getNextNodes(mapData.getMapNodes(), currentNode, userSettings);
        if (skipOptionScreen && availableOptions.size() == 1) {
            currentNode = availableOptions.get(0);
            availableOptions = LogicHelper.getNextNodes(mapData.getMapNodes(), currentNode, userSettings);
        }

        NodeScreenContent screenDetails = new NodeScreenContent(currentNode);

        // If there's only one available option, add a "NEXT" button to allow the user to read the content
        if (availableOptions.size() == 1) {

            MaterialButton optionButton = AndroidHelper.createButton("next", this);
            Node nextNode = availableOptions.get(0);  // The node to show once the "NEXT" button is clicked should be the only available option

            optionButton.setOnClickListener(view -> {
                try {
                    drawScreen(getNodeScreenContent(nextNode, false, userSettings, mapData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });

            screenDetails.addButton(optionButton);
            return screenDetails;
        }

        // Create new buttons and add its relative event
        for (Node nodeOption : availableOptions) {
            MaterialButton optionButton = AndroidHelper.createButton(nodeOption.getTitle(), this);
            optionButton.setOnClickListener(view -> {
                try {
                    drawScreen(getNodeScreenContent(nodeOption, true, userSettings, mapData));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            screenDetails.addButton(optionButton);
        }

        return screenDetails;
    }
}