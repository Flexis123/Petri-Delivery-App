package com.example.petridelivery.app;

import android.app.Application;

import com.example.petridelivery.wrappers.base.abs.File;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;
import com.petri.delivery.web.objects.ContDto;

import javax.inject.Inject;


public class PetriDeliveryApp  extends Application {
    private ContDto cont;
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
}
