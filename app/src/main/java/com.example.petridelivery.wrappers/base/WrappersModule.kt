package com.example.petridelivery.wrappers.base

import com.example.petridelivery.app.PetriDeliveryApp
import com.example.petridelivery.wrappers.AuthWrapper
import com.example.petridelivery.wrappers.ClientWrapper
import com.example.petridelivery.wrappers.ConfigWrapper
import com.example.petridelivery.wrappers.base.abs.File
import com.example.petridelivery.wrappers.base.abs.JsonMapper
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class WrappersModule(private val context: PetriDeliveryApp) {
    @Provides
    fun app(): PetriDeliveryApp {
        return context
    }

    @Provides
    fun getJsonMapper(): JsonMapper? {
        return GsonJsonMapper(app())
    }

    @Provides
    fun getBaseUrl(): String {
        return "http://10.0.2.2:8080"
    }

    @Provides
    fun getContFile(): File {
        return File("cont.json")
    }

    @Provides
    fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(UnsuccesfulRequestInterceptor(getJsonMapper(), app()))
                .addInterceptor(AuthenticatedRequestInterceptor(app()))
                .build()
    }

    @Provides
    fun getRetrofitClient(): Retrofit {
        return Retrofit.Builder()
                .baseUrl(getBaseUrl())
                .client(getOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
    }

    @Provides
    fun getAuthWrapper(): AuthWrapper {
        return getRetrofitClient().create(AuthWrapper::class.java)
    }

    @Provides
    fun getClientWrapper(): ClientWrapper {
        return getRetrofitClient().create(ClientWrapper::class.java)
    }

    @Provides
    fun getConfigWrapper(): ConfigWrapper {
        return getRetrofitClient().create(ConfigWrapper::class.java)
    }

}