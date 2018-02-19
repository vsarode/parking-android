package com.example.ab.myapplication.asyncTask;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;

public class SignUpSuccess implements Response.Listener {
    Context context;
    public SignUpSuccess(Context context) {
        this.context = context;
    }

    @Override
    public void onResponse(Object response) {
        Toast.makeText(context, "Success...", Toast.LENGTH_SHORT).show();
    }
}
