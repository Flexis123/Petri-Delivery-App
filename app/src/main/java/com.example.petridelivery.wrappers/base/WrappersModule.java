package com.example.petridelivery.wrappers.base;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.base.abs.File;
import com.example.petridelivery.wrappers.base.abs.IWebClient;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;

import dagger.Module;
import dagger.Provides;

@Module
public class WrappersModule {
	private final PetriDeliveryApp context;

	public WrappersModule (PetriDeliveryApp context) {
		this.context = context;
	}

	@Provides
	public PetriDeliveryApp app() {
		return context;
	}
	
	@Provides
	public JsonMapper getJsonMapper() {
		return new GsonJsonMapper(app());
	}
	
	@Provides
	public String getBaseUrl() {
		return "http://10.0.2.2:8080";
	}
	
	@Provides
	public IWebClient getWebClient() {
		return new WebClient(getJsonMapper(), getBaseUrl(), app());
	}

	@Provides
	public File getContFile(){
		return new File("cont.json");
	}
}
