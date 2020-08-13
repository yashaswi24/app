package com.example.nairobi;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class locationActivity extends AppCompatActivity implements Callback<loc> {

    TextView tv;
    String value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);
        tv=findViewById(R.id.tv);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            value = extras.getString("user");
            tv.append("\n welcome  "+value);

        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        try {
            Call<loc> userCall = jsonPlaceHolderApi.getLoc(value);
            userCall.enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   @Override
    public void onResponse(Call<loc> call, Response<loc> response) {
        tv.append("success"+response.code());
        loc log = response.body();


        String content = "";
        content += "Code : " + response.code() + "\n";
        content += "City : " + log.getLoc() + "\n\n";
       content += "Latitude : " + log.getLat() + "\n\n";
       content += "Longitude : " + log.getLon() + "\n\n";
       content += "Mark : " + log.getMark() + "\n\n";
        tv.setText(content);
       Intent i=new Intent(this,MapsActivity.class);
        i.putExtra("city",log.getLoc());
        i.putExtra("lat",log.getLat());
        i.putExtra("lon",log.getLon());
       i.putExtra("mark",log.getMark());
       startActivity(i);
    }

    @Override
    public void onFailure(Call<loc> call, Throwable t) {
        tv.append("fl");
        tv.append(t.getMessage());
    }
}
