package com.werner.coffeecompass.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.werner.coffeecompass.R;
import com.werner.coffeecompass.adapters.CafeRecyclerAdapter;
import com.werner.coffeecompass.adapters.DetailRecyclerAdapter;
import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.models.Photos;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.viewmodels.DetailsViewModel;

import java.util.ArrayList;
import java.util.List;

public class PhotoActivity extends AppCompatActivity {

    public static final String PHOTO_ID = "photo_id";

    DetailsViewModel mDetailsViewModel;
    private DetailRecyclerAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo);

        mDetailsViewModel = new ViewModelProvider(this).get(DetailsViewModel.class);

        initRecyclerView();
        subscribeObservers();

        mAdapter.setPhotoIds(null);

        Intent intent = getIntent();
        String placeId = intent.getStringExtra(PHOTO_ID);
        searchForPhotos(placeId);
    }

    private void searchForPhotos(String placeId) {
        mDetailsViewModel.requestDetails(placeId);
    }

    // Sets up recycler view and has callback to see if no more articles are available
    private void initRecyclerView() {

        mRecyclerView = findViewById(R.id.photo_list);
        mAdapter = new DetailRecyclerAdapter();
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    // Sets articles up to automatically update with live data
    private void subscribeObservers() {

        mDetailsViewModel.getDetail().observe(this, new Observer<Detail>() {
            @Override
            public void onChanged(@Nullable Detail detail) {
                if (detail != null ) {
                    List<String> photoIds = new ArrayList<>();
                    if( detail.getPhotos() != null ){
                        for( Photos photo : detail.getPhotos() ) {
                            photoIds.add(photo.getPhoto_reference());
                        }
                    }
                    mAdapter.setPhotoIds(photoIds);
                } else {
                    mAdapter.setPhotoIds(null);
                }
            }
        });
    }
}
