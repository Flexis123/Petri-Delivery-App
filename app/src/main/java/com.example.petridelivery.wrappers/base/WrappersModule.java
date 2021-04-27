package com.example.petridelivery.wrappers.base;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.AuthWrapper;
import com.example.petridelivery.wrappers.ClientWrapper;
import com.example.petridelivery.wrappers.ConfigWrapper;
import com.example.petridelivery.wrappers.base.abs.File;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
	public File getContFile(){
		return new File("cont.json");
	}

	@Provides
	public OkHttpClient getOkHttpClient(){
		return new OkHttpClient.Builder()
				.addInterceptor(new UnsuccesfulRequestInterceptor(getJsonMapper(), app()))
				.addInterceptor(new AuthenticatedRequestInterceptor(app()))
				.build();
	}

	@Provides
	public Retrofit getRetrofitClient(){
		return new Retrofit.Builder()
				.baseUrl(getBaseUrl())
				.client(getOkHttpClient())
				.addConverterFactory(GsonConverterFactory.create())
				.build();
	}

	@Provides
	public AuthWrapper getAuthWrapper(){
		return getRetrofitClient().create(AuthWrapper.class);
	}

	@Provides
	public ClientWrapper getClientWrapper(){return getRetrofitClient().create(ClientWrapper.class);}

	@Provides
	public ConfigWrapper getConfigWrapper(){return getRetrofitClient().create(ConfigWrapper.class);}
}
