package com.example.petridelivery.wrappers.base;

import android.content.Context;
import android.widget.Toast;

import lombok.AllArgsConstructor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@AllArgsConstructor
public abstract class OnResponseCallback<T> implements Callback<T>{
    private Context context;

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        t.printStackTrace();
        Toast.makeText(context, t.getMessage(), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if(response.isSuccessful()){
            onSuccessful(response);
        }else{
            onNotSuccesful(response);
        }
    }

    public abstract void onSuccessful(Response<T> response);
    public void onNotSuccesful(Response<T> response){

    }
}
