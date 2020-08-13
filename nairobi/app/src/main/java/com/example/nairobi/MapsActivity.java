package com.example.nairobi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.RequiresApi;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String city,lat,lon,mark;
    float latv,lonv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = MapsActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MapsActivity.this, R.color.statusbar));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            city = extras.getString("city");
            lat = extras.getString("lat");
            lon = extras.getString("lon");
            mark=extras.getString("mark");
        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        latv=Float.parseFloat(lat);
        lonv=Float.parseFloat(lon);
        LatLng jntuh = new LatLng(latv, lonv);
        

        if(mark.equalsIgnoreCase("yes"))
        mMap.addMarker(new MarkerOptions().position(jntuh).title("jntuh").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        else
            mMap.addMarker(new MarkerOptions().position(jntuh).title("jntuh").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(jntuh));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(11));
    }
}
