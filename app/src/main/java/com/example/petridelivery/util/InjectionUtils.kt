package com.example.petridelivery.util

import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.wrappers.DaggerWrapperComponent
import com.example.petridelivery.wrappers.WrapperComponent
import com.example.petridelivery.wrappers.base.WrappersModule

object InjectionUtils {
    @JvmStatic
    fun getWrapperComponent(app: PetriDeliveryApp?): WrapperComponent {
        val component = DaggerWrapperComponent.builder()
                .wrappersModule(WrappersModule(app))
                .build()
        component.inject(app)
        return component
    }
}