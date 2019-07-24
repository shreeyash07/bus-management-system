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
import android.widget.RadioButton;
import android.widget.Toast;

import com.androidquery.AQuery;
import com.androidquery.callback.AjaxCallback;
import com.androidquery.callback.AjaxStatus;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class passenger_register extends AppCompatActivity {

    Button Next;

    AQuery aQuery;

    String murl ;
    EditText email, password,address, name;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register);
        aQuery =  new AQuery(this);

        murl = "http://edushare.asia/bbbpublic/";

        Next = findViewById(R.id.next);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        address = findViewById(R.id.address);

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (valid()){

                    String nameV = name.getText().toString().trim();

                    String passwordV = password.getText().toString().trim();


                    String emailV = email.getText().toString().trim();

                    //String phone_number = phno.getText().toString().trim();

                    String addressV = address.getText().toString().trim();


                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("email", emailV);
                    params.put("password", passwordV);
                    params.put("name", nameV);
                    //params.put("phone_number", phone_number);
                    params.put("address", addressV);

                    aQuery.ajax(murl + "register1", params, JSONObject.class, new AjaxCallback<JSONObject>() {

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
                                            Toast.makeText(passenger_register.this, json.getString(key), Toast.LENGTH_SHORT).show();
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
                                    Toast.makeText(passenger_register.this, message, Toast.LENGTH_SHORT).show();



//                                    sharedPreferences.getString("token", "");

                                    startActivity(new Intent(passenger_register.this, Passenger_Activity.class));
                                    finish();

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    });


                }


            }
        });
}

    private Boolean valid (){
        Boolean result = false;

        String nameV = name.getText().toString().trim();

        String passwordV = password.getText().toString().trim();


        String emailV = email.getText().toString().trim();

        //String phone_number = phno.getText().toString().trim();

        String addressV = address.getText().toString().trim();

        if (nameV.isEmpty() &&  passwordV.isEmpty() &&  emailV.isEmpty()&& addressV.isEmpty()) {
            Toast.makeText(passenger_register.this, "Please enter all details", Toast.LENGTH_SHORT).show();

        }  else{

            return true;

        }
        return result;
    }

}
