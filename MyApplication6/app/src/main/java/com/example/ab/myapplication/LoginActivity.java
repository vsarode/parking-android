package com.example.ab.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Response;
import com.example.ab.myapplication.asyncTask.UserLoginTask;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements OnClickListener {

    private UserLoginTask mAuthTask = null;

    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mLoginFormView;
    private Context myContext;
    Button mEmailSignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        myContext = this;
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);


        mPasswordView = (EditText) findViewById(R.id.password);

        mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        Button mSignUpButton = (Button) findViewById(R.id.SignUp);

        mSignUpButton.setOnClickListener(this);

        mEmailSignInButton.setOnClickListener(this);

        mLoginFormView = findViewById(R.id.login_form);
    }


    private void attemptLogin() {
        if (mAuthTask != null) {
            return;
        }

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();
        boolean isValidData = true;
        if (TextUtils.isEmpty(email) || !isEmailValid(email)) {
            mEmailView.setError("Invalid Email");
            isValidData = false;
        }
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Invalid password");
            isValidData = false;
        }
        if (!isValidData) {
            return;
        }
        final LoginActivity thisActivity = this;
        try {
            new UserLoginTask(email, password, myContext, new Response.Listener() {
                @Override
                public void onResponse(Object response) {
                    try {
                        JSONObject loginResponse = new JSONObject(response.toString());
                        Object res = loginResponse.get("responseData");
                        if (((JSONObject) res).get("login").toString().equals("Failure")) {
                            System.out.println("Failed to Login");
                            thisActivity.handleFailure();
                        } else {
                            System.out.println("SuccessFully Logged In");
                            thisActivity.handleSuccess();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleSuccess() {
        this.clearFileds();
        Intent intent = new Intent(this, ParkingGround.class);
        startActivity(intent);
    }

    private void handleFailure() {
        this.clearFileds();
    }

    private boolean isEmailValid(String email) {
        return email.contains("@");
    }

    private void clearFileds() {
        mEmailView.setText("");
        mPasswordView.setText("");
    }

    private boolean isPasswordValid(String password) {
        return password.length() > 4;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.SignUp) {
            Intent intent = new Intent(this, SignUp.class);
            startActivity(intent);
            mEmailSignInButton.setBackgroundColor(Color.RED);
        }
        if (view.getId() == R.id.email_sign_in_button) {
            attemptLogin();
        }
    }
}