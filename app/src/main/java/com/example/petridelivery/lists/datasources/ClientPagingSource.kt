package com.example.petridelivery.lists.datasources

import android.view.View
import com.example.petridelivery.lists.datasources.abs.IntegerKeyPagingSource
import com.example.petridelivery.wrappers.ClientWrapper
import com.petri.delivery.web.objects.ClientDto
import java.io.IOException
import kotlin.coroutines.Continuation

class ClientPagingSource(private val cw: ClientWrapper) : IntegerKeyPagingSource<ClientDto>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ClientDto> {
        val page = getCurrentPage(params)
        return try {
            val response = cw.getClients(page)
            LoadResult.Page(response, getPreviousPage(page), getNextPage(page, response))
        } catch (e: IOException) {
            LoadResult.Error(e)
        }
    }


}