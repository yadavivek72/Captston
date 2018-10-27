package com.vivek.captston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;

public class GetLocation extends AppCompatActivity {
    CardView currLocation;
    Button otherLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        currLocation=(CardView)findViewById(R.id.curr_Location);
        otherLocation=(Button)findViewById(R.id.other_Location);
        currLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),AvailableWorkers.class);
                startActivity(intent);
            }
        });
    }
}
