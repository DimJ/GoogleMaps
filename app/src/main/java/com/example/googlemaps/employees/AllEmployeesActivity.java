package com.example.googlemaps.employees;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlemaps.MainActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.adapters.CustomEmployeeAdapter;
import com.example.googlemaps.database.Employee;
import com.example.googlemaps.database.EmployeeDatabaseHelper;

import java.util.ArrayList;

public class AllEmployeesActivity extends AppCompatActivity {

    private final String TAG = "AllEmployeesActivity";
    private EmployeeDatabaseHelper eDbHelper;
    private ArrayList<Employee> employeeModels;
    private ListView listView;
    private CustomEmployeeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_employees);

        eDbHelper = new EmployeeDatabaseHelper(this);
        initializeButtons();

        listView=(ListView)findViewById(R.id.employees_list);
        employeeModels = eDbHelper.getAllEmployees();
        adapter= new CustomEmployeeAdapter(employeeModels,getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Employee dataModel= employeeModels.get(position);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt( "id", dataModel.getId() );
                Intent intent = new Intent(getApplicationContext(), ChangeEmployeeActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });
    }

    public void initializeButtons(){
        Button back = (Button)findViewById(R.id.allEmployeesBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button toNewEmployee = (Button)findViewById(R.id.buttonToNewEmployee);
        toNewEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewEmployeeActivity.class);
                startActivity(intent);
            }
        });
    }
}
