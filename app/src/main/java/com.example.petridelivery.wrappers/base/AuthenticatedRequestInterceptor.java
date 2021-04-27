package com.example.petridelivery.wrappers.base;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.petri.delivery.web.objects.ContDto;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Hashtable;
import java.util.List;

import javax.inject.Inject;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class AuthenticatedRequestInterceptor implements Interceptor {

    private PetriDeliveryApp app;

    public static final String TOKEN = "filter.access.header.username";
    public static final String USERNAME = "filter.access.header.token";

    @Inject
    public AuthenticatedRequestInterceptor(PetriDeliveryApp app) {
        this.app = app;
    }

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
        Request request =  chain.request();

        Hashtable<String, Object> config = app.getConfiguration();
        if(config != null){
            if(((List<String>)config.get("authFilterList")).contains(request.url().uri().toString())){
                ContDto cont = app.getCont();

                final Hashtable<String, String> filters = (Hashtable<String, String>) config.get("filters");
                final String tokenHeader = filters.get(TOKEN);
                final String usernameHeader = filters.get(USERNAME);

                request = request.newBuilder()
                        .addHeader(tokenHeader, cont.getAccesToken())
                        .addHeader(usernameHeader, cont.getNumeDeUtilizator())
                        .build();
            }
        }
        return chain.proceed(request);
    }
}
