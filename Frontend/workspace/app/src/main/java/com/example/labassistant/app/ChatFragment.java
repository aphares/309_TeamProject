package com.example.labassistant.app;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.labassistant.net_utils.MessageSingleton;
import com.example.labassistant.net_utils.MessagesAdapter;
import com.example.labassistant.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * A simple {@link Fragment} subclass.
 */
public class ChatFragment extends Fragment {

    //URL to post and get the data from
    private String messageObjectArrayDirectory = "http://cs309-rr-2.misc.iastate.edu:8080/chatData";

    //Init variables needed throughout the class
    private TextView messageBox;
    private ImageButton sendButton;
    private static ListView messageView;
    private Message currentMessage;
    private static MessagesAdapter messagesAdapter;

    //Timer Handler
    private Handler handler = new Handler();
    private int delay = 1000; //milliseconds

    //Chat size and boolean to keep track of first get for chat
    private int chatSize = 0;
    private boolean initChatFetch = true;

    //Current user
    private String currentUser;

    public ChatFragment() {
        // Required empty public constructor
    }

    /**
     * @param inflater           given inflater
     * @param container          given container
     * @param savedInstanceState given savedInstanceState
     * @return The view for this fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //assigning xml elements to variables
        View v = inflater.inflate(R.layout.fragment_chat, container, false);
        messageBox = (EditText) v.findViewById(R.id.chatEditText);
        sendButton = (ImageButton) v.findViewById(R.id.sendButton);
        messageView = (ListView) v.findViewById(R.id.messages_view);

        //Creating a messageAdapter for this context
        messagesAdapter = new MessagesAdapter(getContext());

        //Send button listener
        sendButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendAMessage();
            }
        });

        //Setting the current user
        Context context = getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("userInfo", Context.MODE_PRIVATE);
        currentUser = sharedPreferences.getString("username", "Invalid");

        //Call to get all messages
        messagesAdapter.clearMessages();
        chatSize = 0;
        initChatFetch = true;
        getAllMessages();

        //Handler to get messages
        handler.postDelayed(new Runnable() {
            public void run() {
                getAllMessages();
                handler.postDelayed(this, delay);
            }
        }, delay);

        return v;
    }

    /**
     * Sends the current message to the server
     */
    private void sendAMessage() {
        final String currentContent = messageBox.getText().toString();
        if (currentContent.length() > 0) {

            //Creating Json object using message and netid
            JSONObject jsonObject = new JSONObject();
            try {
                //Assigning values to the jsonObject
                jsonObject.put("netid", currentUser);
                jsonObject.put("message", currentContent);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, messageObjectArrayDirectory,
                    jsonObject, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    //Makes a failed error toast to the user
                    Context context = getContext();
                    CharSequence text = "Failed to Send";
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    error.printStackTrace();
                }
            });
            //Adding the jsonObjectRequest to the queue
            MessageSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);

            //Text box gets reset back to empty, gets all messages and refreshes the list view to the bottom
            messageBox.setText("");
            getAllMessages();
            scrollMyListViewToBottom();
        }
    }

    /**
     * Updates the messageView with new messages
     */
    private void getAllMessages() {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, messageObjectArrayDirectory,
                null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String embedded_obj = response.getString("_embedded");
                    JSONObject jsonObject = new JSONObject(embedded_obj);
                    JSONArray chat_table = jsonObject.getJSONArray("chat_tableList");

                    //Checks to see if the message list has a new message to update
                    if (chatSize != chat_table.length()) {

                        //Holds the last message user
                        String name = "";

                        // Gets all the messages from the chat_table
                        int index = 0;
                        while (index < chat_table.length()) {
                            JSONObject message = chat_table.getJSONObject(index);
                            String id = message.getString("id");
                            name = message.getString("netid");
                            String content = message.getString("message");
                            String timeStamp = message.getString("timestamp");
                            currentMessage = new Message(Integer.parseInt(id), name, content, timeStamp);
                            messagesAdapter.addMessage(currentMessage);
                            messageView.setAdapter(messagesAdapter);
                            index++;
                        }

                        //Skips "New Message" toast on first fetch for messages
                        if (!initChatFetch && (currentUser.compareTo(name) != 0)) {
                            //Makes a toast when there is a new message
                            Context context = getContext();
                            CharSequence text = "New Message";
                            int duration = Toast.LENGTH_SHORT;
                            Toast toast = Toast.makeText(context, text, duration);
                            toast.show();
                        }
                        initChatFetch = false;

                        //Updating chatSize with new size
                        chatSize = chat_table.length();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                messageBox.setText("Something went wrong");
                error.printStackTrace();
            }
        });
        MessageSingleton.getInstance(getContext()).addToRequestQueue(jsonObjectRequest);
    }

    /**
     * Scroll the view list to the bottom
     */
    static public void scrollMyListViewToBottom() {
        messageView.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
                messageView.setSelection(messagesAdapter.getCount() - 1);
            }
        });
    }
}
