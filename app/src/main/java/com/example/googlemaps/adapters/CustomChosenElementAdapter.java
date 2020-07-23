package com.example.googlemaps.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.example.googlemaps.R;
import com.example.googlemaps.database.Attribute;
import com.example.googlemaps.database.ConnectionsDatabaseHelper;
import com.example.googlemaps.database.Employee;
import java.util.ArrayList;

public class CustomChosenElementAdapter extends ArrayAdapter<Attribute> {
    private ArrayList<Attribute> dataSet;
    private Context mContext;
    private Employee employee;
    private ConnectionsDatabaseHelper cDbHelper;

    public CustomChosenElementAdapter(ArrayList<Attribute> data, Context context, Employee employee, ConnectionsDatabaseHelper cDbHelper) {
        super(context, R.layout.chosen_attribute_item, data);
        this.dataSet = data;
        this.mContext=context;
        this.employee = employee;
        this.cDbHelper = cDbHelper;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Attribute attribute = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.chosen_attribute_item, parent, false);

        final LinearLayout background = (LinearLayout)convertView.findViewById(R.id.chosen_attribute_layout);
        TextView chosenAttributeId = (TextView) convertView.findViewById(R.id.chosen_attribute_id);
        TextView chosenAttributeName = (TextView) convertView.findViewById(R.id.chosen_attribute_name);

        if( cDbHelper.hasForConnection(employee.getId(), attribute.getId()) )
            background.setBackgroundColor(Color.RED);
        else
            background.setBackgroundColor(Color.WHITE);

        chosenAttributeId.setText( (""+attribute.getId()) );
        chosenAttributeName.setText(attribute.getName());
        chosenAttributeName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( !cDbHelper.hasForConnection(employee.getId(), attribute.getId()) ) {
                    cDbHelper.insertConnection(employee.getId(), attribute.getId());
                    background.setBackgroundColor(Color.RED);
                }
                else{
                    cDbHelper.deleteConnection(employee.getId(), attribute.getId());
                    background.setBackgroundColor(Color.WHITE);
                }
            }
        });

        return convertView;
    }
}
