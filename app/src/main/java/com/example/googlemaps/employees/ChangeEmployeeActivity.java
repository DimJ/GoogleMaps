package com.example.googlemaps.employees;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.adapters.CustomChosenElementAdapter;
import com.example.googlemaps.database.Attribute;
import com.example.googlemaps.database.ConnectionsDatabaseHelper;
import com.example.googlemaps.database.Employee;
import java.util.ArrayList;
import java.util.Calendar;

public class ChangeEmployeeActivity extends AppCompatActivity {

    private final String TAG = "ChangeEmployeeActivity";
    private ConnectionsDatabaseHelper cDbHelper;
    private EditText name;
    private TextView birthDate;
    private Switch hasCar;
    private EditText address;
    private EditText latitude;
    private EditText longitude;
    private Employee employee;
    private int id;
    private ArrayList<Attribute> attributeModels;
    private ListView listView;
    private CustomChosenElementAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_employee);

        cDbHelper = new ConnectionsDatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            id = extras.getInt("id");
            Log.i(TAG, "employee with id "+id);
            employee = cDbHelper.getEmployee(id);
            initializeElements();

            listView=(ListView)findViewById(R.id.chosen_attribute_list);
            attributeModels = cDbHelper.getAllAttributes();
            adapter= new CustomChosenElementAdapter(attributeModels,getApplicationContext(), employee, cDbHelper);
            listView.setAdapter(adapter);

        }

    }

    public void initializeElements(){
        Button back = (Button)findViewById(R.id.changeEmployeeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAllEmployees();
            }
        });
        name = (EditText)findViewById(R.id.changeEmployeeName);
        name.setText(employee.getName());

        birthDate = (TextView)findViewById(R.id.changeEmployeeBirthDate);
        birthDate.setText(employee.getDateOfBirth());

        Button birthDateButton  = (Button)findViewById(R.id.changeEmployeeShowCalendar);
        birthDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(ChangeEmployeeActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                birthDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();

            }
        });

        hasCar = (Switch)findViewById(R.id.changeEmployeeHasCar);
        hasCar.setChecked(false);
        if(employee.getHasCar()==1)
            hasCar.setChecked(true);

        address = (EditText)findViewById(R.id.changeEmployeeAddress);
        address.setText(employee.getAddress());

        latitude = (EditText)findViewById(R.id.changeEmployeeLatitude);
        latitude.setText((employee.getLatitude()+""));

        longitude = (EditText)findViewById(R.id.changeEmployeeLongitude);
        longitude.setText((employee.getLongitude()+""));

        Button delete = (Button)findViewById(R.id.changeEmployeeDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cDbHelper.deleteEmployee(id);
                cDbHelper.deleteConnectionBasedOnEmployee(id);
                goToAllEmployees();
            }
        });

        Button addEmployee = (Button)findViewById(R.id.changeEmployeeChange);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String birthDateValue = birthDate.getText().toString();
                int hasCarValue = 0;
                if(hasCar.isChecked())
                    hasCarValue = 1;
                String addressValue = address.getText().toString();
                double latitudeValue = Double.parseDouble(latitude.getText().toString());
                double longitudeValue = Double.parseDouble(longitude.getText().toString());

                cDbHelper.updateEmployee(employee.getId(), nameValue, birthDateValue, hasCarValue, addressValue, latitudeValue, longitudeValue);
                Toast.makeText(getApplicationContext()," Updated Employee ", Toast.LENGTH_SHORT).show();
                goToAllEmployees();
            }
        });
    }

    public void goToAllEmployees(){
        Intent intent = new Intent(getApplicationContext(), AllEmployeesActivity.class);
        startActivity(intent);
    }

}
