package com.werner.coffeecompass.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.werner.coffeecompass.R;

import org.w3c.dom.Text;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CafeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView mImage;
    private ImageView mRating;
    private ImageView mPriceLevel;
    private ImageView mOpen;
    private TextView mName;
    private TextView mAddress;
    private OnCafeClickListener mOnClickListener;

    public CafeViewHolder(@NonNull View itemView, OnCafeClickListener onCafeClickListener ) {
        super(itemView);

        mImage = itemView.findViewById(R.id.image);
        mRating = itemView.findViewById(R.id.stars);
        mPriceLevel = itemView.findViewById(R.id.price);
        mOpen = itemView.findViewById(R.id.open);
        mName = itemView.findViewById(R.id.title);
        mAddress = itemView.findViewById(R.id.address);

        this.mOnClickListener = onCafeClickListener;
        itemView.setOnClickListener(this);
    }

    public ImageView getmImage() {
        return mImage;
    }

    public ImageView getmRating() {
        return mRating;
    }

    public ImageView getmPriceLevel() {
        return mPriceLevel;
    }

    public ImageView getmOpen() {
        return mOpen;
    }

    public TextView getmName() {
        return mName;
    }

    public TextView getmAddress() {
        return mAddress;
    }

    public OnCafeClickListener getmOnClickListener() {
        return mOnClickListener;
    }

    @Override
    public void onClick(View v) {
        mOnClickListener.onCafeClick(getAdapterPosition());
    }
}
