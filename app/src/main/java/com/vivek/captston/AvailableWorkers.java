package com.vivek.captston;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

public class AvailableWorkers extends AppCompatActivity {
    public static final String DEFAULT="0";
    TextView professsion;
    ArrayList<Person> al;
    MyAdaptor md;
    RecyclerView rv;
    Person p;
    int integer_img_var;
    private DatabaseReference rdatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Available Workers");
        setContentView(R.layout.activity_available_workers);
        professsion=(TextView)findViewById(R.id.ava_profession);
        SharedPreferences sharedPreferences= getSharedPreferences("Categories", Context.MODE_PRIVATE);
        String categorie=sharedPreferences.getString("categorie",DEFAULT);
        String img_var=sharedPreferences.getString("imgvar",DEFAULT);
        Toast.makeText(getApplicationContext(),"String"+img_var,Toast.LENGTH_SHORT).show();

        integer_img_var=Integer.parseInt(img_var);
        Toast.makeText(getApplicationContext(),"int"+integer_img_var,Toast.LENGTH_SHORT).show();
        professsion.setText(categorie);

        rv =(RecyclerView) findViewById(R.id.rec);
        RecyclerView.LayoutManager rlm = new LinearLayoutManager(this);
        rv.setLayoutManager(rlm);



        al = new ArrayList<>();
        p = new Person();


        for(int i=0;i<5;i++)
        {
            Person p = new Person();
            p.setName("Rohit");
            switch (integer_img_var)
            {
                case 1:
                    p.setImage(R.drawable.electrician);
                    break;
                case 2:
                    p.setImage(R.drawable.carpainter);
                    break;
                case 3:
                    p.setImage(R.drawable.plumber);
                    break;
                case 4:
                    p.setImage(R.drawable.bricklayer);
                    break;
                case 5:
                    p.setImage(R.drawable.painter);
                    break;
                case 6:
                    p.setImage(R.drawable.labour);
                    break;
                    default:
                        p.setImage(R.drawable.electrician);

            }

            p.setRating("4");
            al.add(p);
        }
        md = new MyAdaptor(this,al);

        rv.setAdapter(md);
    }
}
