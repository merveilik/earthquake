package com.example.alertquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.alertquake.model.City;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class EarthquakeReportActivity extends AppCompatActivity {

    EditText e1;
    private static Socket s;
    //private static ServerSocket ss;
    private static PrintWriter printWriter;
    private static InputStreamReader input;
    private static BufferedReader br;
    ArrayList<String> cityNames;
    private BufferedReader in;
    private BufferedWriter out;
    private static int PORT = 80;
    private static String ipAddress = "34.65.254.96";
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_report);
        final EditText etReport = findViewById(R.id.et_report);
        Button btnReport = findViewById(R.id.btn_report);
        e1 = (EditText)findViewById(R.id.et_report);
        cityNames = new ArrayList<String>();
        for(int i = 0; i<MainActivity.cityList.size(); i++){
            cityNames.add(MainActivity.cityList.get(i).getName());
        }
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        spin.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                name = cityNames.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,cityNames);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(aa);
        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etReport.getText().toString();

                if (!text.isEmpty()) {
                    // TODO: Request
                    Toast.makeText(getApplicationContext(),
                            "Your message is sent.",
                            Toast.LENGTH_SHORT).show();
                    try {
                        Socket socket = new Socket(ipAddress, PORT);
                        PrintStream out2 = new PrintStream(socket.getOutputStream());
                        out2.println("type:report:"+name+":"+text);
                        //out2.println("type:report:"+name);

                        socket.close();

                        new Handler().postDelayed(()->{
                            startActivity(new Intent(EarthquakeReportActivity.this, MainActivity.class));
                        },2000);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please type a message",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(EarthquakeReportActivity.this, MainActivity.class));
        finish();
    }
}


