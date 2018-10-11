package com.vivek.captston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Recruiter_Profile extends AppCompatActivity {
    ImageView emp_img1, emp_small_img1;

    TextView emp_name1, emp_aadhar1, emp_gender1;

    TextView emp_profession1, emp_email1, emp_street1, emp_state1, emp_city1;

    TextView emp_pincode1, emp_contact_no1, emp_alter_contact_no1;
    private DatabaseReference database;
    private DatabaseReference mref;
    private FirebaseUser user;
    private FirebaseAuth mAuth;
    String userid;

   String TAG=Recruiter_Profile.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter__profile);
        emp_img1 = (ImageView)findViewById(R.id.recruiter_emp_img);
        emp_small_img1 = (ImageView)findViewById(R.id.recruiter_emp_smallimg);
        emp_name1 = (TextView)findViewById(R.id.recruiter_emp_name);
        emp_aadhar1 = (TextView)findViewById(R.id.recruiter_emp_aadhar);
        emp_gender1 = (TextView)findViewById(R.id.recruiter_emp_gender);
        emp_profession1 = (TextView)findViewById(R.id.recruiter_emp_profession);
        emp_email1 = (TextView)findViewById(R.id.recruiter_emp_email);
        emp_street1 = (TextView)findViewById(R.id.recruiter_emp_street);
        emp_state1 = (TextView)findViewById(R.id.recruiter_emp_state);
        emp_city1 = (TextView)findViewById(R.id.recruiter_emp_city);
        emp_pincode1 = (TextView)findViewById(R.id.recruiter_emp_pincode);
        emp_contact_no1 = (TextView)findViewById(R.id.recruiter_emp_contact_no);
        emp_alter_contact_no1 = (TextView)findViewById(R.id.recruiter_emp_alter_contact_no);


        //database refrence
        user=FirebaseAuth.getInstance().getCurrentUser();
        userid=user.getUid();
        database=FirebaseDatabase.getInstance().getReference();
        mref=database.child("User").child(userid).child("0");

        //infoCurrentUser();


    }

    //function for retriving current user information
    public void infoCurrentUser(){

        mref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //
              //   user=new ArrayList<User>();


               User  us=dataSnapshot.getValue(User.class);

                emp_name1.setText(us.getName());
                emp_aadhar1.setText(us.getAadhar_Number());
                emp_city1.setText(us.getCity());
                emp_email1.setText(us.getEmail());
                emp_gender1.setText(us.getGender());
                emp_contact_no1.setText(us.getContact_Number());
                emp_alter_contact_no1.setText(us.getAlternate_Contact_Number());
                emp_pincode1.setText(us.getPincode());
                emp_profession1.setText(us.getProfession());
                emp_state1.setText(us.getState());
                emp_street1.setText(us.getStreet_No());

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(),"Loading failed",Toast.LENGTH_LONG).show();
                Log.w(TAG,"Load user data",databaseError.toException());
            }
        });


    }

    public void dothis(View view)

    {

        Intent intent = new Intent(getApplicationContext(),EditRecruiterProfileAcitvity.class);

        startActivity(intent);
        finish();

    }
}
