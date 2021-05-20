package com.example.petridelivery.lists.viewmodels.abs

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.lists.datasources.abs.IntegerKeyPagingSource
import kotlinx.coroutines.flow.Flow


abstract class IntegerKeyViewModel<T : Any>(pagingSource: IntegerKeyPagingSource<T>, app: PetriDeliveryApp, pageSizeProp: String) : ViewModel() {
    lateinit var flow: Flow<PagingData<T>>
    private var pagingConfig: PagingConfig

    companion object {
        const val BASE_PAGE_SIZE = 5
    }

    init {
        var pageSize = app.getConfiguration()!![pageSizeProp] as Int?
        pageSize = pageSize ?: BASE_PAGE_SIZE

        pagingConfig = PagingConfig(pageSize, (pageSize * 0.1) as Int, true)
        changePagingSource(pagingSource)
    }

    fun changePagingSource(source: IntegerKeyPagingSource<T>){
        val pager: Pager<Int, T> = Pager(pagingConfig, null, { source })
        flow = pager.flow
    }

}//muie garada