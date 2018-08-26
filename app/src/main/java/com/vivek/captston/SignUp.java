package com.vivek.captston;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity implements View.OnClickListener  {
    String Email,password;
    EditText signupname,signuppass,signupmobile,signupemail,signupadd;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mAuth=FirebaseAuth.getInstance();
        signupemail=(EditText)findViewById(R.id.signupemail);
        signuppass=(EditText)findViewById(R.id.signuppass);
        findViewById(R.id.signupbuttton).setOnClickListener(this);


    }

    public void createuser(){

        Email =signupemail.getText().toString().trim();
        password=signuppass.getText().toString().trim();
        if(!validation()){
            return;
        }

        mAuth.createUserWithEmailAndPassword(Email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplication(),"user Registered",Toast.LENGTH_SHORT).show();
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
            signupemail.setError("Email is required");
            signupemail.requestFocus();
            valid =false;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches()){
            signupemail.setError("Please Enter valid Email address");
            signupemail.requestFocus();
            valid =false;
        }
        if(password.isEmpty()){
            signuppass.setError("Please Enter the password");
            signuppass.requestFocus();
            valid=false;
        }
        if(password.length()<6){
            signuppass.setError("Minimum password length is 6");
            signuppass.requestFocus();
            valid=false;
        }

        return valid; }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.signupbuttton:
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
}
