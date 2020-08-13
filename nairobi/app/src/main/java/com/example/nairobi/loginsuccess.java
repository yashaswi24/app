package com.example.nairobi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;

public class loginsuccess extends AppCompatActivity {

    TextView tv;
    String value;
    Button btn;
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    Menu menu;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loginsuccess);
        tv=findViewById(R.id.tvtext);
        btn=findViewById(R.id.ambtn);
        tv.setText("LOGIN SUCCESS");
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
             value = extras.getString("user");
            tv.append("\n welcome  "+value);
        }

    }
    public void getData2(View view) {

        Intent i=new Intent(this,dataActivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }

    public void getData3(View view) {
        Intent i=new Intent(this,locationActivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }
    public void getData4(View view) {
        Intent i=new Intent(this,allmeteractivity.class);
        i.putExtra("user",value);
        startActivity(i);
    }
    public void getData5(View view) {
        Intent i=new Intent(this,visualizefinal.class);
        startActivity(i);
    }
    public void getData6(View view) {
        Intent i=new Intent(this,visualizechannel.class);
        startActivity(i);
    }
}



