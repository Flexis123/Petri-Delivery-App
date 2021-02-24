package com.example.petridelivery.util;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.concurent.ConcurrentComponent;
import com.example.petridelivery.concurent.DaggerConcurrentComponent;
import com.example.petridelivery.wrappers.DaggerWrapperComponent;
import com.example.petridelivery.wrappers.WrapperComponent;
import com.example.petridelivery.wrappers.base.WrappersModule;

public class InjectionUtils {
    public static WrapperComponent getWrapperComponent(PetriDeliveryApp app){
        WrapperComponent component = DaggerWrapperComponent.builder()
                .wrappersModule(new WrappersModule(app))
                .build();

        component.inject(app);
        return component;
    }

    public static ConcurrentComponent getConcurrentComponent(){
        return DaggerConcurrentComponent.builder().build();
    }
}
