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

public class allmapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String s1,s2,s3,s4;

    String la1,la2,la3,la4;
    String lo1,lo2,lo3,lo4;
    Float fla1,fla2,fla3,fla4;
    Float flo1,flo2,flo3,flo4;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allmaps);
        //getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = allmapsActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(allmapsActivity.this, R.color.statusbar));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            s1 = extras.getString("s1");
            s2 = extras.getString("s2");
            s3 = extras.getString("s3");
            s4=extras.getString("s4");
            la1=extras.getString("la1");la2=extras.getString("la2");
            la3=extras.getString("la3");la4=extras.getString("la4");
            lo1=extras.getString("lo1");lo2=extras.getString("lo2");
            lo3=extras.getString("lo3");lo4=extras.getString("lo4");
            fla1= Float.parseFloat(la1);fla2= Float.parseFloat(la2);fla3= Float.parseFloat(la3);fla4= Float.parseFloat(la4);
            flo1= Float.parseFloat(lo1);flo2= Float.parseFloat(lo2);flo3= Float.parseFloat(lo3);flo4= Float.parseFloat(lo4);

        }
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng l1 = new LatLng(fla1, flo1);
        LatLng l2 = new LatLng(fla2, flo2);
        LatLng l3 = new LatLng(fla3, flo3);
        LatLng l4 = new LatLng(fla4, flo4);


        if(s1.equalsIgnoreCase("yes"))
            mMap.addMarker(new MarkerOptions().position(l1).title("one").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        else
            mMap.addMarker(new MarkerOptions().position(l1).title("one").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        if(s2.equalsIgnoreCase("yes"))
            mMap.addMarker(new MarkerOptions().position(l2).title("two").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        else
            mMap.addMarker(new MarkerOptions().position(l2).title("two").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        if(s3.equalsIgnoreCase("yes"))
            mMap.addMarker(new MarkerOptions().position(l3).title("three").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        else
            mMap.addMarker(new MarkerOptions().position(l3).title("three").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));

        if(s4.equalsIgnoreCase("yes"))
            mMap.addMarker(new MarkerOptions().position(l4).title("four").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
        else
            mMap.addMarker(new MarkerOptions().position(l4).title("four").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));




        mMap.moveCamera(CameraUpdateFactory.newLatLng(l3));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(13));
    }
}
