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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class Seeker extends ActionBarActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mtoggle;
    private NavigationView navigationView1;
    TextView textviewbenefits,textviewcomment;

    private TextView header_name_seeker,header_mailid_seeker,Seeker_name,Seeker_mob_no,Seeker_adhar_no;
    private ImageView headerpic_seeker,Img_Seeker_profile_pic;
    private FirebaseAuth mAuth;
    private DatabaseReference rdatabase;
    private String ruserid;
    private ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker);
        textviewbenefits=(TextView)findViewById(R.id.textviewbenefits);
        textviewcomment=(TextView)findViewById(R.id.textviewcomments);
        Img_Seeker_profile_pic=(ImageView)findViewById(R.id.Img_profile_Seeker);
        Seeker_name=(TextView)findViewById(R.id.Seeker_name);
        Seeker_adhar_no=(TextView)findViewById(R.id.Seeker_adhar_no);
        Seeker_mob_no=(TextView)findViewById(R.id.Seeker_mob_no);


        navigationView1=(NavigationView)findViewById(R.id.navigationview);
        View hview1=navigationView1.getHeaderView(0);
        headerpic_seeker=(ImageView)hview1.findViewById(R.id.headerpic_seeker);

        header_name_seeker=(TextView)hview1.findViewById(R.id.header_name_seeker);
        header_mailid_seeker=(TextView)hview1.findViewById(R.id.header_mailid_seeker);
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerlayout);
        mtoggle=new ActionBarDrawerToggle(this,drawerLayout,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(mtoggle);
        mtoggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
     pd=new ProgressDialog(this);

        mAuth=FirebaseAuth.getInstance();
        ruserid=mAuth.getCurrentUser().getUid().toString();
        rdatabase= FirebaseDatabase.getInstance().getReference("user").child(ruserid);

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
        headerpic_seeker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent ph=new Intent(Seeker.this,PhotoActivitySeeker.class);
                startActivity(ph);
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
                        logout_Seeker();
                        //Toast.makeText(getApplicationContext(), ""+item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });


        rdatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()) {
                                    Seeker_name.setText(dataSnapshot.child("Name").getValue().toString());
                                    Seeker_mob_no.setText(dataSnapshot.child("Contact number").getValue().toString());
                                    Seeker_adhar_no.setText(dataSnapshot.child("Aadhar number").getValue().toString());
                                    header_name_seeker.setText(dataSnapshot.child("Name").getValue().toString());

                                    header_mailid_seeker.setText(dataSnapshot.child("Email").getValue().toString());

                                if (dataSnapshot.hasChild("urlToImage")) {
                                    Picasso.get().load(dataSnapshot.child("urlToImage").getValue().toString()).transform(new CropCircleTransformation()).into(Img_Seeker_profile_pic);


                                    Picasso.get().load(dataSnapshot.child("urlToImage").getValue().toString()).transform(new CropCircleTransformation()).into(headerpic_seeker);

                                }
                            }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

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





    public void logout_Seeker() {
        pd.setTitle("Logging out");
        pd.show();
        mAuth.signOut();
        finish();
        startActivity(new Intent(Seeker.this,Login.class));
    }

}
