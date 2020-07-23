package com.example.googlemaps.attributes;
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
import com.example.googlemaps.adapters.CustomAttributeAdapter;
import com.example.googlemaps.database.Attribute;
import com.example.googlemaps.database.DatabaseHelper;
import java.util.ArrayList;

public class AllAttributesActivity extends AppCompatActivity {

    private final String TAG = "AllAttributesActivity";
    private DatabaseHelper dbHelper;
    private ArrayList<Attribute> attributeModels;
    private ListView listView;
    private CustomAttributeAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_attrtibutes);

        dbHelper = new DatabaseHelper(this);
        initializeButtons();

        listView=(ListView)findViewById(R.id.attribute_list);
        attributeModels = dbHelper.getAllAttributes();
        adapter= new CustomAttributeAdapter(attributeModels,getApplicationContext());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Attribute dataModel= attributeModels.get(position);
                Bundle dataBundle = new Bundle();
                dataBundle.putInt( "id", dataModel.getId() );
                Intent intent = new Intent(getApplicationContext(), ChangeAttributeActivity.class);
                intent.putExtras(dataBundle);
                startActivity(intent);
            }
        });

    }

    public void initializeButtons(){
        Button back = (Button)findViewById(R.id.allAttributesBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        Button goToNewAttribute = (Button)findViewById(R.id.buttonToNewAttribute);
        goToNewAttribute.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NewAttributeActivity.class);
                startActivity(intent);
            }
        });
    }

}