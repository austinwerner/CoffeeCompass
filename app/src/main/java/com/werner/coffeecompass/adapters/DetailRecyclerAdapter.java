package com.werner.coffeecompass.adapters;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.werner.coffeecompass.R;
import com.werner.coffeecompass.models.Detail;
import com.werner.coffeecompass.models.Places;
import com.werner.coffeecompass.util.Constants;
import com.werner.coffeecompass.util.PrivateConstants;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> mPhotoIds;

    public DetailRecyclerAdapter() {

        this.mPhotoIds = new ArrayList<>();
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_photo_list_item, viewGroup, false);
        return new DetailViewHolder(view);
    }

    @Override
    // Binds a specific view with the view holder
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        final DetailViewHolder holder = (DetailViewHolder) viewHolder;

        if (getItemCount() == 0) {
            return;
        }

        if (mPhotoIds.get(i) != null) {
            // load images with glide library
            RequestOptions options = new RequestOptions()
                    .centerCrop()
                    .error(R.drawable.ic_launcher_background);

            Glide.with((holder).itemView)
                    .setDefaultRequestOptions(options)
                    .load(getPhotoUrl(mPhotoIds.get(i)))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((DetailViewHolder) viewHolder).getmPhoto());
        }

    }

    @Override
    public int getItemCount() {
        if (mPhotoIds != null) {
            return mPhotoIds.size();
        }
        return 0;
    }


    public void setPhotoIds(List<String> photoIds) {

        mPhotoIds = photoIds;
        notifyDataSetChanged();
    }

    public void clearPhotos() {
        int size = mPhotoIds.size();
        mPhotoIds.clear();
        notifyItemRangeRemoved(0, size);;
    }

    // Formats reference ID for URL retrieval
    private String getPhotoUrl(String referenceId) {
        return String.format(Constants.BASE_PHOTO_URL, PrivateConstants.API_KEY, 300, referenceId);
    }
}
