package com.example.googlemaps.attributes;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.database.DatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class NewAttributeActivity extends AppCompatActivity {

    private final String TAG = "NewAttributeActivity";
    private DatabaseHelper dbHelper;
    private EditText newAttributeValue;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_attribute);

        dbHelper = new DatabaseHelper(this);
        initializeButtons();
        newAttributeValue = (EditText)findViewById(R.id.newAttributeText);
    }

    public void initializeButtons(){
        Button addNewAttribute = (Button)findViewById(R.id.newAttributeAdd);
        addNewAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String newValue = newAttributeValue.getText().toString();
                if(!newValue.isEmpty()){
                    int attributes = dbHelper.numberOfAttributes();
                    dbHelper.insertAttribute( (attributes+1), newValue);
                    Toast.makeText(getApplicationContext(),"Added attribute with value "+newValue, Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button back = (Button)findViewById(R.id.newAttributeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), AllAttributesActivity.class);
                startActivity(intent);
            }
        });
    }

}
