package com.ass2.i190626_190438;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyBroadcastReceiver receiver = new MyBroadcastReceiver();
        IntentFilter intfil=new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(receiver,intfil);

        Intent intent = new Intent(MainActivity.this, SelectingAccount.class);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sharedPreferences = getSharedPreferences(SignIn.PREFS_NAME,0);
                boolean hasLoggedIn = sharedPreferences.getBoolean("hasLoggedIn",false);

                if (hasLoggedIn) {
                    Intent intent1 = new Intent(MainActivity.this, HomePage.class);
                    startActivity(intent1);
                    finish();
                } else {
                    startActivity(intent);
                    finish();
                }
            }
        },5000);
    }
}