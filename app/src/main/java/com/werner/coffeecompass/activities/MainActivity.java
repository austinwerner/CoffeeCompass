package com.werner.coffeecompass.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.werner.coffeecompass.R;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.viewmodels.CafeViewModel;

public class MainActivity extends AppCompatActivity {

    CafeViewModel mCafeViewModel;
    private FusedLocationProviderClient mFusedLocationClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mCafeViewModel = new ViewModelProvider(this).get(CafeViewModel.class);

        findViewById( R.id.button ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
            }
        });

        findViewById( R.id.button2 ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCafeViewModel.getMoreCafes();
            }
        });

        findViewById( R.id.button3 ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showData();
            }
        });

    }

    private void showData() {
        if( mCafeViewModel.getCafes().getValue() != null ) {
            for( Places p : mCafeViewModel.getCafes().getValue() ) {
                Log.d( "DATA", p.toString() );
            }
        }
    }

    private void updateLocation() {
        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            mCafeViewModel.updateCafeLocation(location);
                        }
                    }
                });
    }
}
