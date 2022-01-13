package com.example.libertyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.parceler.Parcels;
@SuppressLint("HandlerLeak")
public class createAccount extends AppCompatActivity implements frameworkclientInterface{
    usercommandhandler myUsercommandhandler;
    TextView myMessageWindow;
    String userData;

    @Override
    public void update(String theMessage) {
        Message msg = Message.obtain();
        msg.obj = theMessage;
        handler.sendMessage(msg);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button create = findViewById(R.id.createButton1);
        client myClient = new client((int)7777, (String)"10.0.2.2", this);
        myUsercommandhandler = new usercommandhandler(this,myClient);
        myUsercommandhandler = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUsercommandhandler.handleUserCommand("2");
                EditText firstname = findViewById(R.id.firstnameBase);
                EditText middlename = findViewById(R.id.middleNameBase);
                EditText lastname = findViewById(R.id.lastNameBase);
                EditText emailAddress = findViewById(R.id.emailAddBase);
                EditText phoneNum = findViewById(R.id.phoneNumBase);
                EditText country = findViewById(R.id.countryBase);
                EditText password = findViewById(R.id.passwordBase);
                EditText confirmpassword = findViewById(R.id.confirmPasswordBase);
                EditText username = findViewById(R.id.userNameBase);
                String userDetails = firstname.getText().toString()+
                        "/"+middlename.getText().toString()+
                        "/"+lastname.getText().toString()+
                        "/"+emailAddress.getText().toString()+
                        "/"+phoneNum.getText().toString()+
                        "/"+country.getText().toString()+
                        "/"+password.getText().toString()+
                        "/"+confirmpassword.getText().toString()+
                        "/"+username.getText().toString();
                myUsercommandhandler.handleUserCommand("4"+"/"+"newuser"+"/"+userDetails);
                Intent intent = new Intent(createAccount.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            myMessageWindow.append("\n" + msg.obj.toString());
        }
    };
}