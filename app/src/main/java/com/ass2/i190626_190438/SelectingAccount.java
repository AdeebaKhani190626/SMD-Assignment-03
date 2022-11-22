package com.ass2.i190626_190438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class SelectingAccount extends AppCompatActivity {

    Button signin, fb, google, create;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selecting_account);

        signin = findViewById(R.id.signin);
        fb = findViewById(R.id.facebook);
        google = findViewById(R.id.google);
        create = findViewById(R.id.createaccount);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectingAccount.this, SignIn.class);
                startActivity(intent);
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectingAccount.this, SignUp.class);
                startActivity(intent);
            }
        });

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectingAccount.this,"Logging in through Facebook!", Toast.LENGTH_LONG).show();
            }
        });

        google.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SelectingAccount.this,"Logging in through Google!", Toast.LENGTH_LONG).show();
            }
        });
    }
}