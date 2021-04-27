package com.example.petridelivery.wrappers;

import java.util.Hashtable;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ConfigWrapper {
    String prefix = "/config/";

    @GET(prefix+"get_config")
    Call<Hashtable<String, Object>> getConfig();
}
