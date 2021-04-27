package com.example.petridelivery.app;

import android.app.Application;

import com.example.petridelivery.wrappers.base.abs.File;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;
import com.petri.delivery.web.objects.ContDto;

import java.util.Hashtable;

import javax.inject.Inject;


public class PetriDeliveryApp  extends Application {
    private ContDto cont;
    private Hashtable<String, Object> configuration;
    @Inject File contFile;
    @Inject JsonMapper mapper;

    public ContDto getCont() {
        if(cont == null){
            this.cont = mapper.fromJson(contFile, ContDto.class);
        }
        return this.cont;
    }

    public void setCont(ContDto cont) {
        mapper.toJson(contFile, cont);
        this.cont = cont;
    }

    public Hashtable<String, Object> getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Hashtable<String, Object> configuration) {
        this.configuration = configuration;
    }
}
