package com.trans.mac.busmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class passenger_fragment1 extends Fragment {

    ListView listView;
    SharedPreferences sharedPreferences;

    TextView from;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.passenger_fragment1,null);

        ArrayList<BusInfo> arrayOfUsers = new ArrayList<BusInfo>();

        arrayOfUsers.add(new BusInfo("Kathmandu","dharan", "11:00", "1000", "22", "07", "2019"));
        arrayOfUsers.add(new BusInfo("Kalinchowk","Nagarkot", "11:00", "5000", "12", "09", "2019"));

// Create the adapter to convert the array to views
        avaiablebus adapter = new avaiablebus(getContext(), arrayOfUsers);
// Attach the adapter to a ListView
        ListView listView =  view.findViewById(R.id.listview);

        listView.setAdapter(adapter);
        return view;
    }

}
