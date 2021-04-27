package com.example.petridelivery.wrappers;

import com.example.petridelivery.ChangePasswordActivity;
import com.example.petridelivery.LoginActivity;
import com.example.petridelivery.MainActivity;
import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.fragments.ClientiFragment;
import com.example.petridelivery.wrappers.base.WrappersModule;

import javax.inject.Singleton;

import dagger.Component;

@Component (modules = WrappersModule.class)
@Singleton
public interface WrapperComponent {
	ConfigWrapper getConfigWrapper();
	PetriDeliveryApp app();
	void inject(PetriDeliveryApp app);
	void inject(LoginActivity loginActivity);
	void inject(ChangePasswordActivity changePasswordActivity);
	void inject(MainActivity mainActivity);
	void inject(ClientiFragment clientiFragment);
}
