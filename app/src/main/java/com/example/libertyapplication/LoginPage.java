package com.example.libertyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginPage extends AppCompatActivity implements frameworkclientInterface{
    usercommandhandler myUsercommandhandler;
    String output = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);
        Button transfer = findViewById(R.id.transferButton);
        Button save = findViewById(R.id.saveButton);
        Button back = findViewById(R.id.backButton);
        EditText currentCash = findViewById(R.id.currentCash);

        client myClient = new client((int)7777, (String)"10.0.2.2", this);
        myUsercommandhandler = new usercommandhandler(this,myClient);

        myUsercommandhandler.handleUserCommand("2");


        String ma = getIntent().getStringExtra("output");
        currentCash.append(ma);
        System.out.println("Here we are with "+ ma);

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUsercommandhandler.handleUserCommand("4"+"/"+"transfer"+"/"+"Mayokun"+"/"+"Akintunde");
               // Intent intent = new Intent(LoginPage.this,Transfer.class);
                //startActivity(intent);
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,saved.class);
                startActivity(intent);
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginPage.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void update(String theString) {
        Message msg = Message.obtain();
        msg.obj = theString;
        output=msg.obj.toString();
    }
}