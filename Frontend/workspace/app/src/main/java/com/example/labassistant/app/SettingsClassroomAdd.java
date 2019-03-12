package com.example.labassistant.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.labassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsClassroomAdd extends Fragment {

    public SettingsClassroomAdd() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View inflate =  inflater.inflate(R.layout.fragment_settings_classroom_add, container, false);

        return inflate;
    }

}
