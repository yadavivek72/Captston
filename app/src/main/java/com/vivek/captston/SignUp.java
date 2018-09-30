package com.vivek.captston;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

 public class SignUp extends Activity implements View.OnClickListener,AdapterView.OnItemSelectedListener {

    String Email="", id, Contact_Number, Aadhar_Number,  Street_No,  Pincode, State,  City, Gender="",Password=""
            ,Confirm_password="",  Profession="",  Type="",  Name="",  Alternate_Contact_Number;

    private String TAG=Email;
    EditText editTextEmail,editTextContact_No,editTextAadhar_No,editTextStreet,editTextPassword,editTextConfirmPassword,
    editTextPincode,editTextState,editTextCity,editTextGender,editTextPreofession,editTextName,editTextAlternate_contact_No;
    Button button_next;

   // EditText signupname,signuppass,signupmobile,signupemail,signupadd;

    Spinner spinner_gender,spinner_profession;
    //Button button_next;
    RadioButton radioButtonseeker,radioButtonrecruiter;
    public FirebaseAuth mAuth;
     public FirebaseDatabase database;
     ArrayList<User> Userlist;
    public void onRadioButtonClicked(View view)
    {
        boolean checked=(view).isClickable();

        switch (view.getId())
        {
            case R.id.Radio_btn_seeker:
                if(radioButtonseeker.isChecked())
                {
                    spinner_profession.setEnabled(true);
                    spinner_profession.setClickable(true);
                    //Toast.makeText(SignUp.this,"Hello",Toast.LENGTH_SHORT).show();
                    Type="Seeker";
                }
            case R.id.Radio_Btn_recruiter:
                if(radioButtonrecruiter.isChecked())
                {
                    spinner_profession.setEnabled(false);
                    spinner_profession.setClickable(false);
                    //Toast.makeText(SignUp.this,"Hello",Toast.LENGTH_SHORT).show();
                     Type="Recruiter";
                }
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        Userlist=new ArrayList<>();
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextConfirmPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextAadhar_No=(EditText) findViewById(R.id.editTextAadhar_No);
        editTextCity=(EditText)findViewById(R.id.editTextCity);
        editTextState=(EditText)findViewById(R.id.editTextState);
        editTextStreet=(EditText)findViewById(R.id.editTextStreet);
        editTextName=(EditText)findViewById(R.id.editTextName);
        editTextPincode=(EditText)findViewById(R.id.editTextPincode);
        editTextContact_No=(EditText)findViewById(R.id.editTextContact_No);
        editTextAlternate_contact_No=(EditText)findViewById(R.id.editTextAlternate_No);
        //editTextPreofession=(EditText)findViewById(R.id.editTextProfession);
        radioButtonrecruiter=(RadioButton)findViewById(R.id.Radio_Btn_recruiter);
        radioButtonseeker=(RadioButton)findViewById(R.id.Radio_btn_seeker);
        button_next=(Button)findViewById(R.id.button_next);

        findViewById(R.id.button_next).setOnClickListener(this);
        spinner_gender  = (Spinner)findViewById(R.id.spinner_gender);
//create a list of items for the spinner.
        String[] items = new String[]{"Select", "Male", "Female"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.

        spinner_gender.setAdapter(adapter);
        spinner_gender.setOnItemSelectedListener(SignUp.this);



        spinner_profession  = (Spinner)findViewById(R.id.spinner_profession);
//create a list of items for the spinner.
        String[] items1 = new String[]{"Electrician", "BrickLayer", "Carpenter","Painter"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> profession_adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items1);
//set the spinners adapter to the previously created one.
        spinner_profession.setEnabled(false);
        spinner_profession.setClickable(false);

        spinner_profession.setAdapter(profession_adapter);



        spinner_profession.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Profession=parent.getItemAtPosition(position).toString();//Toast.makeText(SignUp.this, "Selected "+parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Profession="";
                return;

            }
        });
        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Gender=parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Gender="";
                return;

            }
        });


    }




    public void createuser(){

        Email =editTextEmail.getText().toString().trim();
        Password=editTextPassword.getText().toString().trim();

        Contact_Number=editTextContact_No.getText().toString().trim();
        Aadhar_Number=editTextAadhar_No.getText().toString().trim();
        Alternate_Contact_Number=editTextAlternate_contact_No.getText().toString().trim();
        State=editTextState.getText().toString().trim();
        Street_No=editTextStreet.getText().toString().trim();
        City=editTextCity.getText().toString().trim();
        Pincode=editTextPincode.getText().toString().trim();
        Confirm_password=editTextConfirmPassword.getText().toString().trim();
        if(!validation()){
          Toast.makeText(getApplication(),"Reached till validation",Toast.LENGTH_LONG).show();
            return;
        }
        Toast.makeText(getApplication(),"outside",Toast.LENGTH_LONG).show();
        mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {

            @Override

            public void onComplete(@NonNull Task<AuthResult> task) {
              Toast.makeText(getApplicationContext(),"Inside",Toast.LENGTH_LONG).show();
              Log.d(TAG,Email);
                if(task.isSuccessful()){
                    Toast.makeText(getApplication(),"user Registered",Toast.LENGTH_SHORT).show();
                //    Intent iSignup=new Intent(SignUp.this,Login.class);

                    id =FirebaseAuth.getInstance().getCurrentUser().getUid();
                    database=FirebaseDatabase.getInstance();
                    DatabaseReference mref=database.getReference();
                   Userlist.add(new User(Email, id, Contact_Number, Aadhar_Number,  Street_No,  Pincode, State,  City,
                           Gender,  Profession,  Type,  Name,  Alternate_Contact_Number));

                    mref.setValue(Userlist);


                }
                else{
                    Toast.makeText(getApplication(),"Registration failed",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    private boolean validation(){
        boolean valid =true;

        if(Email.isEmpty()){
            editTextEmail.setError("Email is required");
            editTextEmail.requestFocus();
            valid =false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            editTextEmail.setError("Please Enter valid Email address");
            editTextEmail.requestFocus();
            valid =false;
        }
        if(Password.isEmpty()){
            editTextPassword.setError("Please Enter the password");
            editTextPassword.requestFocus();
            valid=false;
        }
        /*if(Password.qual(Confirm_password))
        {
            editTextPassword.setError("Password do not match");
            editTextPassword.requestFocus();
            editTextConfirmPassword.requestFocus();
            valid =false;
        }*/
        if(Password.length()<6){
            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            valid=false;
        }
        if(Profession.isEmpty()){
            spinner_profession.requestFocus();
            valid=false;
        }
        if(Gender.isEmpty()){
            spinner_gender.requestFocus();
            valid=false;
        }
        if(Pincode.isEmpty()){
            editTextPincode.setError("Enter Pincode");
            editTextPincode.requestFocus();
            valid=false;
        }
        if(Contact_Number.isEmpty()){
            editTextContact_No.setError("Enter Mobile Number");
            editTextContact_No.requestFocus();
            valid=false;
        }
        if(Aadhar_Number.isEmpty()){

            editTextAadhar_No.setError("Enter Aadhar Number");
            editTextAadhar_No.requestFocus();
            valid=false;

        }
        if(State.isEmpty()){
            editTextState.setError("Enter state");
            editTextState.requestFocus();
            valid=false;
        }
        if(Street_No.isEmpty()){
            editTextStreet.setError("Enter the Stree Name or Number ");
            editTextStreet.requestFocus();
            valid=false;
        }
        if(City.isEmpty()){
            editTextCity.setError("Enter city");
            editTextCity.requestFocus();

        }

        return valid; }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_next:

                Toast.makeText(getApplication(),"Shit is happening",Toast.LENGTH_LONG).show();
                createuser();
                break;
            /*case R.id.textViewLogin:
                Intent i=new Intent(this,MainActivity.class);
                startActivity(i);
                break;*/
            default:
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


    }

     @Override
     public void onNothingSelected(AdapterView<?> parent) {

     }
 }
