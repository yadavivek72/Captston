package com.vivek.captston;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ForgotPasswordModule extends Activity {
private String Email;
    EditText editTextEmail;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_module);



   mAuth=FirebaseAuth.getInstance();

        }

        private void sendEmailVerification(String Email){



//        findViewById(R.id.button_forgotpassword);
       final FirebaseUser user=mAuth.getCurrentUser();
       user.sendEmailVerification().addOnCompleteListener(this, new OnCompleteListener<Void>() {
           @Override
           public void onComplete(@NonNull Task<Void> task) {
               findViewById(R.id.editTextForgotPassword).setEnabled(true);
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Verification Email has been sent",Toast.LENGTH_LONG).show();

                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Send Email verification has failed",Toast.LENGTH_LONG).show();
                }
           }
       });



        }


    boolean validation(String Email){
      boolean valid=true;
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



        return valid;
    }
    public void verify(View view){
        editTextEmail=(EditText) findViewById(R.id.editTextForgotPassword);
        String Email =editTextEmail.getText().toString();
        if(validation(Email))
        {
            sendEmailVerification(Email);
        }
    }

}
