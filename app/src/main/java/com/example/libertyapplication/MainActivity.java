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
import android.widget.Toast;

import org.parceler.Parcel;
import org.parceler.Parcels;
@SuppressLint("HandlerLeak")
public class MainActivity  extends AppCompatActivity implements frameworkclientInterface  {
    usercommandhandler myUsercommandhandler;
    String output = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button create = findViewById(R.id.createButton);
        Button login = findViewById(R.id.loginButton);
        client myClient = new client((int)7777, (String)"10.0.2.2", this);
        myUsercommandhandler = new usercommandhandler(this,myClient);

        myUsercommandhandler.handleUserCommand("2");

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
                EditText username = findViewById(R.id.userName);
                EditText password = findViewById(R.id.password);
                String userDetails = username.getText().toString()+"/"+password.getText().toString();
                System.out.println("This is the user detail from Main Activity "+userDetails);
                myUsercommandhandler.handleUserCommand("4"+"/"+"viewuser"+"/"+userDetails);
                // return value that you will use to create a new user class/object
                // Pass that class through Intent.putExtra into the Login Page so That we can view
                // the User Profile
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(output.compareTo("")!=0){
                    Intent intent = new Intent(MainActivity.this,LoginPage.class);
                    System.out.println(output);
                    intent.putExtra("output",output);
                    //myUsercommandhandler.handleUserCommand("3");
                    startActivity(intent);
                }else{
                    Toast.makeText(MainActivity.this, "Error getting user. Please try again", Toast.LENGTH_SHORT).show();
                }
                System.out.println("This is the output we were looking for: "+output);

            }
        });




    }
    public void update(String theString) {
        Message msg = Message.obtain();
        msg.obj = theString;
        output=msg.obj.toString();
    }
}