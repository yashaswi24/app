package com.example.nairobi;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class visualizefinal extends AppCompatActivity {

    GraphView graph;
    HashMap<Double,Integer> hm=new HashMap<>();
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizefinal);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = visualizefinal.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(visualizefinal.this, R.color.statusbar));

        graph= findViewById(R.id.graphid);
        graph.setVisibility(View.VISIBLE);
        readData();
    }

    private List<csvsample> csvsamples=new ArrayList<>();
    private void readData() {
        InputStream is=getResources().openRawResource(R.raw.mycsv);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line;
        LineGraphSeries<DataPoint> s = new LineGraphSeries <>();
       ArrayList<Double>al=new ArrayList<>();
       ArrayList<Double>al2=new ArrayList<>();
        try {
            reader.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        try{
            while ((line=reader.readLine())!=null)
            {
                String[] tokens=line.split(",");
                csvsample csvs=new csvsample();
                csvs.setTimestamp(Double.parseDouble(tokens[0]));
                csvs.setV1((double) Math.round(Double.parseDouble(tokens[1])));
                csvs.setV2((double) Math.round(Double.parseDouble(tokens[2])));
                csvs.setV3((double) Math.round(Double.parseDouble(tokens[3])));
                csvs.setE1((double) Math.round(Double.parseDouble(tokens[4])));
                csvs.setE2((double) Math.round(Double.parseDouble(tokens[5])));
                csvs.setE3((double) Math.round(Double.parseDouble(tokens[6])));

               al.add((double) Math.round(Double.parseDouble(tokens[1])));
               al2.add((double) Math.round(Double.parseDouble(tokens[4])));
                csvsamples.add(csvs);



               // DataPoint d=new DataPoint(Long.parseLong(csvs.getV1()),Long.parseLong(csvs.getE1()));
                  /*  LineGraphSeries<DataPoint> series = new LineGraphSeries <> (new DataPoint[] {
                            new DataPoint(Long.parseLong(csvs.getV1()),Long.parseLong(csvs.getE1())),

                            new DataPoint(Long.parseLong(csvs.getV2()),Long.parseLong(csvs.getE2())),

                            new DataPoint(Long.parseLong(csvs.getV3()),Long.parseLong(csvs.getE3())),

                              });*/



                Log.d("Activity","just created"+csvs);
            }
            DataPoint[] dp = new DataPoint[al.size()];
            for (int i = 0; i < al.size(); i++)
            {
                dp[i] = new DataPoint(al.get(i), al2.get(i));
            }

            s= new LineGraphSeries <> ( dp);
            graph.addSeries(s);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setMinX(200);
            graph.getViewport().setMaxX(300);
            graph.getViewport().setMinY(0);
            graph.getViewport().setMaxY(5500);


            graph.getViewport().setScrollable(true);
            graph.getViewport().setScrollableY(true);
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(visualizefinal.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }
}
