package com.example.ab.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText name;
    EditText email;
    EditText address;
    EditText mobile;
    EditText pwd;
    EditText confirmpwd;
    Button sign_up;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        name = (EditText) findViewById(R.id.txtname);
        email = (EditText) findViewById(R.id.txtemail);
        address = (EditText) findViewById(R.id.txtaddress);
        mobile = (EditText) findViewById(R.id.txtmobile);
        pwd = (EditText) findViewById(R.id.txtpwd);
        confirmpwd = (EditText) findViewById(R.id.txtconfirmpwd);

        sign_up = (Button) findViewById(R.id.btnsign_up);
        final Context Iam = this;

        sign_up.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String uname = name.getText().toString();
                String uemail = email.getText().toString();
                String uaddress = address.getText().toString();
                String umobile = mobile.getText().toString();
                String upwd = pwd.getText().toString();
                String ucpwd = confirmpwd.getText().toString();
                String usignup = sign_up.getText().toString();

                Toast.makeText(Iam,"Successfull Registration",Toast.LENGTH_LONG).show();
                name.setText(" ");
                email.setText(" ");
                address.setText(" ");
                mobile.setText(" ");
                pwd.setText(" ");
                confirmpwd.setText(" ");
            }
        });
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
