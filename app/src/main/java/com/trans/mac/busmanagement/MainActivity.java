package com.trans.mac.busmanagement;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class MainActivity extends AppCompatActivity {

    EditText username, password;
    TextView Register;
    Button Login;
    AQuery aQuery;
    String murl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        murl = "http://edushare.asia/bbb/public/";

        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        Register = findViewById(R.id.register);
        Login = findViewById(R.id.login);

        aQuery = new AQuery(this);


        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Usercheck.class));


            }
        });

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String, Object> params = new HashMap<String, Object>();

                String emailV = username.getText().toString().trim();
                String passwordV = password.getText().toString().trim();


                params.put("email", emailV);
                params.put("password", passwordV);
                aQuery.ajax(murl + "login", params, JSONObject.class, new AjaxCallback<JSONObject>() {

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
                                    Toast.makeText(MainActivity.this, json.getString(key), Toast.LENGTH_SHORT).show();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            String token = json.getString("token");
                            String message = json.getString("message");
                            int userType =  Integer.parseInt(json.getString("type"));
                            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
                            SharedPreferences sharedPreferences = getSharedPreferences("bb", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("token", token);
                            editor.commit();

                            if(userType==1){
//                                Toast.makeText(MainActivity.this, "I am user", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Passenger_Activity.class));
                               finish();
                            }else{
//                                Toast.makeText(MainActivity.this, "I am bus malik", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(MainActivity.this, Bus_Activity.class));
                                finish();
                            }

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
