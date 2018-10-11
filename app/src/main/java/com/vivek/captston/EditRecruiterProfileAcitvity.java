package com.vivek.captston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

public class EditRecruiterProfileAcitvity extends AppCompatActivity implements View.OnClickListener,AdapterView.OnItemSelectedListener {
    ImageView emp_img1, emp_small_img1;

    EditText emp_name1, emp_aadhar1;

    EditText emp_email1, emp_street1, emp_state1, emp_city1;

    EditText emp_pincode1, emp_contact_no1, emp_alter_contact_no1;
    Spinner spinner_gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_recruiter_profile_acitvity);
        emp_img1 = (ImageView)findViewById(R.id.recruiter_edit_emp_img);
        emp_small_img1 = (ImageView)findViewById(R.id.recruiter_edit_emp_smallimg);
        emp_name1 = (EditText) findViewById(R.id.recruiter_edit_emp_name);
        emp_aadhar1 = (EditText) findViewById(R.id.recruiter_edit_emp_aadhar);
        emp_email1 = (EditText) findViewById(R.id.recruiter_edit_emp_email);
        emp_street1 = (EditText) findViewById(R.id.recruiter_edit_emp_street);
        emp_state1 = (EditText) findViewById(R.id.recruiter_edit_emp_state);
        emp_city1 = (EditText) findViewById(R.id.recruiter_edit_emp_city);
        emp_pincode1 = (EditText) findViewById(R.id.recruiter_edit_emp_pincode);
        emp_contact_no1 = (EditText) findViewById(R.id.recruiter_edit_emp_contact_no);
        emp_alter_contact_no1 = (EditText) findViewById(R.id.recruiter_edit_emp_alter_contact_no);

        spinner_gender  = (Spinner)findViewById(R.id.edit_recruiter_profile_spinner_gender);
//create a list of items for the spinner.
        String[] items = new String[]{"Select", "Male", "Female"};
//create an adapter to describe how the items are displayed, adapters are used in several places in android.
//There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
//set the spinners adapter to the previously created one.

        spinner_gender.setAdapter(adapter);
        spinner_gender.setOnItemSelectedListener(EditRecruiterProfileAcitvity.this);

        spinner_gender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Gender=parent.getItemAtPosition(position).toString();
                Toast.makeText(getApplicationContext(),"selected"+parent.getItemAtPosition(position),Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                //Gender="";
                return;

            }
        });



    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
