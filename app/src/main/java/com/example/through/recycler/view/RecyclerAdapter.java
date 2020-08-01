package com.example.through.recycler.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.through.R;
import com.example.through.recycler.model.PhotoLoader;
import com.example.through.recycler.presenter.BindRecycler;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private OnClickListenerCounter listenerCounter;
    private PhotoLoader loader;
    private BindRecycler bindRecycler;


    RecyclerAdapter(Context context, BindRecycler bindRecycler) {
        this.bindRecycler = bindRecycler;
        loader = new PhotoLoader(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layoutView = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_view2, parent, false);
        return new ViewHolder(layoutView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.position = position;
        bindRecycler.bindView(holder);
        holder.photo.setOnClickListener(v -> {
            if (listenerCounter != null) {
                listenerCounter.iterationCounter(v, position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return bindRecycler.getItemCount();
    }

    public void setListenerCounter(OnClickListenerCounter listenerCounter) {
        this.listenerCounter = listenerCounter;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements ISetPhoto {

        private int position;
        private ImageView photo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.photo);
        }

        @Override
        public void setPhoto(String url) {
            loader.loadImage(url, photo);
        }

        @Override
        public int getPos() {
            return position;
        }
    }

}
