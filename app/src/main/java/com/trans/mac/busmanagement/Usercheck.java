package com.trans.mac.busmanagement;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class Usercheck extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    RadioButton Bus, Passenger;
    Button Next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usercheck);

        sharedPreferences = getSharedPreferences("usertype_info", 0);

        Passenger = findViewById(R.id.passenger);
        Bus = findViewById(R.id.bus);

        Next = findViewById(R.id.next);

    Next.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {


            if (Passenger.isChecked()) {
                sharedPreferences.edit().putBoolean("Passenger", true).commit();


                startActivity(new Intent(Usercheck.this, passenger_register.class));
                finish();

            } else {
                startActivity(new Intent(Usercheck.this, Bus_register.class));
                finish();
            }


        }


    });

}
    }

