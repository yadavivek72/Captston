package com.vivek.captston;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends Activity implements View.OnClickListener {

    public static final String DEFAULT=" ";

    private FirebaseAuth mAuth;
    EditText editTextEmail,editTextPassword;
    ProgressBar progressBar;
    Button login,signUp;
    String Email,Email_sh="";
    String password,Password_sh="";
    TextView forgetpassword;
    ImageView logo;
    ProgressDialog pd;
    CheckBox chbox;
    SharedPreferences sharedPreferences;
   private DatabaseReference database;
   private DatabaseReference mref;
   private  DatabaseReference msubref;


    public void onCheckBoxClicked(View view)
    {
        boolean checked=((CheckBox) view).isChecked();
        SharedPreferences sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        Email_sh=editTextEmail.getText().toString();
        Password_sh=editTextEmail.getText().toString();
        editor.putString("Email",Email_sh);
        editor.putString("Password",Password_sh);
        editor.commit();
    }


    public void showSeeker(View view)
    {
        Intent i=new Intent(Login.this,Seeker.class);
        startActivity(i);
    }


    public void showRecruiter(View view)
    {
        Intent i=new Intent(Login.this,Recruiter.class);
        startActivity(i);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editTextEmail=(EditText)findViewById(R.id.username);
        editTextPassword=(EditText)findViewById(R.id.password);
        SharedPreferences sharedPreferences= getSharedPreferences("MyData", Context.MODE_PRIVATE);
        Email_sh=sharedPreferences.getString("Email",DEFAULT);
        Password_sh=sharedPreferences.getString("Password",DEFAULT);
        editTextEmail.setText(Email_sh);
        editTextPassword.setText(Password_sh);
        database= FirebaseDatabase.getInstance().getReference();
        mref=database.child("user");

        logo=(ImageView)findViewById(R.id.logo);
        // progressBar=findViewById(R.id.progressBar);
        findViewById(R.id.buttonsignup).setOnClickListener(this);
        findViewById(R.id.buttonlogin).setOnClickListener(this);
        findViewById(R.id.forget_password_textView).setOnClickListener(this);
        mAuth=FirebaseAuth.getInstance();
         pd=new ProgressDialog(this);

         chbox=(CheckBox) findViewById(R.id.remember_me_checkbox);

        if(mAuth.getCurrentUser() != null)
        {


            FirebaseUser user = mAuth.getCurrentUser();

            msubref=mref.child(user.getUid());
            msubref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.exists()){
                        String type=dataSnapshot.child("Type").getValue(String.class);
                        //  Toast.makeText(getApplicationContext(),"Type"+type,Toast.LENGTH_SHORT).show();

                        if(type.equals("Recruiter")){
                            Intent intent=new Intent(getApplication(),Recruiter.class);
                            startActivity(intent);
                        }
                        else{

                            Intent intent1=new Intent(getApplication(),Seeker.class);
                            startActivity(intent1);
                        }

                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });

            finish();
        }



    }

    private void userLogin(){
        Email=editTextEmail.getText().toString().trim();
        password=editTextPassword.getText().toString().trim();
        if(!validation()) {
            return;
        }

      //  progressBar.setVisibility(View.VISIBLE);
        pd.setTitle("Logging");
        pd.show();
        mAuth.signInWithEmailAndPassword(Email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                      //progressBar.setVisibility(View.GONE);
                if (task.isSuccessful()) {
                    // Sign in success, update UI with the signed-in user's information
                    //  Log.d(TAG, "signInWithEmail:success");
                    Toast.makeText(getApplication(),"Successful authentication",Toast.LENGTH_SHORT).show();
                 pd.cancel();
                 // finish();

                    FirebaseUser user = mAuth.getCurrentUser();

                    msubref=mref.child(user.getUid());
                    msubref.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            if(dataSnapshot.exists()){
                               String type=dataSnapshot.child("Type").getValue(String.class);
                             //  Toast.makeText(getApplicationContext(),"Type"+type,Toast.LENGTH_SHORT).show();

                               if(type.equals("Recruiter")){
                                   Intent intent=new Intent(getApplication(),Recruiter.class);
                                   startActivity(intent);
                               }
                               else{

                                   Intent intent1=new Intent(getApplication(),Seeker.class);
                                   startActivity(intent1);
                               }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });













                } else {
                    // If sign in fails, display a message to the user.
                    //   Log.w(TAG, "signInWithEmail:failure", task.getException());
            //        finish();
              pd.cancel();
                    Toast.makeText(getApplication(), "Authentication failed.",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

private void sendEmailVerification(){

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
        if(password.isEmpty()){
            editTextPassword.setError("Please Enter the password");
            editTextPassword.requestFocus();
            valid=false;
        }
        if(password.length()<6){
            editTextPassword.setError("Minimum password length is 6");
            editTextPassword.requestFocus();
            valid=false;
        }

        return valid; }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.buttonlogin:
                userLogin();
                // Toast.makeText(getApplication(),"User successfully login",Toast.LENGTH_SHORT).show();
                break;
            case R.id.buttonsignup:
                Intent intent=new Intent(this,SignUp.class);
                startActivity(intent);
                break;
            case R.id.forget_password_textView:
                Intent intent1 =new Intent(this,ForgotPasswordModule.class);
                startActivity(intent1);
                break;
                default:
                break;

        }




        //int id=v.getId();
        ///if(id==R.id.buttonlogin){

        //}

    }

}
