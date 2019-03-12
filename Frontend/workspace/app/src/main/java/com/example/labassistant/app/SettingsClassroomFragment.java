package com.example.labassistant.app;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import com.example.labassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsClassroomFragment extends Fragment {

    SettingsClassroomStudentFragment settingsClassroomStudentFragment;
    SettingsClassroomTaFragment settingsClassroomTaFragment;

    //Init variables needed throughout the class
    private ImageView sortView;



    TabLayout tabLayout;

    public SettingsClassroomFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_settings_classroom, container,
                false);
        settingsClassroomStudentFragment = new SettingsClassroomStudentFragment();
        settingsClassroomTaFragment = new SettingsClassroomTaFragment();

        tabLayout = inflate.findViewById(R.id.classroomTabLayout);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab.getPosition() == 0){
                    setFragment(settingsClassroomStudentFragment);
                }
                else if (tab.getPosition() == 1){
                    setFragment(settingsClassroomTaFragment);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        setFragment(settingsClassroomStudentFragment);


        //Handles the sort button click
        sortView = (ImageView) inflate.findViewById(R.id.sortImageView);
        sortView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpUtils.generateSortPopup(inflate, getLayoutInflater());
            }
        });


        return inflate;
    }

    private void setFragment(Fragment frag){
        FragmentTransaction fragmentTransaction =
                getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.classroomListFragment, frag);
        fragmentTransaction.commit();
    }

}
