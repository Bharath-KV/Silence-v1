package com.example.bharath.silencev1;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.media.AudioManager;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.text.Format;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    RequestQueue rq;

    private Button fajrText, dhuhrText, asrText, maghribText, ishaText;
    private Button gotoAbout,gotoHome;

    String loc, url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rq = Volley.newRequestQueue(this); //Creating a new volley request

        gotoAbout = (Button) findViewById(R.id.gotoAbout);

        gotoAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_about);
            }
        });

        final GetMyLocation getMyLocation = new GetMyLocation();

        try {

            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            if (location != null) {
                loc = getMyLocation.showMyAddress(location, getApplicationContext());
            }

            final LocationListener locationListener = new LocationListener() {
                public void onLocationChanged(Location location) {
                    loc = getMyLocation.showMyAddress(location, getApplicationContext());
                }

                public void onProviderDisabled(String arg0) {
                }

                public void onProviderEnabled(String arg0) {
                }

                public void onStatusChanged(String arg0, int arg1, Bundle arg2) {
                }
            };

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            {
                return;
            }
            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 2000, 10, locationListener);

        }catch (Exception e){
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        fajrText = (Button) findViewById(R.id.fajrStartTime);
        dhuhrText = (Button) findViewById(R.id.dhuhrStartTime);
        asrText = (Button) findViewById(R.id.asrStartTime);
        maghribText = (Button) findViewById(R.id.maghribStartTime);
        ishaText = (Button) findViewById(R.id.ishaStartTime);

        //url = // Put your website url here

        PrayerTimings prayerTimings = new PrayerTimings();

        //Calendar testDate = Calendar.getInstance();
        //Format format = android.text.format.DateFormat.getTimeFormat(getApplicationContext());
        //String currentTime = format.format(testDate.getTime());

        //final AudioManager audioManager = (AudioManager) getSystemService(getApplicationContext().AUDIO_SERVICE);

        //final String finalCurrentTime = currentTime;
        prayerTimings.sendjsonrequest(url, rq, fajrText,dhuhrText,asrText,maghribText,ishaText,getApplicationContext(), new ServerCallback() {
            @Override
            public void onSuccess(String result) {
                //SetMode setMode = new SetMode();
                //setMode.setMode(res,result,finalCurrentTime,audioManager,getApplicationContext());
            }
        });
    }
}
