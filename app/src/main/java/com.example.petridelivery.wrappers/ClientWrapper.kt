package com.example.petridelivery.wrappers

import com.petri.delivery.web.objects.AdresaDto
import com.petri.delivery.web.objects.ClientDto
import com.petri.delivery.web.objects.NewClientBody
import com.petri.delivery.web.objects.UpdateClientBody
import retrofit2.Call
import retrofit2.http.*

interface ClientWrapper {

    @GET("/clienti/get_client")
    suspend fun getClients(@Header("prenume") prenume: String?, @Header("nume") nume: String?,
                        @Query("page") page: Int?): MutableList<ClientDto>

    @GET("/clienti/get_client_by_id")
    suspend fun getClient(@Query("id") id: Int): ClientDto?

    @GET("/clienti/get_clients")
    suspend fun getClients(@Query("page") page: Int?): MutableList<ClientDto>

    @POST("/clienti/add_adresa")
    suspend fun addAdresa(@Query("client_id") client_id: Int, @Body adresa: AdresaDto)

    @DELETE("/clienti/remove_adresa")
    suspend fun removeAdresa(@Query("client_id") client_id: Int, @Body adresa: AdresaDto)

    @DELETE("/clienti/delete")
    fun deleteClient(@Query("id") id: Int): Call<Void>

    @POST("/clienti/add")
    suspend fun addClient(@Body body: NewClientBody)

    @POST("/clienti/update")
    suspend fun updateClient(@Body body: UpdateClientBody)
}