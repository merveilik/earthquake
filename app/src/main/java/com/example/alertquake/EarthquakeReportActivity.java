package com.example.alertquake;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class EarthquakeReportActivity extends AppCompatActivity {

    EditText e1;
    private static Socket s;
    //private static ServerSocket ss;
    private static PrintWriter printWriter;
    private static InputStreamReader input;
    private static BufferedReader br;

    String message = "";
    private static String ip = "193.140.194.16";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake_report);
        final EditText etReport = findViewById(R.id.et_report);
        Button btnReport = findViewById(R.id.btn_report);
        e1 = (EditText)findViewById(R.id.et_report);


        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = etReport.getText().toString();

                if (!text.isEmpty()) {
                    // TODO: Request
                    sendText(text);
                    finish();
                    Toast.makeText(getApplicationContext(),
                            "Your message is sent.",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Please type a message",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void sendText(String message) {
        myTask mt = new myTask();
        mt.execute();
        if(!message.isEmpty()) {
            Toast.makeText(getApplicationContext(),
                    "Your message is sent.",
                    Toast.LENGTH_SHORT).show();
        }
    }

    class myTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                s = new Socket(ip,5000);
                printWriter = new PrintWriter(s.getOutputStream());
                printWriter.write(message);
                printWriter.flush();
                printWriter.close();
                s.close();


            }
            catch(IOException e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}


