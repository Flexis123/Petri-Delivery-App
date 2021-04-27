package com.example.petridelivery.fragments;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.petridelivery.R;
import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.fragments.abs.PaginationFragment;
import com.example.petridelivery.lists.adapters.ClientListAdapter;
import com.example.petridelivery.lists.viewmodels.ClientViewModel;
import com.example.petridelivery.lists.viewmodels.factories.ClientViewModelFactory;
import com.example.petridelivery.util.InjectionUtils;
import com.example.petridelivery.wrappers.ClientWrapper;
import com.petri.delivery.web.objects.ClientDto;

import javax.inject.Inject;

public class ClientiFragment extends PaginationFragment<ClientDto, ClientListAdapter.ClientViewHolder> {

    @Inject ClientWrapper clientWrapper;

    public ClientiFragment() {
        super(R.layout.fragment_client, ClientViewModel.class, R.id.clientRecyclerView);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        wc = InjectionUtils.getWrapperComponent((PetriDeliveryApp) getActivity().getApplication());
        wc.inject(this);

        super.onViewCreated(view, savedInstanceState, new ClientListAdapter(clientWrapper, view),
                new ClientViewModelFactory(clientWrapper, view, (PetriDeliveryApp) getActivity().getApplication()));
    }
}
