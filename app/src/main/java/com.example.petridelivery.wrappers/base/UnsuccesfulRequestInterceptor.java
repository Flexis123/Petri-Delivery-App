package com.example.petridelivery.wrappers.base;

import android.os.Looper;
import android.widget.Toast;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Response;

public class UnsuccesfulRequestInterceptor implements Interceptor {

    private JsonMapper jsonMapper;
    private PetriDeliveryApp app;

    @Inject
    public UnsuccesfulRequestInterceptor(JsonMapper jsonMapper, PetriDeliveryApp app) {
        this.jsonMapper = jsonMapper;
        this.app = app;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain ch) throws IOException {
        Response res = ch.proceed(ch.request());

        if(ch.request().url().uri().toString().contains("config")){
            System.out.println();
        }

        if (!res.isSuccessful()) {
            String body = res.peekBody(1024 * 4).string();
            String text = "";
            try {
                for (Object constraint : jsonMapper.fromJson(body, List.class)) {
                    text += constraint + "\n";
                }
            } catch (Throwable e) {
                text = body;
            }
            try {
                Looper.prepare();
            } catch (RuntimeException e) {
            }
            Toast.makeText(app, text, Toast.LENGTH_LONG).show();
        }
        return res;
    }

}
