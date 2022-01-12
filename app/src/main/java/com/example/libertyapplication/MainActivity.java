package com.example.libertyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.parceler.Parcel;
import org.parceler.Parcels;

public class MainActivity extends AppCompatActivity implements frameworkclientInterface {
    usercommandhandler myUsercommandhandler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create = findViewById(R.id.createButton);
        client myClient = new client((int)7777, (String)"10.0.2.2", this);
        myUsercommandhandler = new usercommandhandler(this,myClient);
        Button login = findViewById(R.id.loginButton);

        if(getIntent().getExtras() != null) {
            String user =(String) getIntent().getSerializableExtra("user");
            System.out.println(user.toString());
        }

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,createAccount.class);
                startActivity(intent);
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myUsercommandhandler.handleUserCommand("2");
                EditText username = findViewById(R.id.userName);
                EditText password = findViewById(R.id.password);
                String userDetails = username.getText().toString()+"/"+password.getText().toString();
                System.out.println(userDetails);
                myUsercommandhandler.handleUserCommand("4"+"/"+"viewuser"+"/"+userDetails);
                // return value that you will use to create a new user class/object
                // Pass that class through Intent.putExtra into the Login Page so That we can view
                // the User Profile
                myUsercommandhandler.handleUserCommand("3");
                Intent intent = new Intent(MainActivity.this,LoginPage.class);
                startActivity(intent);
            }
        });




    }
    TextView myMessageWindow;
    public void update(String theString) {
      //  Message msg = Message.obtain();
        //msg.obj = theString;
        //handler.sendMessage(msg);
    }
  //  Handler handler = new Handler() {
    //    @Override
     //   public void handleMessage(Message msg) {
       //     myMessageWindow.append("\n" + msg.obj.toString());
        //}
    //};
}