package com.example.ab.myapplication.asyncTask;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.ab.myapplication.constants.Constants;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

public class UserSignUp extends AsyncTask<Void, Void, Boolean> {
    Context contex;
    String uname;
    String uemail;
    String uaddress;
    String umobile;
    String upwd;
    String ucpwd;
    boolean success;

    public UserSignUp(Context contex, String uname, String uemail, String uaddress, String umobile, String upwd, String ucpwd) {
        this.contex = contex;
        this.uname = uname;
        this.uemail = uemail;
        this.uaddress = uaddress;
        this.umobile = umobile;
        this.upwd = upwd;
        this.ucpwd = ucpwd;
        success = false;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            RequestQueue requestQueue = Volley.newRequestQueue(contex);
            String url = Constants.SERVER_URL + "/user/";
            HashMap<String, String> paramsBody = new HashMap<>();

            paramsBody.put("name", uname);
            paramsBody.put("email", uemail);
            paramsBody.put("password", upwd);
            paramsBody.put("address", uaddress);
            paramsBody.put("mobileNo", umobile);

            JsonObjectRequest request = new JsonObjectRequest(url, new JSONObject(paramsBody), new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {
                    System.out.println("Request Success ######################" + response);
                    success = true;
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
        System.out.println("Success Value ------------------" + success);
        return success;
    }

    @Override
    protected void onPostExecute(Boolean success) {
        if (success) {
            System.out.println("GOt success response.......^^^^^^^^^^^^^^^^^^^^");
            Toast.makeText(contex, "Success...", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(contex, "Login Attempt Failed", Toast.LENGTH_SHORT).show();
        }
    }
}