package com.example.googlemaps.maps;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.googlemaps.MainActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.adapters.CustomAttributeAdapter;
import com.example.googlemaps.adapters.CustomEmployeeAdapter;
import com.example.googlemaps.attributes.ChangeAttributeActivity;
import com.example.googlemaps.database.Attribute;
import com.example.googlemaps.database.ConnectionsDatabaseHelper;
import com.example.googlemaps.database.Employee;
import com.example.googlemaps.employees.ChangeEmployeeActivity;

import java.util.ArrayList;

public class SearchActivity extends AppCompatActivity {

    private final String TAG = "SearchActivity";
    private ConnectionsDatabaseHelper cDbHelper;
    private ArrayList<Attribute> attributeModels;
    private ListView attributesListView;
    private CustomAttributeAdapter attributesAdapter;
    private ArrayList <Employee> employeeModels;
    private ListView employeesListView;
    private CustomEmployeeAdapter employeesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        cDbHelper = new ConnectionsDatabaseHelper(this);

        Button back = (Button)findViewById(R.id.search_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        attributesListView=(ListView)findViewById(R.id.search_attributes_list);
        attributeModels = cDbHelper.getAllAttributes();
        attributesAdapter= new CustomAttributeAdapter(attributeModels,getApplicationContext());
        attributesListView.setAdapter(attributesAdapter);
        attributesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attribute dataModel= attributeModels.get(position);
                Log.i(TAG, dataModel.getName());
                employeeModels = cDbHelper.getEmployeesBasedOnAttributeId(dataModel.getId());
                for(int i=0; i<employeeModels.size(); i++){
                    Log.i(TAG, employeeModels.get(i).getName());
                    Log.i(TAG, employeeModels.get(i).getAddress());
                }
                initializeEmployeesList();
            }
        });

    }

    private void initializeEmployeesList()
    {
        employeesListView=(ListView)findViewById(R.id.search_employees_list);
        employeesAdapter= new CustomEmployeeAdapter(employeeModels,getApplicationContext());
        employeesListView.setAdapter(employeesAdapter);
        employeesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee dataModel= employeeModels.get(position);
                Log.i(TAG, "Employee with name "+dataModel.getName());
//                Bundle dataBundle = new Bundle();
//                dataBundle.putInt( "id", dataModel.getId() );
//                Intent intent = new Intent(getApplicationContext(), ChangeEmployeeActivity.class);
//                intent.putExtras(dataBundle);
//                startActivity(intent);
            }
        });
    }

}
