package com.example.ab.myapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.ab.myapplication.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

public class UserLoginTask extends AsyncTask<Void, Void, Boolean> {

    private final String email;
    private final String password;
    Context contex;
    boolean result = false;

    public UserLoginTask(String email, String password, Context contex) {
        this.email = email;
        this.password = password;
        this.contex = contex;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(this.contex, "Logging in...", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(contex);
            String url = Constants.SERVER_URL + "/login/";
            HashMap<String, String> paramsBody = new HashMap<>();
            paramsBody.put("username", email);
            paramsBody.put("password", password);
            JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(paramsBody), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Request Success ######################");
                    result = true;
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    NetworkResponse response = error.networkResponse;
                    if (error instanceof ServerError && response != null) {
                        try {
                            String res = new String(response.data, HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                            JSONObject obj = new JSONObject(res);
                            System.out.println("--------------Response : " + obj.toString());
                        } catch (UnsupportedEncodingException e1) {
                            e1.printStackTrace();
                        } catch (JSONException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
            });
            requestQueue.add(request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(final Boolean success) {
        if (result) {
            System.out.println("GOt success response.......^^^^^^^^^^^^^^^^^^^^");
            Toast.makeText(contex, "Success...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contex, "Login Attempt Failed", Toast.LENGTH_SHORT).show();
        }
    }
}