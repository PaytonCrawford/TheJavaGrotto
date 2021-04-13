package com.example.thejavagrotto.API;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class APISingleton {

    public static APISingleton instance;
    private RequestQueue requestQueue;
    private static Context context;

    private APISingleton(Context context) {
        this.context = context;
    }

    public static APISingleton getInstance(Context context) {
        if (instance == null) {
            instance = new APISingleton(context);
        }
        return instance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return requestQueue;
    }

}
