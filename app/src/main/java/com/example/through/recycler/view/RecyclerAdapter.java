package com.example.through.recycler.view;

import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.through.R;
import com.example.through.recycler.model.RecyclerImage;
import com.example.through.recycler.presenter.RecyclerPresenter;

import java.util.List;

import moxy.presenter.InjectPresenter;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private List<Drawable> images;
    private OnClickListenerCounter listenerCounter;
    private int pos;


    RecyclerAdapter(List<Drawable> images){
        this.images = images;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if(images != null){
            Drawable image = images.get(position);
            holder.photo.setImageDrawable(image);
            holder.photo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listenerCounter != null){
                        listenerCounter.iterationCounter(v, position);
                    }
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public void setListenerCounter(OnClickListenerCounter listenerCounter){
        this.listenerCounter = listenerCounter;
    }



    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }
    }

}
