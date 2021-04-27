package com.example.petridelivery.lists.viewmodels.factories;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.lists.viewmodels.ClientViewModel;
import com.example.petridelivery.wrappers.ClientWrapper;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ClientViewModelFactory  extends ViewModelProvider.NewInstanceFactory {
    private final ClientWrapper clientWrapper;
    private final View view;
    private final PetriDeliveryApp app;

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ClientViewModel(clientWrapper, app, view);
    }
}
