package com.ass2.i190626_190438;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.util.Log;
import android.widget.Toast;

public class MyBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        /* Checking if a broadcast is received
        Toast.makeText(context,"Received",Toast.LENGTH_LONG).show();
        Log.d("broadcastReceived",intent.getAction()+""); */

        if (intent.getAction()== ConnectivityManager.CONNECTIVITY_ACTION)
        {
            // Multiple broadcast
            Boolean ans = intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,false);
            if (ans)
            {
                Toast.makeText(context,"Internet Disconnect",Toast.LENGTH_LONG).show();
                Log.d("broadcastReceived","Disconnected");
            }
            else
            {
                Toast.makeText(context,"Internet Connected",Toast.LENGTH_LONG).show();
                Log.d("broadcastReceived","Connected");
            }
        }
    }
}
