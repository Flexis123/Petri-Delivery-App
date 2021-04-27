package com.example.petridelivery.lists.datasources;

import android.view.View;

import com.example.petridelivery.lists.datasources.abs.IntegerKeyPagingSource;
import com.example.petridelivery.wrappers.ClientWrapper;
import com.petri.delivery.web.objects.ClientDto;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.util.List;

import kotlin.coroutines.Continuation;

public class ClientPagingSource extends IntegerKeyPagingSource<ClientDto> {

    private ClientWrapper cw;

    public ClientPagingSource(ClientWrapper cw, View v) {
        super(v);
        this.cw = cw;
    }

    @Nullable
    @Override
    public Object load(@NotNull LoadParams<Integer> loadParams, @NotNull Continuation<? super LoadResult<Integer, ClientDto>> continuation) {
        Integer page = getCurrentPage(loadParams);

        try {
            List<ClientDto> response = cw.getClients(page).execute().body();

            return new LoadResult.Page<>(response, getPreviousPage(page), getNextPage(page, response));
        } catch (IOException e) {
            return new LoadResult.Error<>(e);
        }
    }
}
