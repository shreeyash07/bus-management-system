package com.trans.mac.busmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class passenger_register2 extends AppCompatActivity {

    Button Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_passenger_register2);

        Register = findViewById(R.id.register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(passenger_register2.this, MainActivity.class));
                finish();

                Toast.makeText(passenger_register2.this, "User Registered", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
