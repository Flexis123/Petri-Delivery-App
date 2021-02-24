package com.example.petridelivery.wrappers;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.base.WrappersModule;

import javax.inject.Singleton;

import dagger.Component;

@Component (modules = WrappersModule.class)
@Singleton
public interface WrapperComponent {
	AuthWrapper getAuthWrapper();
	PetriDeliveryApp app();
	void inject(PetriDeliveryApp app);
}
