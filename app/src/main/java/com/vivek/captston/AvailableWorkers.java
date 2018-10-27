package com.vivek.captston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class AvailableWorkers extends AppCompatActivity {
    TextView professsion;
    ArrayList<Person> al;
    MyAdaptor md;
    RecyclerView rv;
    Person p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_available_workers);
        professsion=(TextView)findViewById(R.id.ava_profession);
        //Intent in = getIntent();
        //Bundle b = in.getExtras();
        //String s = b.getString("cat");
        //Toast.makeText(this, ""+s, Toast.LENGTH_SHORT).show();
       // professsion.setText(s);

        rv =(RecyclerView) findViewById(R.id.rec);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);


        al = new ArrayList<>();
        p = new Person();


        for(int i=0;i<5;i++)
        {
            Person p = new Person();
            p.setName("Rohit");
            p.setImage(R.drawable.electrician);
            p.setRating(4);
            al.add(p);
        }
        md = new MyAdaptor(this,al);

        rv.setAdapter(md);
    }
}
