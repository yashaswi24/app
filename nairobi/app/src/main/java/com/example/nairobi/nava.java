package com.example.nairobi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

import java.util.Map;

public class nava extends AppCompatActivity {

    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    String value="user";
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nava);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = nava.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(nava.this, R.color.statusbar));

        dl = (DrawerLayout)findViewById(R.id.drawerlayout);
        t = new ActionBarDrawerToggle(this, dl,R.string.open, R.string.close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull  MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.a1:
                    {
                        Toast.makeText(nava.this, "Data",Toast.LENGTH_SHORT).show();
                        getData2();
                        break;
                    }
                    case R.id.a2:
                    {
                        Toast.makeText(nava.this, "Location",Toast.LENGTH_SHORT).show();
                        getData3();
                        break;
                    }
                    case R.id.a3:
                    {
                        Toast.makeText(nava.this, "All meter Data",Toast.LENGTH_SHORT).show();
                        getData4();
                        break;
                    }
                    case R.id.a4:
                    {
                        Toast.makeText(nava.this, "Usage Pattern",Toast.LENGTH_SHORT).show();
                        getData5();
                        break;
                    }
                    case R.id.a5:
                    {
                        Toast.makeText(nava.this, "Visualize Channel",Toast.LENGTH_SHORT).show();
                        getData6();
                        break;
                    }

                    default:
                        return true;
                }


                return true;

            }
        });


    }

    public void getData2() {

        Intent i=new Intent(this,dataActivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }

    public void getData3() {
        Intent i=new Intent(this,locationActivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }
    public void getData4() {
        Intent i=new Intent(this,allmeteractivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }
    public void getData5() {
        Intent i=new Intent(this,visualizefinal.class);
        startActivity(i);
    }
    public void getData6() {
        Intent i=new Intent(this,visualizechannel.class);
        startActivity(i);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}