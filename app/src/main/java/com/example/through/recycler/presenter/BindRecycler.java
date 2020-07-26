package com.example.through.recycler.presenter;

import com.example.through.recycler.view.ISetPhoto;

public interface BindRecycler {
    void bindView(ISetPhoto iSetPhoto);

    int getItemCount();
}
