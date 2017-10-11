package com.example.bharath.silencev1;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.widget.TextView;

/**
 * Created by Bharath on 06-10-2017.
 */

public class SetMode {


    public void setMode(TextView res, String result, String finalCurrentTime, AudioManager audioManager, Context context)
    {
        res.setText(result.toString());
        if(finalCurrentTime.equals(res.getText().toString())){
            Intent i = new Intent(context, Silent.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,0,i,0);
            AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.set(AlarmManager.RTC_WAKEUP,System.currentTimeMillis()*1000,pendingIntent);
        }
    }



}
