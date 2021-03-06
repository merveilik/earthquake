package com.example.alertquake;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MessageActivity extends AppCompatActivity {

    ArrayList<String> msgs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        msgs = new ArrayList<String>();

        Intent intent = getIntent();
        String msg = intent.getStringExtra("msg");

        String s = "";
        for(int i=0;i<msg.length(); i++){
            if(msg.charAt(i)!=':'){
                if(msg.charAt(i)=='*'){
                    s+=" ";
                }
                else {
                    s+=msg.charAt(i);
                }
            } else{
                if(s.length() != 0)
                    msgs.add(s);
                s="";
            }
        }
        if(!s.equals(""))
            msgs.add(s);

        ArrayAdapter<String> itemsAdapter =
                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, msgs);
        ListView listView = (ListView) findViewById(R.id.msg_list);
        listView.setAdapter(itemsAdapter);

    }
}
