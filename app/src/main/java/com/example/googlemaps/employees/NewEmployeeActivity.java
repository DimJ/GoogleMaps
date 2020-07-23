package com.example.googlemaps.employees;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.database.EmployeeDatabaseHelper;
import java.util.Calendar;

public class NewEmployeeActivity extends AppCompatActivity {

    private final String TAG = "NewEmployeeActivity";
    private EmployeeDatabaseHelper eDbHelper;
    private EditText name;
    private TextView birthDate;
    private Switch hasCar;
    private EditText address;
    private EditText latitude;
    private EditText longitude;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_employee);

        eDbHelper = new EmployeeDatabaseHelper(this);
        initializeElements();

    }

    public void initializeElements(){
        Button back = (Button)findViewById(R.id.newEmployeeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllEmployeesActivity.class);
                startActivity(intent);
            }
        });
        name = (EditText)findViewById(R.id.newEmployeeName);
        birthDate = (TextView)findViewById(R.id.newEmployeeBirthDate);
        Button birthDateButton = (Button)findViewById(R.id.newEmployeeShowCalendar);
        birthDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(NewEmployeeActivity.this,
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
        hasCar = (Switch)findViewById(R.id.newEmployeeHasCar);
        address = (EditText)findViewById(R.id.newEmployeeAddress);
        latitude = (EditText)findViewById(R.id.newEmployeeLatitude);
        longitude = (EditText)findViewById(R.id.newEmployeeLongitude);

        Button addEmployee = (Button)findViewById(R.id.newEmployeeAdd);
        addEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int code = eDbHelper.numberOfEmployees();
                String nameValue = name.getText().toString();
                String birthDateValue = birthDate.getText().toString();
                int hasCarValue = 0;
                if(hasCar.isChecked())
                    hasCarValue = 1;
                String addressValue = address.getText().toString();
                String latitudeValueString = latitude.getText().toString();
                String longitudeValueString = longitude.getText().toString();

                if( !nameValue.isEmpty() && !birthDateValue.isEmpty() && !addressValue.isEmpty() && !latitudeValueString.isEmpty() && !longitudeValueString.isEmpty() ) {
                    double latitudeValue = Double.parseDouble(latitudeValueString);
                    double longitudeValue = Double.parseDouble(longitudeValueString);
                    eDbHelper.insertEmployee((code + 1), nameValue, birthDateValue, hasCarValue, addressValue, latitudeValue, longitudeValue);
                    Toast.makeText(getApplicationContext(), "New Employee with ID: " + (code + 1), Toast.LENGTH_SHORT).show();
                }
                else
                    Toast.makeText(getApplicationContext(), "Please fill all the fields ", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
