package com.werner.coffeecompass.adapters;

import android.view.View;
import android.widget.ImageView;

import com.werner.coffeecompass.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailViewHolder extends RecyclerView.ViewHolder  {

    ImageView mPhoto;

    public DetailViewHolder(@NonNull View itemView ) {
        super(itemView);

        mPhoto = itemView.findViewById(R.id.detail_photo);
    }

    public ImageView getmPhoto() {
        return mPhoto;
    }
}
