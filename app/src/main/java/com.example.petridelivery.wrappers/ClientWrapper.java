package com.example.petridelivery.wrappers;

import com.petri.delivery.web.objects.AdresaDto;
import com.petri.delivery.web.objects.ClientDto;
import com.petri.delivery.web.objects.NewClientBody;
import com.petri.delivery.web.objects.UpdateClientBody;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ClientWrapper{

    String prefix = "/clienti/";

    @GET(prefix + "get_client")
    Call<List<ClientDto>> getClients(@Header("prenume") String prenume, @Header("nume") String nume,
                                    @Query("page") Integer page);

    @GET(prefix+"get_client_by_id")
    Call<ClientDto> getClient(@Query("id") Integer id);

    @GET(prefix+"get_clients")
    Call<List<ClientDto>> getClients(@Query("page") Integer page);

    @POST(prefix+"add_adresa")
    Call<Void> addAdresa(@Query("client_id") Integer client_id, @Body AdresaDto adresa);

    @DELETE(prefix+"remove_adresa")
    Call<Void> removeAdresa(@Query("client_id") Integer client_id, @Body AdresaDto adresa);

    @DELETE(prefix+"delete")
    Call<Void> deleteClient(@Query("id") Integer id);

    @POST(prefix+"add")
    Call<Void> addClient(@Body NewClientBody body);

    @POST(prefix+"update")
    Call<Void> updateClient(@Body UpdateClientBody body);
}
