package com.rsmsa.accapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

/**
 * Created by PETER on 11/3/2014.
 */
public class NetworkListener extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService( Context.CONNECTIVITY_SERVICE );
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {

            Toast.makeText(context, "Acc App is connected to internet",Toast.LENGTH_SHORT).show();
              try{
                Intent i=new Intent(context, SendLocalOnline.class);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            } catch(Exception e){
                e.printStackTrace();
            }

        } else {
            Toast.makeText(context, "Acc App is not connected to internet",Toast.LENGTH_SHORT).show();

        }// end of else
    }
}
