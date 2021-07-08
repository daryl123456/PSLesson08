package sg.edu.rp.c346.id19042545.pslesson08;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.content.PermissionChecker;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends AppCompatActivity {

    Button btnNorth,btnCentral,btnEast;
    private GoogleMap map;
    Spinner spinner;

    LatLng poi_Singapore = new LatLng(1.3521, 103.8198);
    LatLng poi_Admiralty = new LatLng(1.4593126056147607, 103.82843068169042);
    LatLng poi_Orchard = new LatLng(1.3049921159095283, 103.83221985537429);
    LatLng poi_Tampines = new LatLng(1.3489844631389207, 103.93582687808242);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fm = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment)
                fm.findFragmentById(R.id.map);

        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                //code to map it zoom in to that coori

                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Singapore,
                        15));
                map.moveCamera(CameraUpdateFactory.zoomTo(10));


                UiSettings ui = map.getUiSettings();
                //compass ui
                ui.setCompassEnabled(true);

                //zoom ui
                ui.setZoomControlsEnabled(true);

                int permissionCheck = ContextCompat.checkSelfPermission(MainActivity.this,
                        android.Manifest.permission.ACCESS_FINE_LOCATION);

                if (permissionCheck == PermissionChecker.PERMISSION_GRANTED) {
                    map.setMyLocationEnabled(true);
                } else {
                    Log.e("GMap - Permission", "GPS access has not been granted");
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 0);
                }
                    int height = 100;
                    int width = 100;
                    BitmapDrawable bitmapdraw = (BitmapDrawable)getResources().getDrawable(R.drawable.star);
                    Bitmap b = bitmapdraw.getBitmap();
                    Bitmap smallMarker = Bitmap.createScaledBitmap(b, width, height, false);
                Marker rp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Admiralty)
                        .title("North - HQ")
                        .snippet("C347 Android Programming II")
                        .icon(BitmapDescriptorFactory.fromBitmap(smallMarker)));
                Marker op = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Orchard)
                        .title("Central")
                        .snippet("Block 3A, Orchard Ave 3, 134542")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                Marker tp = map.addMarker(new
                        MarkerOptions()
                        .position(poi_Tampines)
                        .title("East")
                        .snippet("Block 555, Tampines Ave 3, 287788")
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE)));

                map.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        String markerName = marker.getTitle();
                        Toast.makeText(MainActivity.this, markerName, Toast.LENGTH_SHORT).show();
                        return false;
                    }
                });
            }
        });

        btnNorth = findViewById(R.id.btnNorth);
        btnCentral = findViewById(R.id.btnCentral);
        btnEast = findViewById(R.id.btnEast);
        spinner = findViewById(R.id.spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.location_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 1){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Admiralty,
                            15));
                }else if(i == 2){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Orchard,
                            15));
                }else if(i == 3){
                    map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Tampines,
                            15));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnNorth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Admiralty,
                        15));
            }
        });
        btnCentral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Orchard,
                        15));
            }
        });
        btnEast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                map.moveCamera(CameraUpdateFactory.newLatLngZoom(poi_Tampines,
                        15));
            }
        });
    }
}