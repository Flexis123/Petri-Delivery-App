package com.example.petridelivery.lists.viewmodels;

import android.view.View;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.lists.datasources.ClientPagingSource;
import com.example.petridelivery.lists.viewmodels.abs.BaseViewModel;
import com.example.petridelivery.wrappers.ClientWrapper;
import com.petri.delivery.web.objects.ClientDto;

import lombok.Getter;

@Getter
public class ClientViewModel extends BaseViewModel<ClientDto>{
    public static final String PAGE_SIZE_PROP = "client.pagination.pageSize";

    public ClientViewModel(ClientWrapper clientWrapper, PetriDeliveryApp app, View view) {
        super(new ClientPagingSource(clientWrapper, view), app, PAGE_SIZE_PROP);
    }
}
