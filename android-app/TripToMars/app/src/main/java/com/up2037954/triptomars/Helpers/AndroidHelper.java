package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.google.android.material.button.MaterialButton;
import com.up2037954.triptomars.R;

import java.util.List;

public class AndroidHelper {

    public static MaterialButton newButton(String text, Context context) {
        return newButton(text, R.color.purple_200, context);
    }

    public static MaterialButton newButton(String text, int colorId, Context context) {
        MaterialButton newButton = new MaterialButton(context);
        newButton.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newButton.setText(text);
        newButton.setId(View.generateViewId());
        newButton.setBackgroundColor(context.getColor(colorId));

        return newButton;
    }

    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }

    public static void createButtons(List<MaterialButton> buttonsToAdd, ConstraintLayout layout, Context context) {

        // Remove the old buttons
        layout.getTouchables().forEach(layout::removeView);

        // Add the new buttons
        buttonsToAdd.forEach(layout::addView);

        ConstraintSet constraintSet = new ConstraintSet();

        // Make buttons look nice
        for (int i = 0; i < buttonsToAdd.size(); i++) {
            constraintSet.clone(layout);
            // First button should be pinned to the bottom of the screen, everything else to the previous button
            if (i == 0) {
                constraintSet.connect(buttonsToAdd.get(i).getId(), ConstraintSet.BOTTOM,
                        layout.getId(), ConstraintSet.BOTTOM,
                        AndroidHelper.convertDpToPx(context, 32));
            } else {
                constraintSet.connect(buttonsToAdd.get(i).getId(), ConstraintSet.BOTTOM,
                        buttonsToAdd.get(i - 1).getId(), ConstraintSet.TOP,
                        AndroidHelper.convertDpToPx(context, 12));
            }

            // Center horizontally
            constraintSet.connect(buttonsToAdd.get(i).getId(), ConstraintSet.LEFT,
                    layout.getId(), ConstraintSet.LEFT,
                    AndroidHelper.convertDpToPx(context, 64));
            constraintSet.connect(buttonsToAdd.get(i).getId(), ConstraintSet.RIGHT,
                    layout.getId(), ConstraintSet.RIGHT,
                    AndroidHelper.convertDpToPx(context, 64));

            constraintSet.applyTo(layout);
        }
    }
}
