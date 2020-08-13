package com.example.nairobi;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
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

public class allmeteractivity extends AppCompatActivity implements Callback<allmeter> {

    TextView tv;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allmeteractivity);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = allmeteractivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(allmeteractivity.this, R.color.statusbar));

        tv=findViewById(R.id.amtv);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        try {

            Call<allmeter> userCall = jsonPlaceHolderApi.getallmeterdata();
            userCall.enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<allmeter> call, Response<allmeter> response) {
        tv.append("success"+response.code());
        allmeter log=response.body();
        String content = "";
        content += "Code: " + response.code() + "\n";
        content += "Meter : " + log.get1() + " Status : "+ log.get5()+" \n\n";
        content += "Meter : " + log.get2() + " Status : "+ log.get6() +"\n\n";
        content += "Meter : " + log.get3() + " Status : "+ log.get7() +"\n\n";
        content += "Meter : " + log.get4() + " Status : "+ log.get8()+ "\n\n";

        tv.setText(content);
        Intent i=new Intent(this,allmapsActivity.class);
        i.putExtra("s1",log.get5());
        i.putExtra("s2",log.get6());
        i.putExtra("s3",log.get7());
        i.putExtra("s4",log.get8());
        i.putExtra("c1",log.get9());
        i.putExtra("c2",log.get10());
        i.putExtra("c3",log.get11());
        i.putExtra("c4",log.get12());

        i.putExtra("la1",log.get13());
        i.putExtra("la2",log.get14());
        i.putExtra("la3",log.get15());
        i.putExtra("la4",log.get16());
        i.putExtra("lo1",log.get17());
        i.putExtra("lo2",log.get18());
        i.putExtra("lo3",log.get19());
        i.putExtra("lo4",log.get20());
        startActivity(i);
    }

    @Override
    public void onFailure(Call<allmeter> call, Throwable t) {
        tv.append("fl");
        tv.append(t.getMessage());
    }
}
