package com.example.nairobi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class visualizeActivity extends AppCompatActivity implements Callback<Visualize> {

    GraphView graph;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualize);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = visualizeActivity.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(visualizeActivity.this, R.color.statusbar));

        graph= findViewById(R.id.graphid);
        graph.setVisibility(View.VISIBLE);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(JsonPlaceHolderApi.URL_BASE)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        try {
            Call<Visualize> userCall = jsonPlaceHolderApi.getVis();
            userCall.enqueue(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onResponse(Call<Visualize> call, Response<Visualize> response) {

        Visualize log=response.body();

        try {
            LineGraphSeries<DataPoint> series = new LineGraphSeries <> (new DataPoint[] {
                     new DataPoint(0,-1),
                    new DataPoint(Integer.parseInt(log.getDate1().substring(0,2)), Integer.parseInt(log.getRead1())),
                    new DataPoint(Integer.parseInt(log.getDate2().substring(0,2)), Integer.parseInt(log.getRead2()))
            });
            graph.addSeries(series);
        } catch (IllegalArgumentException e) {
            Toast.makeText(visualizeActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onFailure(Call<Visualize> call, Throwable t) {

    }
}
