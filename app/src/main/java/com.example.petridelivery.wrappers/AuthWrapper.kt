package com.example.petridelivery.wrappers

import com.petri.delivery.web.objects.ContDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthWrapper {
    @POST("/auth/change_default_password")
    fun change_default_password(@Header("password") password: String?,
                                     @Header("newPassword") newPassword: String?,
                                     @Header("username") username: String?): Call<Void>

    @GET("/auth/login")
    fun login(@Header("password") password: String?, @Header("username") username: String?): Call<ContDto?>

    @GET("/auth/login_with_token")
    fun loginWithToken(@Header("token") token: String?, @Header("username") username: String?): Call<ContDto>
}