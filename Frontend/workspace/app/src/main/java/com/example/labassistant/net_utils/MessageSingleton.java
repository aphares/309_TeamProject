package com.example.labassistant.net_utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MessageSingleton {

    private static MessageSingleton mInstance;
    private RequestQueue requestQueue;
    private static Context mCtx;

    private MessageSingleton(Context context){
        mCtx = context;
        requestQueue = getRequestQueue();
    }

    public RequestQueue getRequestQueue(){
        if(requestQueue==null){
            requestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return requestQueue;
    }

    public static synchronized MessageSingleton getInstance(Context context){
        if (mInstance == null) {
            mInstance = new MessageSingleton(context);
        }
        return mInstance;
    }

    public<T> void addToRequestQueue(Request request){
        requestQueue.add(request);
    }
}
