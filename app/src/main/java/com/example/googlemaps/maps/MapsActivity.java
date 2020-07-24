package com.example.googlemaps.maps;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.googlemaps.MainActivity;
import com.example.googlemaps.R;
import com.example.googlemaps.database.ConnectionsDatabaseHelper;
import com.example.googlemaps.database.Employee;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private final String TAG = "MapsActivity";
    private GoogleMap mMap;
    private ConnectionsDatabaseHelper cDbHelper;
    private ArrayList<Employee> employees;
    private int employeeId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        cDbHelper = new ConnectionsDatabaseHelper(this);

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            int attributeId = extras.getInt("attributeId");
            employeeId = extras.getInt("employeeId");
            Log.i(TAG, "attribute with id "+attributeId);
            Log.i(TAG, "employee with id "+employeeId);
            employees = cDbHelper.getEmployeesBasedOnAttributeId(attributeId);

        }

        Button back = (Button)findViewById(R.id.map_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));

        LatLng coordinates = null;
        for(int i=0; i<employees.size(); i++){
            coordinates = new LatLng(employees.get(i).getLatitude(), employees.get(i).getLongitude());
            MarkerOptions marker = new MarkerOptions();
            marker.position(coordinates);
            marker.title(employees.get(i).getName());
            if( employeeId == employees.get(i).getId() )
                marker.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE));
            mMap.addMarker(marker);
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLng(coordinates));



    }
}
