package com.trans.mac.busmanagement;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class avaiable_passenger extends ArrayAdapter<HireInfo> {

    Context context;



    public avaiable_passenger( Context context, ArrayList<HireInfo>list)  {
        super(context, 0, list);
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.hire_item_layout, parent, false);

            final HireInfo info = getItem(position);

            TextView from3 = convertView.findViewById(R.id.from3);
            from3.setText(info.from);

            TextView to3 = convertView.findViewById(R.id.to3);
            to3.setText(info.to);

            TextView days = convertView.findViewById(R.id.days);
            days.setText(info.days);

            TextView type3 = convertView.findViewById(R.id.type3);
            type3.setText(info.type);



            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();

                }
            });
        }

        return convertView;



    }
}
