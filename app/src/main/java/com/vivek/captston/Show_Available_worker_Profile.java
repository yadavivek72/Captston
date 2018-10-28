package com.vivek.captston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Show_Available_worker_Profile extends AppCompatActivity {

    TextView tv_name1, tv_profession1, tv_phone1, tv_alter_phone1, tv_rating_num1, tv_experience_num1;

    ImageView img_profile1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show__available_worker__profile);
        tv_name1 = (TextView)findViewById(R.id.tv_name);

        tv_profession1 = (TextView)findViewById(R.id.tv_profession);

        tv_phone1 =(TextView) findViewById(R.id.tv_phone);

        tv_alter_phone1 = (TextView)findViewById(R.id.tv_alter_phone);

        tv_rating_num1 =(TextView) findViewById(R.id.tv_rating_num);

        tv_experience_num1 = (TextView)findViewById(R.id.tv_experience_num);

        img_profile1 =(ImageView) findViewById(R.id.img_profile);
    }
}
