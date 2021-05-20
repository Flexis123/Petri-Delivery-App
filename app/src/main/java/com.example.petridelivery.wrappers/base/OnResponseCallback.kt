package com.example.petridelivery.wrappers.base

import android.content.Context
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


abstract class OnResponseCallback<T>(private val context: Context) : Callback<T> {

    override fun onFailure(call: Call<T>, t: Throwable) {
        t.printStackTrace()
        Toast.makeText(context, t.message, Toast.LENGTH_LONG).show()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {
        if(response.isSuccessful){
            onSuccessful(response)
        }else{
            onNotSuccesful(response)
        }
    }

    abstract fun onSuccessful(response: Response<T>)
    open fun onNotSuccesful(response: Response<T>) {}
}