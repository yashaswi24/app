package com.example.nairobi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class dataActivity extends AppCompatActivity implements Callback<data>{

    TextView tv;
    Button btn;
    String value;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = dataActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(dataActivity.this, R.color.statusbar));

        tv=findViewById(R.id.tvdisplay);
        btn=findViewById(R.id.ambtn);
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
            Call<data> userCall = jsonPlaceHolderApi.getData(value);
            userCall.enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<data> call, Response<data> response) {
        tv.append("success"+response.code());
        data log = response.body();

        String content = "";
        content += "Code: " + response.code() + "\n";
        content += "load profile id: " + log.getLoadprofileid() + "\n\n";
       content +=  "channel id: " + log.getChannelid() + "\n\n";
        content +=  "date time: " + log.getDatetime() + "\n\n";
        content +=  "consumption: " + log.getConsumptionreading() + "\n\n";
        tv.setText(content);
    }

    @Override
    public void onFailure(Call<data> call, Throwable t) {
        tv.append("fl");
        tv.append(t.getMessage());
    }
}
