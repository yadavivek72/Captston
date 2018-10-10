package com.vivek.captston;

import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class Recruiter extends ActionBarActivity {
    private DrawerLayout m;
    private ActionBarDrawerToggle mt;
    NavigationView navigation;
    ImageView electrician1, plumber1, bricklayer1, labour1, painter1, carpainter1, headerpic1;
   private FirebaseAuth mAuth;
    private ProgressDialog pd;
   private DatabaseReference rdatabase;
    private  final int PICK_IMAGE_REQUEST= 1;
    private Uri filepath;
private String ruserid;
    FirebaseStorage rstorage;
    StorageReference rstorageReference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recruiter);
        m = (DrawerLayout) findViewById(R.id.drawer);
        electrician1 = (ImageView) findViewById(R.id.electrician);
        plumber1 = (ImageView) findViewById(R.id.plumber);
        bricklayer1 = (ImageView) findViewById(R.id.bricklayer);
        labour1 = (ImageView) findViewById(R.id.labour);
        painter1 = (ImageView) findViewById(R.id.painter);
        carpainter1 = (ImageView) findViewById(R.id.carpainter);
        headerpic1 = (ImageView) findViewById(R.id.headerpic);
        pd=new ProgressDialog(this);
        mAuth=FirebaseAuth.getInstance();
        rstorage=FirebaseStorage.getInstance();
        ruserid=mAuth.getCurrentUser().getUid().toString();
        rstorageReference=rstorage.getReference().child("RecruiterImages");
        rdatabase= FirebaseDatabase.getInstance().getReference("user").child(ruserid);


        mt = new ActionBarDrawerToggle(this, m, R.string.open, R.string.close);
        navigation = (NavigationView) findViewById(R.id.navigationView);
        m.addDrawerListener(mt);
        mt.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigation.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int k = item.getItemId();
                switch (k) {
                    case R.id.addEmployee_Recruiter:
                        Toast.makeText(getApplicationContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.showEmployee_Recruiter:
                        Toast.makeText(getApplicationContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Contact_Recruiter:
                        Toast.makeText(getApplicationContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.Help_Recruiter:
                        Toast.makeText(getApplicationContext(), "" + item.getTitle(), Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.logout_Recruiter:
                        logout();
                        default:
                        return false;
                }
                return true;
            }
        });
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (mt.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void dothis(View v)
    {
        switch(v.getId())
        {
            case R.id.electrician:
                Toast.makeText(getApplicationContext(),"Electrician",Toast.LENGTH_SHORT).show();
                break;
            case R.id.carpainter:
                Toast.makeText(getApplicationContext(),"Carpainter",Toast.LENGTH_SHORT).show();
                break;
            case R.id.plumber:
                Toast.makeText(getApplicationContext(),"Plumber",Toast.LENGTH_SHORT).show();
                break;
            case R.id.bricklayer:
                Toast.makeText(getApplicationContext(),"BrickLayer",Toast.LENGTH_SHORT).show();
                break;
            case R.id.painter:
                Toast.makeText(getApplicationContext(),"Painter",Toast.LENGTH_SHORT).show();
                break;
            case R.id.labour:
                Toast.makeText(getApplicationContext(),"Labour",Toast.LENGTH_SHORT).show();
                break;
            case R.id.profile_recruiter:
                //Toast.makeText(MainActivity.this,"Header",Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(),Recruiter_Profile.class);
                startActivity(i);
                break;
            case R.id.headerpic:
                //Toast.makeText(getApplication(),"Image clicked",Toast.LENGTH_SHORT).show();
                 uploadRecruiterPicture();
                  break;
            default:
                break;
        }
    }
    public void logout() {
        pd.setTitle("Logging out");
        pd.show();
        mAuth.signOut();
        finish();
        startActivity(new Intent(Recruiter.this,Login.class));
    }
    public void uploadRecruiterPicture(){
        chooseImage();
        if(filepath!=null)
        uploadImage();

    }
    public void chooseImage(){
        Intent intent=new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),PICK_IMAGE_REQUEST);

    }
    public void uploadImage(){
        if(filepath!=null){
            final ProgressDialog pd=new ProgressDialog(this);
            pd.setTitle("Uploading....");
            pd.show();

         StorageReference ref=rstorageReference.child("images/"+mAuth.getCurrentUser().getUid().toString());
         ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
             @Override
             public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {


                 Handler handler=new Handler();
                 handler.postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         pd.dismiss();
                     }
                 },500);
                 String urlToImage=taskSnapshot.getDownloadUrl().toString();
                 rdatabase.child("urlToImage").setValue(urlToImage);
                 Picasso.get().load(urlToImage).into(headerpic1);

            Toast.makeText(getApplicationContext(),"Uploaded",Toast.LENGTH_SHORT).show();
             }
         }).addOnFailureListener(new OnFailureListener() {
             @Override
             public void onFailure(@NonNull Exception e) {
                 pd.dismiss();
                 Toast.makeText(getApplication(),"Uploading failed"+e.getMessage(),Toast.LENGTH_SHORT).show();
             }
         })
                 .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                     @Override
                     public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                    double progress=(100.0*taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                    pd.setMessage("Uploaded"+(int)progress+"%");
                     }
                 });


        }
        else {
            Toast.makeText(getApplication(),"No file selected",Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST&&resultCode==RESULT_OK
                &&data!=null&&data.getData()!=null){

            filepath=data.getData();
            Toast.makeText(getApplication(),"working till picaso",Toast.LENGTH_SHORT).show();

            Picasso.get().load(filepath).into(headerpic1);

        }
        else
        {
            Toast.makeText(getApplication(),"Not working",Toast.LENGTH_SHORT).show();
        }







    }
}
