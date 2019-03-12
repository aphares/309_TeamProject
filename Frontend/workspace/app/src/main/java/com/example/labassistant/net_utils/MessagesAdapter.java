package com.example.labassistant.net_utils;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.labassistant.R;
import com.example.labassistant.app.Message;

import java.util.ArrayList;
import java.util.List;

public class MessagesAdapter extends BaseAdapter {

    List<Message> allMessage = new ArrayList<Message>();
    Context context;

    public MessagesAdapter(Context givenContext){
        context = givenContext;
    }

    public void addMessage(Message givenMessage){
        allMessage.add(givenMessage);
        notifyDataSetChanged();
    }

    public void clearMessages(){
        allMessage.clear();
    }

    @Override
    public int getCount() {
        return allMessage.size();
    }

    @Override
    public Message getItem(int position) {
        return allMessage.get(position);
    }

    @Override
    public long getItemId(int position) {
        return allMessage.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Message currentMessage = getItem(position);

        LayoutInflater messageInflater = (LayoutInflater)
                context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        convertView = messageInflater.inflate(R.layout.messages_layout, null);

        TextView messageContentTextView =
                (TextView) convertView.findViewById(R.id.messageContentTextView);
        TextView messageTagTextView =
                (TextView) convertView.findViewById(R.id.messageTagTextView);

        messageContentTextView.setText(currentMessage.getContent());
        messageTagTextView.setText(currentMessage.getTag());

        return convertView;
    }
}
