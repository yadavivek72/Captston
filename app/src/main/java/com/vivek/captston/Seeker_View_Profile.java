package com.vivek.captston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class Seeker_View_Profile extends AppCompatActivity {

    ImageView emp_img1, emp_small_img1;
    TextView emp_name1, emp_aadhar1, emp_gender1;
    TextView emp_profession1, emp_email1, emp_street1, emp_state1, emp_city1;
    TextView emp_pincode1, emp_contact_no1, emp_alter_contact_no1;
     private DatabaseReference database;
     private DatabaseReference mref;
     private DatabaseReference msubref;
     private FirebaseAuth mAuth;
     private String UrlToImg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seeker__view__profile);
        setTitle("Profile");
        emp_img1 = (ImageView)findViewById(R.id.seeker_emp_img);
        emp_small_img1 = (ImageView)findViewById(R.id.seeker_emp_smallimg);
        emp_name1 = (TextView)findViewById(R.id.seeker_emp_name);
        emp_aadhar1 = (TextView)findViewById(R.id.seeker_emp_aadhar);
        emp_gender1 = (TextView)findViewById(R.id.seeker_emp_gender);
        emp_profession1 = (TextView)findViewById(R.id.seeker_emp_profession);
        emp_email1 = (TextView)findViewById(R.id.seeker_emp_email);
        emp_street1 = (TextView)findViewById(R.id.seeker_emp_street);
        emp_state1 = (TextView)findViewById(R.id.seeker_emp_state);
        emp_city1 = (TextView)findViewById(R.id.seeker_emp_city);
        emp_pincode1 = (TextView)findViewById(R.id.seeker_emp_pincode);
        emp_contact_no1 = (TextView)findViewById(R.id.seeker_emp_contact_no);
        emp_alter_contact_no1 = (TextView)findViewById(R.id.seeker_emp_alter_contact_no);

        displayProfile();
    }

    public void displayProfile(){
        database=FirebaseDatabase.getInstance().getReference();
        Toast.makeText(getApplicationContext(),"id"+FirebaseAuth.getInstance().getCurrentUser().getUid().toString(),Toast.LENGTH_SHORT).show();
       database.child("user").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                        emp_name1.setText(dataSnapshot.child("Name").getValue().toString());
                        emp_street1.setText(dataSnapshot.child("Street number").getValue().toString());
                        emp_state1.setText(dataSnapshot.child("State").getValue().toString());
                        emp_profession1.setText(dataSnapshot.child("Profession").getValue().toString());
                        emp_pincode1.setText(dataSnapshot.child("Pincode").getValue().toString());
                        emp_gender1.setText(dataSnapshot.child("Gender").getValue().toString());
                        emp_contact_no1.setText(dataSnapshot.child("Contact number").getValue().toString());
                        emp_alter_contact_no1.setText(dataSnapshot.child("Alternate contact number").getValue().toString());
                        emp_city1.setText(dataSnapshot.child("city").getValue().toString());
                        emp_aadhar1.setText(dataSnapshot.child("Aadhar number").getValue().toString());
                        emp_email1.setText(dataSnapshot.child("Email").getValue().toString());
                             if(dataSnapshot.hasChild("urlToImage")){
                        UrlToImg=dataSnapshot.child("urlToImage").getValue().toString();
                        if(!UrlToImg.isEmpty()){

                            Picasso.get().load(UrlToImg).transform(new CropCircleTransformation()).into(emp_img1);

                        }}




                }

                else
                {
                    Toast.makeText(getApplicationContext(),"Data does not exit",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Data loading failed",Toast.LENGTH_SHORT).show();
            }
        });




    }



    public void dothis(View view)
    {
        Intent intent = new Intent(getApplicationContext(),Seeker_Edit_Profile.class);
        startActivity(intent);
    }
}
