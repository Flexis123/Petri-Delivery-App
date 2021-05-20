package com.example.petridelivery.fragments

import android.os.Bundle
import android.view.View
import com.example.petridelivery.R
import com.example.petridelivery.fragments.abs.PaginationFragment
import com.example.petridelivery.lists.adapters.ClientListAdapter
import com.example.petridelivery.lists.adapters.ClientListAdapter.ClientViewHolder
import com.example.petridelivery.lists.viewmodels.ClientViewModel

import com.example.petridelivery.wrappers.ClientWrapper
import com.petri.delivery.web.objects.ClientDto
import javax.inject.Inject

class ClientiFragment : PaginationFragment<ClientDto, ClientViewHolder>(R.layout.fragment_client, ClientViewModel::class.java, R.id.clientRecyclerView) {

    @Inject
    lateinit var clientWrapper: ClientWrapper

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        setup()
        wc.inject(this)

        super.onViewCreated(view, savedInstanceState, ClientListAdapter(clientWrapper, view),
                ClientViewModel(clientWrapper, wc.app()))
    }
}