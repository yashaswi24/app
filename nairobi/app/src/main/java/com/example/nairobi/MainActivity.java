package com.example.nairobi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import org.json.JSONObject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity implements Callback<Login> {

    EditText id1, id2;
    TextView tv;
    Button btn;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        id1 = findViewById(R.id.username);
        id2 = findViewById(R.id.userpass);
        btn = findViewById(R.id.btn);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = MainActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.statusbar));
        //btn.setBackgroundColor(getResources().getColor(R.color.green));

        tv = findViewById(R.id.text_view_result);
    }
    public void getData(View view) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        try {
            JSONObject paramObject = new JSONObject();
            paramObject.put("username", id1.getText().toString());
            paramObject.put("password", id2.getText().toString());

           Call<Login> userCall = jsonPlaceHolderApi.getUser(paramObject.toString());

            userCall.enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<Login> call, Response<Login> response) {
        Login log = response.body();

        if(log.getUsername().equalsIgnoreCase(id1.getText().toString()) && log.getPassword().equalsIgnoreCase(id2.getText().toString())) {

            tv.setText("success"+response.code());
            Intent i = new Intent(this, newlocationActivity.class);
            i.putExtra("user",log.getUsername());
            startActivity(i);
        }
        else {
            tv.append("INVALID USER");
        }
    }

    @Override
    public void onFailure(Call<Login> call, Throwable t) {
        tv.append("fl");
        tv.append(t.getMessage());
    }
}

