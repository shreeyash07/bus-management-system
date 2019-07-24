package com.trans.mac.busmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

import static com.androidquery.util.AQUtility.getContext;

public class for_hire_activity extends AppCompatActivity {

    AQuery aQuery;
    String murl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_for_hire_activity);

        murl = "http://edushare.asia/bbb/public/";
        aQuery = new AQuery(this);


        ArrayList<HireInfo> arrayOfUsers = new ArrayList<HireInfo>();


        arrayOfUsers.add(new HireInfo("Kathmandu","dharan", "1", "bus"));
        arrayOfUsers.add(new HireInfo("Kalinchowk","Nagarkot", "1", "bus"));

        avaiable_passenger adapter = new avaiable_passenger(for_hire_activity.this, arrayOfUsers);
        final ListView listView =  findViewById(R.id.listview2);

        listView.setAdapter(adapter);

        aQuery.ajax(murl + "hires",  JSONArray.class, new AjaxCallback<JSONArray>() {

            @Override
            public void callback(String url, JSONArray arr, AjaxStatus status) {
                Log.d("testig", Integer.toString(status.getCode()));

                ArrayList<HireInfo> arrayOfUsers = new ArrayList<HireInfo>();

                for (int i=0; i < arr.length(); i++) {
                    JSONObject json = null;
                    try {
                        json = arr.getJSONObject(i);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    if (status.getCode() > 300) {
                        try {
                            json = new JSONObject(status.getError());

                            Iterator<String> keys = json.keys();

                            while (keys.hasNext()) {
                                String key = keys.next();
                                try {
                                    Toast.makeText(for_hire_activity.this, json.getString(key), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            arrayOfUsers.add(new HireInfo(json.getString("from"),json.getString("to"), json.getString("days"), json.getString("type")));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }

                avaiable_passenger adapter = new avaiable_passenger(for_hire_activity.this, arrayOfUsers);

                listView.setAdapter(adapter);


            }
        });
    }

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        return super.onCreateView(name, context, attrs);
    }
}
