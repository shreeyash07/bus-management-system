package com.trans.mac.busmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Bus_Activity extends AppCompatActivity {


    AQuery aQuery;

    String murl ;
    EditText from,to,time,price,date,month,year;
    TextView logout,tohire ;

    Button post;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus);


        aQuery =  new AQuery(this);

        murl = "http://edushare.asia/bbb/public/";

        post = findViewById(R.id.post);
        logout = findViewById(R.id.logout2);
        tohire = findViewById(R.id.forhire);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        time = findViewById(R.id.time);
        price = findViewById(R.id.price);
        date = findViewById(R.id.date);
        month = findViewById(R.id.month);
        year = findViewById(R.id.year);

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


        String fromV = from.getText().toString().trim();

        String toV = to.getText().toString().trim();
        String timeV = time.getText().toString().trim();
        String priceV = price.getText().toString().trim();
        String dateV = date.getText().toString().trim();
        String monthV = month.getText().toString().trim();
        String yearV = year.getText().toString().trim();




        Map<String, Object> params = new HashMap<String, Object>();
        params.put("from", fromV);
                params.put("to", toV);
        params.put("time", timeV);
        params.put("price", priceV);
        //params.put("phone_number", phone_number);
        //params.put("address", addressV);
        params.put("date", dateV);
        params.put("month", monthV);
        params.put("year", yearV);

        aQuery.ajax(murl + "reserve", params, JSONObject.class, new AjaxCallback<JSONObject>() {

            @Override
            public void callback(String url, JSONObject json, AjaxStatus status) {
                Log.d("testig", Integer.toString(status.getCode()));

                if (status.getCode() > 300){
                    try {
                        json = new JSONObject(status.getError());

                        Iterator<String> keys = json.keys();

                        while (keys.hasNext()) {
                            String key = keys.next();
                            try {
                                Toast.makeText(Bus_Activity.this, json.getString(key), Toast.LENGTH_SHORT).show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
//                                    String token = json.getString("token");
                        String message = json.getString("message");

                        Toast.makeText(Bus_Activity.this, message, Toast.LENGTH_SHORT).show();



//                                    sharedPreferences.getString("token", "");


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bus_Activity.this,MainActivity.class));
                finish();
            }
        });

        tohire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Bus_Activity.this,for_hire_activity.class));

            }
        });






    }
}
