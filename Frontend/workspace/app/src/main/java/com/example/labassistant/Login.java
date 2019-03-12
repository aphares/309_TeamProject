package com.example.labassistant;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.labassistant.app.MainActivity;
import com.example.labassistant.app.Message;
import com.example.labassistant.net_utils.MessageSingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Login extends AppCompatActivity {

    //Init the username and password edit text boxes
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button signInBtn;

    private boolean loginSuccessful = false;

    //URL to verify credentials
    private String credentialsDirectoryURL = "http://cs309-rr-2.misc.iastate.edu:8080/users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = (EditText) findViewById(R.id.usernameEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        signInBtn = (Button) findViewById(R.id.signInBtn);

        signInBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verifyCredentials();
            }
        });
    }

    /**
     * Verifies the credentials with the server
     * If credentials are correct, switches to main activity
     * Otherwise, toasts the user that the credentials are invalid
     */
    private void verifyCredentials() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, credentialsDirectoryURL,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String embedded_obj = response.getString("_embedded");
                    JSONObject jsonObject = new JSONObject(embedded_obj);
                    JSONArray user_table = jsonObject.getJSONArray("user_tableList");

                    //Gets the current credentials for edit text boxes
                    String currentUsername = usernameEditText.getText().toString().trim();
                    String currentPassword = passwordEditText.getText().toString().trim();

                    //The user "test" bypasses all checks
                    if (currentUsername.compareToIgnoreCase("test") == 0){
                        loginSuccessful = true;
                        saveUserInfo(currentUsername, currentPassword);
                        goToMainActivity();
                    }
                    //Regular checks against the credentials on the server
                    else if((currentUsername.length() > 0) && ((currentPassword.length() > 0))){
                        //Verifies to see if the credentials match.
                        int index = 0;
                        while (index < user_table.length()) {

                            JSONObject userCredentials = user_table.getJSONObject(index);

                            //Gets the credentials from server
                            String serverUsername = userCredentials.getString("username");
                            String serverPassword = userCredentials.getString("password");

                            //Compares to see if the credentials match
                            if (((serverUsername.compareToIgnoreCase(currentUsername) == 0) && (serverPassword.compareTo(currentPassword) == 0))) {
                                loginSuccessful = true;
                                saveUserInfo(currentUsername, currentPassword);
                                goToMainActivity();
                            }
                            index++;
                        }
                        //Toasts the user if the login was invalid
                        if(!loginSuccessful){
                            toastIncorrectLogin();
                        }
                    }
                    // Both fields are empty
                    else if(currentUsername.length() == 0 && currentPassword.length() == 0){
                        toastCredentialsEmpty();
                    }
                    //Username box is empty
                    else if(currentUsername.length() == 0){
                        toastUsernameEmpty();
                    }
                    //Password box is empty
                    else if (currentPassword.length() == 0){
                        toastPasswordEmpty();
                    }
                    else{
                        toastServerError();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                String currentUsername = usernameEditText.getText().toString().trim();
                String currentPassword = passwordEditText.getText().toString().trim();
                if (currentUsername.compareToIgnoreCase("test") == 0){
                    loginSuccessful = true;
                    saveUserInfo(currentUsername, currentPassword);
                    goToMainActivity();
                }
                toastServerError();
                error.printStackTrace();
            }
        });
        MessageSingleton.getInstance(Login.this).addToRequestQueue(jsonObjectRequest);
    }


    /**
     * Displays a toast telling the user that login failed
     */
    private void toastIncorrectLogin() {
        passwordEditText.setText("");
        Toast.makeText(this, "Incorrect Username or Password", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a toast telling the user that the user field is empty
     */
    private void toastUsernameEmpty() {
        Toast.makeText(this, "Username field cannot be empty", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a toast telling the user that the password field is empty
     */
    private void toastPasswordEmpty() {
        Toast.makeText(this, "Password field cannot be empty", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a toast telling the user that the login fields are empty
     */
    private void toastCredentialsEmpty() {
        Toast.makeText(this, "Fields cannot be empty", Toast.LENGTH_SHORT).show();
    }

    /**
     * Displays a toast for a server error
     */
    private void toastServerError() {
        passwordEditText.setText("");
        Toast.makeText(this, "Server Error", Toast.LENGTH_LONG).show();
    }

    /**
     * Saves the username and password in shared Pref "userInfo"
     * @param username
     *      Given username
     * @param password
     *      Given password
     */
    private void saveUserInfo(String username, String password){
        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.apply();
    }

    /**
     * Switches to the main activity
     */
    private void goToMainActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
