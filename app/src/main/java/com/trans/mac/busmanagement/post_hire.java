package com.trans.mac.busmanagement;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class post_hire extends AppCompatActivity {


    Button post;

    AQuery aQuery;

    String murl ;
    EditText from, to,days,type;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_hire);

        post = findViewById(R.id.post2);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        days = findViewById(R.id.duration);
        type = findViewById(R.id.type);


        aQuery =  new AQuery(this);

        murl = "http://edushare.asia/bbb/public/";

        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                String fromV = from.getText().toString().trim();

                String toV = to.getText().toString().trim();


                String daysV = days.getText().toString().trim();

                String typeV = type.getText().toString().trim();



                Map<String, Object> params = new HashMap<String, Object>();
                params.put("from", fromV);
                params.put("to", toV);
                params.put("days", daysV);

                params.put("type", typeV);


                aQuery.ajax(murl + "hire", params, JSONObject.class, new AjaxCallback<JSONObject>() {

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
                                        Toast.makeText(post_hire.this, json.getString(key), Toast.LENGTH_SHORT).show();
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

                                Toast.makeText(post_hire.this, message, Toast.LENGTH_SHORT).show();



//                                    sharedPreferences.getString("token", "");


                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                    }
                });




            }
        });
    }
}
