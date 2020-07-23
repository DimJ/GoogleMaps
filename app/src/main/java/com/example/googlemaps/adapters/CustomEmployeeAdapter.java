package com.example.googlemaps.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.googlemaps.R;
import com.example.googlemaps.database.Employee;
import java.util.ArrayList;

public class CustomEmployeeAdapter extends ArrayAdapter<Employee> {
    private ArrayList<Employee> dataSet;
    private Context mContext;

    public CustomEmployeeAdapter(ArrayList<Employee> data, Context context) {
        super(context, R.layout.employee_item, data);
        this.dataSet = data;
        this.mContext=context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Employee employee = getItem(position);
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.employee_item, parent, false);

        TextView employeeId = (TextView) convertView.findViewById(R.id.employee_id);
        TextView employeeName = (TextView) convertView.findViewById(R.id.employee_name);
        TextView employeeBirthDate = (TextView) convertView.findViewById(R.id.employee_dateOfBirth);
        TextView employeeHasCar = (TextView) convertView.findViewById(R.id.employee_hasCar);
        TextView employeeAddress = (TextView) convertView.findViewById(R.id.employee_address);

        employeeId.setText( (""+employee.getId()) );
        employeeName.setText(employee.getName());
        employeeBirthDate.setText(employee.getDateOfBirth());
        String hasCar = "has car: ";
        if(employee.getHasCar()==1)
            hasCar += "yes";
        else
            hasCar += "no";
        employeeHasCar.setText(hasCar);
        employeeAddress.setText(employee.getAddress());

        return convertView;
    }
}
