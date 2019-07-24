package com.trans.mac.busmanagement;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class avaiablebus extends ArrayAdapter<BusInfo> {


    Context context;
    SharedPreferences sharedPreferences;

    public avaiablebus(Context context, ArrayList <BusInfo> list) {
        super(context, 0, list);
        this.context = context;
    }


    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_layout, parent, false);

            final BusInfo info = getItem(position);

            final TextView from2 = convertView.findViewById(R.id.from2);
            from2.setText(info.from);

            final TextView to2 = convertView.findViewById(R.id.to2);
            to2.setText(info.to);

            TextView time2 = convertView.findViewById(R.id.time1);
            time2.setText(info.time);

            TextView price2 = convertView.findViewById(R.id.price2);
            price2.setText(info.price);

            TextView date2 = convertView.findViewById(R.id.date2);
            date2.setText(info.date);

            TextView month2 = convertView.findViewById(R.id.month2);
            month2.setText(info.month);

            TextView year2 = convertView.findViewById(R.id.year2);
            year2.setText(info.year);

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(context, "ok", Toast.LENGTH_SHORT).show();
                    sharedPreferences = context.getSharedPreferences("Booking", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    String fromV = from2.getText().toString();
                    String toV = to2.getText().toString();

                    editor.putString("from", fromV);
                    editor.putString("to", toV);
                    editor.commit();

                }
            });
        }

        return convertView;



    }
}
