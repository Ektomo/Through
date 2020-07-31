package com.example.through.recycler.view;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface UpdateStates extends MvpView {
    void largeUrl(String url);


    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();
}
