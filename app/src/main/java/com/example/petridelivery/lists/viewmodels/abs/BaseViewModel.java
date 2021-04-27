package com.example.petridelivery.lists.viewmodels.abs;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.PagingLiveData;
import androidx.paging.PagingSource;

import com.example.petridelivery.app.PetriDeliveryApp;

import kotlinx.coroutines.CoroutineScope;
import lombok.Getter;

@Getter
public abstract class BaseViewModel<T> extends ViewModel {
    protected LiveData<PagingData<T>> liveData;

    public static final int BASE_PAGE_SIZE = 5;

    public BaseViewModel(PagingSource<Integer, T> pagingSource, PetriDeliveryApp app, final String pageSizeProp) {
        Integer pageSize = (Integer) app.getConfiguration().get(pageSizeProp);
        pageSize = pageSize != null ? pageSize : BASE_PAGE_SIZE;

        PagingConfig pagingConfig = new PagingConfig(pageSize, (int) (pageSize * 0.1), true);
        Pager<Integer, T> pager = new Pager<>(pagingConfig, () -> pagingSource);

        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this);
        liveData = PagingLiveData.cachedIn(PagingLiveData.getLiveData(pager), viewModelScope);
    }
}
