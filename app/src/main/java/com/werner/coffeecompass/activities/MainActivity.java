package com.werner.coffeecompass.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.werner.coffeecompass.R;
import com.werner.coffeecompass.adapters.CafeRecyclerAdapter;
import com.werner.coffeecompass.adapters.OnCafeClickListener;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.repositories.CafeRepository;
import com.werner.coffeecompass.viewmodels.CafeViewModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements OnCafeClickListener {

    CafeViewModel mCafeViewModel;
    private FusedLocationProviderClient mFusedLocationClient;
    private RecyclerView mRecyclerView;
    private CafeRecyclerAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        mCafeViewModel = new ViewModelProvider(this).get(CafeViewModel.class);

        initRecyclerView();
        subscribeObservers();

        updateLocation();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateLocation();
            }
        });


    }

    // Sets articles up to automatically update with live data
    private void subscribeObservers() {

        mCafeViewModel.getCafes().observe(this, new Observer<List<Places>>() {
            @Override
            public void onChanged(@Nullable List<Places> cafes) {
                if (cafes != null && cafes.size() > 0) {
                    mAdapter.setCafes(cafes);
                } else {
                    mAdapter.setCafes(null);
                }
            }
        });
    }

    // Sets up recycler view and has callback to see if no more articles are available
    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.cafe_list);
        mAdapter = new CafeRecyclerAdapter(this);
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View view, int i, int i1, int i2, int i3) {

                // If the recycler view can't be search any further, query next page
                if (!mRecyclerView.canScrollVertically(1)) {
                    mCafeViewModel.getMoreCafes();
                }
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
        mAdapter.setCafes(null);
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

    @Override
    public void onCafeClick(int position) {
        Intent intent = new Intent(this,PhotoActivity.class);
        intent.putExtra(PhotoActivity.PHOTO_ID, mCafeViewModel.getCafePlaceId(position));
        startActivity(intent);
    }
}
