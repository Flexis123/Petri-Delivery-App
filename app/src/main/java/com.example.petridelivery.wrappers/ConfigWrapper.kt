package com.example.petridelivery.wrappers

import retrofit2.Call
import retrofit2.http.GET
import java.util.*

interface ConfigWrapper {
    @GET("/config/get_config")
    open fun getConfig(): Call<Hashtable<String?, Any?>?>
}