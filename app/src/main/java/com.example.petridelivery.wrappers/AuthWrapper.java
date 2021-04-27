package com.example.petridelivery.wrappers;

import com.petri.delivery.web.objects.ContDto;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface AuthWrapper{

	String prefix = "/auth/";

	@POST(prefix+"change_default_password")
	Call<Void> change_default_password(@Header("password") String password,
								 @Header("newPassword") String newPassword,
								 @Header("username") String username);

	@GET(prefix+"login")
	Call<ContDto> login(@Header("password") String password, @Header("username") String username);

	@GET(prefix+"login_with_token")
	Call<ContDto> loginWithToken(@Header("token") String token, @Header("username") String username);
}
