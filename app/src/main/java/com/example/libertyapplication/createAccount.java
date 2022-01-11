package com.example.libertyapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.parceler.Parcels;

public class createAccount extends AppCompatActivity {
    usercommandhandler myUsercommandhandler;

    public createAccount(usercommandhandler myUsercommandhandler){
        this.myUsercommandhandler = myUsercommandhandler;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        Button create = findViewById(R.id.createButton1);
        myUsercommandhandler = Parcels.unwrap(getIntent().getParcelableExtra("user"));
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                System.out.println(userDetails);
                if(getIntent().getExtras() != null) {
                    myUsercommandhandler = (usercommandhandler) getIntent().getSerializableExtra("myUsercommandhandler");
                }
                myUsercommandhandler.handleUserCommand("4"+"/"+"newuser"+"/"+userDetails);
                Intent intent = new Intent(createAccount.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}