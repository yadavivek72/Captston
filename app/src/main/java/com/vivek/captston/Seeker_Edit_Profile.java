package com.vivek.captston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class Seeker_Edit_Profile extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    Spinner spinner_gender,spinner_profession;
    String Profession="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker__edit__profile);
        String[] items = new String[]{"Select", "Male", "Female"};

        spinner_gender  = (Spinner)findViewById(R.id.spinner_gender);
        spinner_profession  = (Spinner)findViewById(R.id.spinner_profession);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);

        spinner_gender.setAdapter(adapter);
        spinner_gender.setOnItemSelectedListener(Seeker_Edit_Profile.this);


        String[] items1 = new String[]{"Electrician", "BrickLayer", "Carpenter","Painter"};

        ArrayAdapter<String> profession_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
        spinner_profession.setAdapter(profession_adapter);
        spinner_profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Profession=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(), "Selected "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Profession="";
                return;

            }
        });
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
