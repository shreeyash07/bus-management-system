package com.trans.mac.busmanagement;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class passenger_fragment2 extends Fragment {

    SharedPreferences sharedPreferences;
    Context context;

    TextView from, to;

    void setContext(Context context) {
        this.context = context;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.passenger_fragment2,null);


        sharedPreferences = context.getSharedPreferences("Booking", Context.MODE_PRIVATE);
        from = view.findViewById(R.id.from5);
        to = view.findViewById(R.id.to5);


        String fromV = sharedPreferences.getString("from", "");
        String toV = sharedPreferences.getString("to", "");


        from.setText(fromV);
        to.setText(toV);

        return view;
    }
}
