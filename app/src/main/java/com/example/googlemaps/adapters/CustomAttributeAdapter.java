package com.example.googlemaps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.googlemaps.R;
import com.example.googlemaps.database.Attribute;

import java.util.ArrayList;

public class CustomAttributeAdapter extends ArrayAdapter<Attribute>  {
    private ArrayList<Attribute> dataSet;
    private Context mContext;

    public CustomAttributeAdapter(ArrayList<Attribute> data, Context context) {
        super(context, R.layout.attribute_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Attribute Attribute = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.attribute_item, parent, false);

        TextView attributeId = (TextView) convertView.findViewById(R.id.attribute_id);
        TextView attributeName = (TextView) convertView.findViewById(R.id.attribute_name);
        attributeId.setText( (""+Attribute.getId()) );
        attributeName.setText(Attribute.getName());

        return convertView;
    }
}
