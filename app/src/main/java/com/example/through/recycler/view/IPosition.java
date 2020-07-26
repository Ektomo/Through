package com.example.through.recycler.view;

import moxy.MvpView;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

public interface IPosition extends MvpView {
    void largeUrl(String url);

    void setPosition(int pos);

    @StateStrategyType(value = SkipStrategy.class)
    void updateRecyclerView();
}
