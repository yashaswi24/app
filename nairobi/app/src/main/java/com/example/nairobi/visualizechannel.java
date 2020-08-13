package com.example.nairobi;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class visualizechannel extends AppCompatActivity {

    GraphView graph;
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizechannel);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#808080")));

        Window window = visualizechannel.this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(visualizechannel.this, R.color.statusbar));

        graph= findViewById(R.id.graphid);
        graph.setVisibility(View.VISIBLE);
        readData();
    }
    private List<csvsample> csvsamples=new ArrayList<>();
    private void readData() {
        InputStream is=getResources().openRawResource(R.raw.channel1);
        BufferedReader reader=new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
        String line;
        LineGraphSeries<DataPoint> s = new LineGraphSeries <>();
        ArrayList<Date> al=new ArrayList<>();
        ArrayList<Double>al2=new ArrayList<>();

        try{
            while ((line=reader.readLine())!=null)
            {
                String[] tokens=line.split(",");
                csvsample csvs=new csvsample();
                Timestamp ts=new Timestamp(Long.parseLong(tokens[0]));
                al.add(new Date(ts.getTime()));
                al2.add((double) Math.round(Double.parseDouble(tokens[1])));

            }
            DataPoint[] dp = new DataPoint[al.size()];
            for (int i = 0; i < al.size(); i++)
            {
                dp[i] = new DataPoint(al.get(i), al2.get(i));
            }

            s= new LineGraphSeries <> ( dp);
            s.setColor(Color.parseColor("blue"));
                   }
        catch (IllegalArgumentException e) {
            Toast.makeText(visualizechannel.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is2=getResources().openRawResource(R.raw.channel2);
        BufferedReader reader2=new BufferedReader(new InputStreamReader(is2, Charset.forName("UTF-8")));
        String line2;
        LineGraphSeries<DataPoint> s2 = new LineGraphSeries <>();
        ArrayList<Date> bl=new ArrayList<>();
        ArrayList<Double>bl2=new ArrayList<>();
        try{
            while ((line2=reader2.readLine())!=null)
            {
                String[] tokens=line2.split(",");
                Timestamp ts=new Timestamp(Long.parseLong(tokens[0]));
                bl.add(new Date(ts.getTime()));
                bl2.add((double) Math.round(Double.parseDouble(tokens[1])));
            }
            DataPoint[] dp2 = new DataPoint[bl.size()];
            for (int i = 0; i < bl.size(); i++)
            {
                dp2[i] = new DataPoint(bl.get(i), bl2.get(i));
            }
            s2= new LineGraphSeries <> (dp2);
            s2.setColor(Color.parseColor("green"));
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(visualizechannel.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is3=getResources().openRawResource(R.raw.channel3);
        BufferedReader reader3=new BufferedReader(new InputStreamReader(is3, Charset.forName("UTF-8")));
        String line3;
        LineGraphSeries<DataPoint> s3 = new LineGraphSeries <>();
        ArrayList<Date> cl=new ArrayList<>();
        ArrayList<Double>cl2=new ArrayList<>();
        try{
            while ((line3=reader3.readLine())!=null)
            {
                String[] tokens=line3.split(",");
                Timestamp ts=new Timestamp(Long.parseLong(tokens[0]));
                cl.add(new Date(ts.getTime()));
                cl2.add((double) Math.round(Double.parseDouble(tokens[1])));
            }
            DataPoint[] dp3 = new DataPoint[cl.size()];
            for (int i = 0; i < cl.size(); i++)
            {
                dp3[i] = new DataPoint(cl.get(i), cl2.get(i));
            }
            s3= new LineGraphSeries <> (dp3);
            s3.setColor(Color.parseColor("red"));


        }
        catch (IllegalArgumentException e) {
            Toast.makeText(visualizechannel.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }
        InputStream is4=getResources().openRawResource(R.raw.channel4);
        BufferedReader reader4=new BufferedReader(new InputStreamReader(is4, Charset.forName("UTF-8")));
        String line4;
        LineGraphSeries<DataPoint> s4 = new LineGraphSeries <>();
        ArrayList<Date> dl=new ArrayList<>();
        ArrayList<Double>dl2=new ArrayList<>();
        try{
            while ((line4=reader4.readLine())!=null)
            {
                String[] tokens=line4.split(",");
                Timestamp ts=new Timestamp(Long.parseLong(tokens[0]));
                dl.add(new Date(ts.getTime()));
                dl2.add((double) Math.round(Double.parseDouble(tokens[1])));
            }
            DataPoint[] dp4 = new DataPoint[dl.size()];
            for (int i = 0; i < dl.size(); i++)
            {
                dp4[i] = new DataPoint(dl.get(i), dl2.get(i));
            }
            s4= new LineGraphSeries <> (dp4);
            s4.setColor(Color.parseColor("black"));

            graph.addSeries(s);
            graph.addSeries(s2);
            graph.addSeries(s3);
            graph.addSeries(s4);
            graph.getViewport().setXAxisBoundsManual(true);
            graph.getViewport().setYAxisBoundsManual(true);

            graph.getViewport().setMaxY(300);
            graph.getViewport().setMinY(0);

            graph.getViewport().setMaxX(40000);
            graph.getViewport().setMinX(0);
            graph.getViewport().setScrollable(true);
            graph.getViewport().setScrollableY(true);
        }
        catch (IllegalArgumentException e) {
            Toast.makeText(visualizechannel.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

        catch (IOException e) {
            e.printStackTrace();
        }


    }
}
