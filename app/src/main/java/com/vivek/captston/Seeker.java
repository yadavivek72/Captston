package com.vivek.captston;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Seeker extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView1;
    TextView textviewbenefits,textviewcomment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker);

        textviewbenefits=(TextView)findViewById(R.id.textviewbenefits);
        textviewcomment=(TextView)findViewById(R.id.textviewcomments);
        navigationView1=(NavigationView)findViewById(R.id.navigationview);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mtoggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Event handling on Benefits textview

        textviewbenefits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Benefits", Toast.LENGTH_SHORT).show();
            }
        });


        textviewcomment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "See Comments", Toast.LENGTH_SHORT).show();
            }
        });


        navigationView1.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                int k=item.getItemId();
                switch (k)
                {
                    case R.id.profile_Seeker:
                        Intent intent=new Intent(getApplicationContext(),Seeker_View_Profile.class);
                        startActivity(intent);

                        Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.contact_Seeker:
                        Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.help_Seeker:
                        Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout_Seeker:
                        Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mtoggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
