package com.example.labassistant.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.labassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {

    private LinearLayout linearLayout;
    SettingsClassroomFragment settingsClassroomFragment;

    public SettingsFragment() {
        // Required empty public constructor
    }

    /**
     * @param inflater
     *      Given inflater
     * @param container
     *      Given container
     * @param savedInstanceState
     *      Given savedInstanceState
     * @return
     *      Inflater for SettingFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_settings, container, false);
        linearLayout = (LinearLayout) inflate.findViewById(R.id.classroomLinearLayout);
        settingsClassroomFragment = new SettingsClassroomFragment();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setFragment(settingsClassroomFragment);
            }
        });

        // Inflate the layout for this fragment
        return inflate;
    }

    private void setFragment(Fragment frag){
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.mainFrame, frag);
        fragmentTransaction.commit();
    }
}
