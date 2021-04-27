package com.example.petridelivery.fragments.abs;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProvider;
import androidx.paging.PagingDataAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.petridelivery.lists.adapters.abs.BaseViewHolder;
import com.example.petridelivery.lists.viewmodels.abs.BaseViewModel;

public abstract class PaginationFragment<T, U extends BaseViewHolder> extends BaseFragment{
    protected BaseViewModel<T> viewModel;
    private Class<? extends BaseViewModel<T>> viewModelT;

    protected PagingDataAdapter<T, U> adapter;

    protected ViewModelProvider.NewInstanceFactory viewModelFactory;

    private int recyclerViewResId;

    public PaginationFragment(int contentLayoutId, Class<? extends BaseViewModel<T>> viewModelT, int recyclerViewResId) {
        super(contentLayoutId);
        this.viewModelT = viewModelT;
        this.recyclerViewResId = recyclerViewResId;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState, PagingDataAdapter<T, U> adapter,
                              ViewModelProvider.NewInstanceFactory viewModelFactory) {
        super.onViewCreated(view, savedInstanceState);

        this.viewModelFactory = viewModelFactory;
        this.adapter = adapter;

        viewModel = new ViewModelProvider(this, viewModelFactory).get(viewModelT);

        RecyclerView recyclerView = view.findViewById(recyclerViewResId);
        recyclerView.setAdapter(adapter);

        viewModel.getLiveData().observe(this, items -> adapter.submitData(getLifecycle(), items));
    }
}
