package com.vivek.captston;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.location.Address;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Geocoder;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class GetLocation extends AppCompatActivity {
    FusedLocationProviderClient fusedLocationProviderClient;
    int PLACE_PICKER_REQUEST = 1;
    CardView currLocation;
    Button otherLocation;
    Location lastlocation;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String Scity;

    public void goPlacePicker(View view) {
        PlacePicker.IntentBuilder builder = new PlacePicker.IntentBuilder();
        try {
            startActivityForResult(builder.build(GetLocation.this), PLACE_PICKER_REQUEST);
        } catch (GooglePlayServicesRepairableException e) {
            e.printStackTrace();
        } catch (GooglePlayServicesNotAvailableException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = PlacePicker.getPlace(GetLocation.this, data);
                Geocoder geocoder = new Geocoder(this);
                try
                {
                    List<Address> addresses = geocoder.getFromLocation(place.getLatLng().latitude,place.getLatLng().longitude, 1);
                    String address = addresses.get(0).getAddressLine(0);
                    //String city = addresses.get(0).getAddressLine(1);
                    String city1=addresses.get(0).getSubAdminArea();
                    //String country = addresses.get(0).getAddressLine(2);
                    String knownName1 = addresses.get(0).getFeatureName();
                    String Locality=addresses.get(0).getLocality();
                    if (city1 != null)
                        Scity=city1;
                    else if (knownName1 != null)
                        Scity=knownName1;
                    else if (Locality!=null)
                        Scity=Locality;
                    editor.putString("City",Scity);
                    editor.commit();
                    Toast.makeText(GetLocation.this, "AddressPlacePicker" + address, Toast.LENGTH_LONG).show();
                    Toast.makeText(GetLocation.this, "CityPlacePicker" + Scity, Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(), AvailableWorkers.class);
                    startActivity(intent);


                } catch (IOException e)
                {

                    e.printStackTrace();
                }

                //tv.setText(place.getAddress());
               // Toast.makeText(GetLocation.this, "Address" + place.getAddress() + "\n", Toast.LENGTH_SHORT).show();

            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_location);
        currLocation = (CardView) findViewById(R.id.curr_Location);
        otherLocation = (Button) findViewById(R.id.other_Location);
        sharedPreferences= getSharedPreferences("Categories", Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(GetLocation.this);

        }

    public void dothis(View view) {
        Toast.makeText(getApplicationContext(),"here",Toast.LENGTH_LONG).show();
        getLocation();

    }

    void getLocation() {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 1);

        } else {
            Log.d("TAG", "getLocation: permissions granted");
            fusedLocationProviderClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        Toast.makeText(getApplicationContext(),"not null",Toast.LENGTH_LONG).show();
                        lastlocation = location;
                        double latitude = lastlocation.getLatitude();
                        double longitue = lastlocation.getLongitude();

                        //tvlatitue.setText(""+latitude);
                        //tvlongitude.setText(""+longitue);

                        Geocoder geocoder = new Geocoder(GetLocation.this, Locale.getDefault());


                        try {
                            //List<Address> locationlist = geocoder.getFromLocation(latitude,longitue,1);
                            List<Address> addresses = geocoder.getFromLocation(latitude, longitue, 1);

//kvns
                            if (addresses.size() > 0) {
                                // Address address = locationlist.get(0);
                                String address1 = addresses.get(0).getAddressLine(0); // If any additional address line present than only, check with max available address lines by getMaxAddressLineIndex()
                                String city1 = addresses.get(0).getSubAdminArea();
                                String state1 = addresses.get(0).getAdminArea();
                                String country1 = addresses.get(0).getCountryName();
                                String postalCode1 = addresses.get(0).getPostalCode();
                                String knownName1 = addresses.get(0).getFeatureName();
                                String Locality=addresses.get(0).getLocality();


                                if (city1 != null)
                                    Scity=city1;
                                else if (knownName1 != null)
                                    Scity=knownName1;
                                else if (Locality!=null)
                                    Scity=Locality;

                                Toast.makeText(GetLocation.this,"Address"+address1,Toast.LENGTH_LONG).show();
                                Toast.makeText(GetLocation.this,"City"+Scity,Toast.LENGTH_LONG).show();
                                editor.putString("City",Scity);
                                editor.commit();
                                Intent intent = new Intent(getApplicationContext(), AvailableWorkers.class);
                                startActivity(intent);



                                // tvphysicaladdress.setText("Address is:"+ address);
                            }

                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    getLocation();
                else
                    Toast.makeText(this, "permission denied", Toast.LENGTH_SHORT).show();
                break;
        }


    }
}
