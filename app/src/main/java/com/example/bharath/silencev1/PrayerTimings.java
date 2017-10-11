package com.example.bharath.silencev1;

import android.content.Context;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.text.format.Time;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.bharath.silencev1.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Bharath on 03-10-2017.
 */

public  class PrayerTimings extends MainActivity {

    static String fajr;
    static String dhuhr;
    static String asr;
    static String maghrib;
    static String isha;

    public void sendjsonrequest(String url, RequestQueue rq, final Button fajrText, final Button dhuhrText,
                                final Button asrText, final Button maghribText, final Button ishaText, final Context context, final ServerCallback serverCallback)
    {

        final JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray array = response.getJSONArray("items");
                    JSONObject object = array.getJSONObject(0);
                    fajr = object.getString("fajr");
                    dhuhr = object.getString("dhuhr");
                    asr = object.getString("asr");
                    maghrib = object.getString("maghrib");
                    isha = object.getString("isha");

                    serverCallback.onSuccess(fajr);

                    fajrText.setText(fajr);
                    dhuhrText.setText(dhuhr);
                    asrText.setText(asr);
                    maghribText.setText(maghrib);
                    ishaText.setText(isha);

                    Log.d("TAG",fajr+dhuhr+asr+maghrib+isha);

                } catch (JSONException e) {
                    Log.d("TAG", e.toString());
                    e.printStackTrace();
                }

            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.d("TAG",error.getMessage());
                        Toast.makeText(context, error.getMessage().toString() ,Toast.LENGTH_LONG).show();
                    }
                });

        rq.add(request);

    }

}
