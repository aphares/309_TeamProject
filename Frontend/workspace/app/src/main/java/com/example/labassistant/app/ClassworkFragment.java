package com.example.labassistant.app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.labassistant.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassworkFragment extends Fragment {


    public ClassworkFragment() {
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
     *      Inflater for ClassworkFragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_classwork, container, false);
    }

}
