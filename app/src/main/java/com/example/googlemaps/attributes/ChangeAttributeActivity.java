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
import com.example.googlemaps.database.Attribute;
import com.example.googlemaps.database.ConnectionsDatabaseHelper;
import com.google.android.material.snackbar.Snackbar;

public class ChangeAttributeActivity extends AppCompatActivity {

    private final String TAG = "ChangeAttributeActivity";
    private ConnectionsDatabaseHelper cDbHelper;
    private EditText attributeValue;
    private int id; // the attribute id

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_attribute);

        cDbHelper = new ConnectionsDatabaseHelper(this);
        initializeButtons();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            id = extras.getInt("id");
            Log.i(TAG, "attribute with id "+id);
            Attribute att = cDbHelper.getAttribute(id);
            if(att!=null) {
                Log.i(TAG, "" + att.getName());
                attributeValue = (EditText)findViewById(R.id.changeAttributeText);
                attributeValue.setText(att.getName());
            }
        }
    }

    public void initializeButtons(){
        Button back = (Button)findViewById(R.id.changeAttributeBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToAllAttributes();
            }
        });

        Button delete = (Button)findViewById(R.id.changeAttributeDelete);
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cDbHelper.deleteAttribute(id);
                cDbHelper.deleteConnectionBasedOnAttribute(id);
                goToAllAttributes();
            }
        });

        Button change = (Button)findViewById(R.id.changeAttributeChange);
        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    String newValue = attributeValue.getText().toString();
                    if( !newValue.isEmpty() ){
                        cDbHelper.updateAttribute(id, newValue);
                        Toast.makeText(getApplicationContext(),"Updated attribute with value "+newValue, Toast.LENGTH_SHORT).show();
                        goToAllAttributes();
                    }
            }
        });
    }

    public void goToAllAttributes(){
        Intent intent = new Intent(getApplicationContext(), AllAttributesActivity.class);
        startActivity(intent);
    }

}
