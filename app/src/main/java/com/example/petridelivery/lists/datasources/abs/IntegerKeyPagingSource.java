package com.example.petridelivery.lists.datasources.abs;

import android.view.View;

import androidx.paging.PagingSource;
import androidx.paging.PagingState;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public abstract class IntegerKeyPagingSource<T> extends PagingSource<Integer, T> {
    protected View v;

    @Nullable
    @Override
    public Integer getRefreshKey(@NotNull PagingState<Integer, T> state) {
        Integer anchorPosition = state.getAnchorPosition();
        if (anchorPosition == null) {
            return null;
        }

        LoadResult.Page<Integer, T> anchorPage = state.closestPageToPosition(anchorPosition);
        if (anchorPage == null) {
            return null;
        }

        Integer prevKey = anchorPage.getPrevKey();
        if (prevKey != null) {
            return prevKey + 1;
        }

        Integer nextKey = anchorPage.getNextKey();
        if (nextKey != null) {
            return nextKey + 1;
        }

        return null;
    }

    protected Integer getCurrentPage(LoadParams<Integer> loadParams){
        Integer page = loadParams.getKey();
        return page != null ? page : 1;
    }

    protected Integer getPreviousPage(Integer currentPage){
        return currentPage > 0 ? currentPage - 1 : null;
    }

    protected Integer getNextPage(Integer currentPage, List<T> response){
        return !response.isEmpty() ? currentPage + 1 : null;
    }
}
