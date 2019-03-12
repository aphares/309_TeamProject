package com.example.labassistant.app;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.labassistant.R;

public class PopUpUtils {

    /**
     * Generates a popUp View for add
     * @param view
     * @param layoutInflater
     */
    public static void generateAddPopup(View view, LayoutInflater layoutInflater){
        View popupSortView = layoutInflater.inflate(R.layout.fragment_settings_classroom_add, null);

        final TextView cancelTextView = popupSortView.findViewById(R.id.cancelTextView);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupSortView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER,0, 0);


        //on Click listener that will close the popup view
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * Generates the pop view for sort
     */
    public static void generateSortPopup(View view, LayoutInflater layoutInflater){
        View popupSortView = layoutInflater.inflate(R.layout.fragment_settings_classroom_sort, null);
        final TextView cancelTextView = popupSortView.findViewById(R.id.cancelTextView);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        final int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        final PopupWindow popupWindow = new PopupWindow(popupSortView, width, height, true);
        popupWindow.showAtLocation(view, Gravity.CENTER,0, 0);


        //on Click listener that will close the popup view
        cancelTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

}
