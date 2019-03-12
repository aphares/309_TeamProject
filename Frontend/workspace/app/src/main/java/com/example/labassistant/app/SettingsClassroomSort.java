package com.example.labassistant.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.labassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsClassroomSort extends Fragment {

    public SettingsClassroomSort() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings_classroom_sort, container, false);
    }

}
