package com.example.labassistant.app;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.TextView;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.labassistant.R;
import com.example.labassistant.net_utils.ClassroomListAdapter;
import com.example.labassistant.net_utils.MessageSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsClassroomStudentFragment extends Fragment {

    private String taListDirectory = "http://cs309-rr-2.misc.iastate.edu:8080/students";

    private static ListView classroomStudentListView;
    private ClassroomIndividual classroomIndividual;
    private static ClassroomListAdapter classroomListAdapter;

    private FloatingActionButton addButton;

    private TextView okTextView;
    private TextView cancelTextView;

    public SettingsClassroomStudentFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View inflate = inflater.inflate(R.layout.fragment_settings_classroom_student, container,
                false);

        classroomStudentListView = inflate.findViewById(R.id.classroomStudentListView);

        classroomListAdapter = new ClassroomListAdapter(getContext());
        //Init
        addButton = (FloatingActionButton) inflate.findViewById(R.id.classroomStudentAddBtn);

        //On click listeners
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopUpUtils.generateAddPopup(inflate, getLayoutInflater());
            }
        });

        getAllTAs();

        return inflate;
    }


    public void getAllTAs(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                taListDirectory, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String embedded_obj = response.getString("_embedded");
                    JSONObject jsonObject = new JSONObject(embedded_obj);
                    JSONArray users_table = jsonObject.getJSONArray("studentList");

                    int index = 0;
                    while(index < users_table.length()){
                        JSONObject currentObject = users_table.getJSONObject(index);
                        JSONObject userObject = currentObject.getJSONObject("user");

                        String firstName = userObject.getString("firstname");
                        String lastname = userObject.getString("lastname");
                        String sectionNumber = userObject.getString("section");

                        String fullName = firstName + " " + lastname;

                        classroomIndividual = new ClassroomIndividual(sectionNumber, fullName);
                        classroomListAdapter.addIndividual(classroomIndividual);
                        classroomStudentListView.setAdapter(classroomListAdapter);
                        index++;
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast toast = Toast.makeText(getContext(), "Failed to get TAs",
                        Toast.LENGTH_LONG);
                toast.show();
                error.printStackTrace();
            }
        });
        MessageSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }
}
