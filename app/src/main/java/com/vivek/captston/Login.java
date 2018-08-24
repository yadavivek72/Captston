package com.vivek.captston;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Login extends AppCompatActivity {

   // RadioGroup radioGroup;
   // RadioButton seeker,recruiter,radioButton;
   // Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //radioGroup=(RadioGroup)findViewById(R.id.radiogroup);
        //seeker=(RadioButton)findViewById(R.id.seeker);
        //recruiter =(RadioButton)findViewById(R.id.recruiter);
        //login=(Button)findViewById(R.id.blogin);
        //myLister();
    }
   /* public void myLister(){
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int rgs_id=radioGroup.getCheckedRadioButtonId();
                radioButton=(RadioButton)findViewById(rgs_id);
                String string=radioButton.getText().toString();
                Toast.makeText(Login.this,""+string,Toast.LENGTH_SHORT).show();
                if(string.equals("Seeker"))
                {
                    Intent intent= new Intent(Login.this,Seeker.class);
                    startActivity(intent);
                }
                else
                {
                    Intent intent= new Intent(Login.this,Recruiter.class);
                    startActivity(intent);
                }
*/
            //}
      //});
    //}
}
