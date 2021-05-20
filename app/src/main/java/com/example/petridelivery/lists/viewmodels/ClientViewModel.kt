package com.example.petridelivery.lists.viewmodels

import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.lists.datasources.ClientPagingSource
import com.example.petridelivery.lists.viewmodels.abs.IntegerKeyViewModel
import com.example.petridelivery.wrappers.ClientWrapper
import com.petri.delivery.web.objects.ClientDto


class ClientViewModel(clientWrapper: ClientWrapper, app: PetriDeliveryApp) : IntegerKeyViewModel<ClientDto>(ClientPagingSource(clientWrapper), app, PAGE_SIZE_PROP) {
    companion object {
        val PAGE_SIZE_PROP: String = "client.pagination.pageSize"
    }
}