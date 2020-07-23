package com.example.googlemaps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlemaps.attributes.AllAttributesActivity;
import com.example.googlemaps.database.DatabaseHelper;
import com.example.googlemaps.database.EmployeeDatabaseHelper;
import com.example.googlemaps.employees.AllEmployeesActivity;
import com.example.googlemaps.maps.MapsActivity;
import com.example.googlemaps.maps.SearchActivity;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";
    private DatabaseHelper dbHelper;
    private EmployeeDatabaseHelper eDbHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);

        dbHelper = new DatabaseHelper(this);
        eDbHelper = new EmployeeDatabaseHelper(this);
        initializeButtons();
    }

    private void initializeButtons(){
        Button ToAttributes = (Button)findViewById(R.id.buttonToAttributes);
        ToAttributes.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllAttributesActivity.class);
                startActivity(intent);
            }
        });

        Button ToEmployees = (Button)findViewById(R.id.buttonToEmployees);
        ToEmployees.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllEmployeesActivity.class);
                startActivity(intent);
            }
        });

        Button ToMap = (Button)findViewById(R.id.buttonToMap);
        ToMap.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });
    }

}
