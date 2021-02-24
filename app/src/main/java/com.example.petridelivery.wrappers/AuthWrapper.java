package com.example.petridelivery.wrappers;

import com.example.petridelivery.wrappers.base.abs.HttpMethod;
import com.example.petridelivery.wrappers.base.abs.IWebClient;
import com.petri.delivery.web.controllers.abs.IAuthController;
import com.petri.delivery.web.objects.ContDto;

import java.util.HashMap;

import javax.inject.Inject;

public class AuthWrapper implements IAuthController{
	
	private IWebClient cl;
	private String baseUrl = "/auth/";
	
	@Inject
	public AuthWrapper(IWebClient cl) {
		this.cl = cl;
	}

	public void change_default_password(String password, String newPassword, String username) {
		cl.doRequest(baseUrl + "change_default_password", HttpMethod.POST, new HashMap<String, String>(){{
			put("password", password);
			put("newPassword", newPassword);
			put("username", username);
		}}, null, null, Void.class);
	}

	public ContDto login(String password, String username) {
		return cl.doRequest(baseUrl + "login", HttpMethod.GET,
				new HashMap<String, String>(){{
					put("password", password);
					put("username", username);
			}}, null, null, ContDto.class);
	}

	@Override
	public ContDto loginWithToken(String token, String username) {
		return cl.doRequest(baseUrl + "login_with_token", HttpMethod.GET,
				new HashMap<String, String>(){{
					put("token", token);
					put("username", username);
				}}, null, null, ContDto.class);
	}
}
