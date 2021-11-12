package com.up2037954.triptomars.Helpers;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.button.MaterialButton;

public class AndroidHelper {

    public static MaterialButton createButton(String text, Context context) {
        MaterialButton newButton = new MaterialButton(context);
        newButton.setLayoutParams(new ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        newButton.setText(text);
        newButton.setId(View.generateViewId());
        return newButton;
    }

    public static int convertDpToPx(Context context, float dp) {
        return (int) (dp * context.getResources().getDisplayMetrics().density);
    }
}
