package com.example.alertquake;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.alertquake.adapter.EarthquakeAdapter;
import com.example.alertquake.model.Earthquake;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.earthquakeList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new EarthquakeAdapter(getApplicationContext(), getDummyEarthquakes()));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));

        Button btnReport = findViewById(R.id.quakeButton);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EarthquakeReportActivity.class));
            }
        });

        Button btnHelp = findViewById(R.id.helpButton);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuideActivity.class));
            }
        });
    }

    private Earthquake[] getDummyEarthquakes() {
        Earthquake[] quakes = new Earthquake[20];

        for(int i = 0; i < quakes.length; i++) {
            if(i % 2 == 0) {
                quakes[i] = new Earthquake(2.8, "11.08.18", "Southern California");
            }
            else {
                quakes[i] = new Earthquake(5.3, "5.09.19", "COAST OF WESTERN TURKEY");
            }
        }

        return quakes;
    }
}