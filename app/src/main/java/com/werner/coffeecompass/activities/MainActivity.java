package com.werner.coffeecompass.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.werner.coffeecompass.R;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.viewmodels.CafeViewModel;

public class MainActivity extends AppCompatActivity {

    CafeViewModel mCafeViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCafeViewModel = new ViewModelProvider(this).get(CafeViewModel.class);

        findViewById( R.id.button ).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCafeViewModel.updateCafeLocation();
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
}
