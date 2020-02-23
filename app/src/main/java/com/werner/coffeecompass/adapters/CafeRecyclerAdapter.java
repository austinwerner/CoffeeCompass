package com.werner.coffeecompass.adapters;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.werner.coffeecompass.R;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.util.Constants;
import com.werner.coffeecompass.util.PrivateConstants;
import com.werner.coffeecompass.viewmodels.CafeViewModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CafeRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Places> mCafes;
    private OnCafeClickListener mOnClickListener;
    Resources mResources;

    public CafeRecyclerAdapter(OnCafeClickListener mOnClickListener) {

        this.mCafes = new ArrayList<>();
        this.mOnClickListener = mOnClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        mResources = viewGroup.getResources();
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_cafe_list_item, viewGroup, false);
        return new CafeViewHolder(view, mOnClickListener);
    }

    @Override
    // Binds a specific view with the view holder
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final CafeViewHolder holder = (CafeViewHolder) viewHolder;

        if (getItemCount() == 0) {
            return;
        }

        if (mCafes.get(i).getPhotos() != null && mCafes.get(i).getPhotos().length > 0) {
            // load images with glide library
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background);

            Glide.with((holder).itemView)
                    .setDefaultRequestOptions(options)
                    .load(getPhotoUrl(mCafes.get(i).getPhotos()[0].getPhoto_reference()))
                    .into(((CafeViewHolder) viewHolder).getmImage());

            holder.getmName().setText(mCafes.get(i).getName());
            holder.getmName().getBackground().setAlpha(150);

            holder.getmAddress().setText(mCafes.get(i).getVicinity());

            boolean isOpen = mCafes.get(i).getOpening_hours() != null &&
                    mCafes.get(i).getOpening_hours().isOpen_now();
            if (isOpen) {
                holder.getmOpen().setImageResource(R.drawable.ic_open);
            } else {
                holder.getmOpen().setImageResource(R.drawable.ic_close);
            }
        }

    }

    @Override
    public int getItemCount() {
        if (mCafes != null) {
            return mCafes.size();
        }
        return 0;
    }


    public void setCafes(List<Places> cafes) {

        mCafes = cafes;
        notifyDataSetChanged();
    }

    // Formats reference ID for URL retrieval
    private String getPhotoUrl(String referenceId) {
        return String.format(Constants.BASE_PHOTO_URL, PrivateConstants.API_KEY, Constants.MAX_PHOTO_HEIGHT, referenceId);
    }
}
