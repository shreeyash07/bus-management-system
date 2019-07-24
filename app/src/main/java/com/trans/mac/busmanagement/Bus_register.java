package com.trans.mac.busmanagement;

import android.content.DialogInterface;
import android.content.Intent;
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

public class Bus_register extends AppCompatActivity {

    Button register;

    AQuery aQuery;

    String murl ;
    EditText email, password,address, name,busno, license,billbook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_register);

        aQuery =  new AQuery(this);

        murl = "http://edushare.asia/bbb/public/";

        register = findViewById(R.id.register);
        email = findViewById(R.id.email2);
        name = findViewById(R.id.billbookh);
        password = findViewById(R.id.password2);
        busno = findViewById(R.id.busno);
        license = findViewById(R.id.licenseno);
        billbook = findViewById(R.id.billbook);


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (valid()){

                    String nameV2 = name.getText().toString().trim();

                    String passwordV2 = password.getText().toString().trim();


                    String emailV2 = email.getText().toString().trim();

                    //String phone_number = phno.getText().toString().trim();

                    //String addressV = address.getText().toString().trim();
                    String busnoV = busno.getText().toString().trim();
                    String billbookV = billbook.getText().toString().trim();
                    String licenseV = license.getText().toString().trim();


                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put("email", emailV2);
                    params.put("password", passwordV2);
                    params.put("name", nameV2);
                    //params.put("phone_number", phone_number);
                    //params.put("address", addressV);
                    params.put("bus_no", busnoV);
                    params.put("bill_book_no", billbookV);
                    params.put("liscense_no", licenseV);

                    aQuery.ajax(murl + "register2", params, JSONObject.class, new AjaxCallback<JSONObject>() {

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
                                            Toast.makeText(Bus_register.this, json.getString(key), Toast.LENGTH_SHORT).show();
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

                                    Toast.makeText(Bus_register.this, message, Toast.LENGTH_SHORT).show();



//                                    sharedPreferences.getString("token", "");

                                    startActivity(new Intent(Bus_register.this, Bus_Activity.class));
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

        String nameV2 = name.getText().toString().trim();

        String passwordV2 = password.getText().toString().trim();


        String emailV2 = email.getText().toString().trim();

        //String phone_number = phno.getText().toString().trim();

        //String addressV = address.getText().toString().trim();

        if (nameV2.isEmpty() &&  passwordV2.isEmpty() &&  emailV2.isEmpty()) {
            Toast.makeText(Bus_register.this, "Please enter all details", Toast.LENGTH_SHORT).show();

        }  else{

            return true;

        }
        return result;
    }
            }

