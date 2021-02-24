package com.example.petridelivery.fragments.abs;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.petridelivery.abs.BaseMainActivity;
import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.concurent.TaskRunner;
import com.example.petridelivery.wrappers.WrapperComponent;

public abstract class BaseFragment extends Fragment {

    protected WrapperComponent wc;
    protected TaskRunner runner;
    protected PetriDeliveryApp app;
    protected BaseMainActivity activity;

    public BaseFragment(int contentLayoutId) {
        super(contentLayoutId);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        activity = (BaseMainActivity) getActivity();

        wc = activity.getWc();
        runner = activity.getRunner();
        app = activity.getApp();
    }
}
