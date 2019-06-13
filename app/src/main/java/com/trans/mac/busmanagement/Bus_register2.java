package com.trans.mac.busmanagement;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Bus_register2 extends AppCompatActivity {

    Button Register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bus_register2);

        Register = findViewById(R.id.register);

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Bus_register2.this,MainActivity.class));

                Toast.makeText(Bus_register2.this, "User Registered", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
