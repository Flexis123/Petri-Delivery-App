package com.example.petridelivery.lists.datasources.abs


import androidx.paging.PagingSource
import androidx.paging.PagingState

abstract class IntegerKeyPagingSource<T: Any> : PagingSource<Int, T>() {

    override fun getRefreshKey(state: PagingState<Int, T>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val (_, prevKey, nextKey) = state.closestPageToPosition(anchorPosition) ?: return null
        if (prevKey != null) {
            return prevKey + 1
        }
        return if (nextKey != null) {
            nextKey + 1
        } else null
    }

    protected fun getCurrentPage(loadParams: LoadParams<Int>): Int {
        val page = loadParams.key
        return page ?: 1
    }

    protected fun getPreviousPage(currentPage: Int): Int? {
        return if (currentPage > 0) currentPage - 1 else null
    }

    protected fun getNextPage(currentPage: Int, response: MutableList<T>): Int? {
        return if (!response.isEmpty()) currentPage + 1 else null
    }
}