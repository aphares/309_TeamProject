package com.example.labassistant.net_utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.labassistant.R;
import com.example.labassistant.app.ClassroomIndividual;

import java.util.ArrayList;
import java.util.List;

public class ClassroomListAdapter extends BaseAdapter {
    List<ClassroomIndividual> allIndividuals = new ArrayList<ClassroomIndividual>();
    Context context;

    public ClassroomListAdapter(Context givenContext){
        context = givenContext;
    }

    public void addIndividual(ClassroomIndividual givenClassroomIndividual){
        allIndividuals.add(givenClassroomIndividual);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return allIndividuals.size();
    }

    @Override
    public Object getItem(int position) {
        return allIndividuals.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ClassroomIndividual currentClassroomIndividual = (ClassroomIndividual) getItem(position);

        LayoutInflater classroomInflator =
                (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = classroomInflator.inflate(R.layout.classroom_list_layout, null);

        TextView classroomIndividualSectionNumberTextView =
                (TextView) convertView.findViewById(R.id.classroomIndividualSectionNumberTextView);
        TextView classroomIndividualNameTextView =
                (TextView) convertView.findViewById(R.id.classroomIndividualNameTextView);
        CheckBox classroomIndividualSelectedCheckbox =
                (CheckBox) convertView.findViewById(R.id.classroomIndividualSelectCheckBox);

        classroomIndividualSectionNumberTextView.setText(
                currentClassroomIndividual.getSectionNumber());
        classroomIndividualNameTextView.setText(currentClassroomIndividual.getName());
        classroomIndividualSelectedCheckbox.setChecked(currentClassroomIndividual.isCheckBox());

        return convertView;
    }
}
