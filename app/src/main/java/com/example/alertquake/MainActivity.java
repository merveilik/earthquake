package com.example.alertquake;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alertquake.adapter.CityAdapter;
import com.example.alertquake.model.City;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {
    public final String IP_ADRESS = "192.168.43.76";
    public final int PORT = 51564;
    public static ArrayList<City> cityList;
    private BufferedReader in;
    private BufferedWriter out;
    CityAdapter adapter;
    String myCity = "";
    ArrayList<String> cityNames;
    ArrayAdapter aa;
    EditText simpleEditText;
    private Handler handler;
    private HandlerThread handlerThread;
    MediaPlayer mediaPlayer;


    @Override
    protected void onResume() {
        super.onResume();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handlerThread = new HandlerThread("NETWORK_THREAD");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        cityList = new ArrayList<City>();

        ListView listView = (ListView) findViewById(R.id.cityList);

        adapter = new CityAdapter(cityList,getApplicationContext());

        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                City city= cityList.get(position);
                Toast.makeText(MainActivity.this, ""+city.getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(MainActivity.this, MessageActivity.class);
                i.putExtra("msg",city.getMessage());
                startActivity(i);

            }
        });

        Button btnReport = findViewById(R.id.quakeButton);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, EarthquakeReportActivity.class));
                finish();
            }
        });

        Button btnHelp = findViewById(R.id.helpButton);
        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GuideActivity.class));
            }
        });

        cityNames = new ArrayList<>();
         simpleEditText = (EditText) findViewById(R.id.editText);

         mediaPlayer = MediaPlayer.create(this, R.raw.media);

        handler.post(this::doInBackground);
        handler.post(this::popUp);

    }

    public void getText(View view){
        myCity = simpleEditText.getText().toString();
        Toast.makeText(this, ""+myCity, Toast.LENGTH_SHORT).show();
    }

    private void doInBackground() {
        try {
            Socket socket = new Socket(IP_ADRESS, PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

            PrintStream out2 = new PrintStream(socket.getOutputStream());
            out2.println("type:getList");

            Scanner scanner = new Scanner(socket.getInputStream());

            cityList.clear();
            while (scanner.hasNext()){

                String name = scanner.next();
                if(name.equals("&&")) {
                    break;
                }
                String counters = scanner.next();
                String messages = scanner.next();
                int counter = Integer.parseInt(counters);
                City c = new City(name,counter,messages);
                cityList.add(c);

                cityNames.add(name);
            }

            runOnUiThread(() -> {
                adapter.notifyDataSetChanged();
            });

            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    int t = 0;

    private void popUp() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                t++;
                if (t < 10000) {
                    try {
                        Socket socket = new Socket(IP_ADRESS, PORT);
                        in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        PrintStream out2 = new PrintStream(socket.getOutputStream());
                        out2.println("type:getList");

                        Scanner scanner = new Scanner(socket.getInputStream());

                        while (scanner.hasNext()) {
                            String name = scanner.next();
                            if(name.equals("&&")) {
                                break;
                            }
                            String counters = scanner.next();
                            String messages = scanner.next();
                            int counter = Integer.parseInt(counters);
                            if (name.equals(myCity) && counter >= 10) {
                                mediaPlayer.start();

                            }
                        }
                        Log.i("ASDASD", "hey");

                        socket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    handler.postDelayed(this, 3000);
                }
            }
        }, 1000);
    }
}