package com.example.petridelivery.wrappers.base.abs;

import com.petri.delivery.web.objects.ContDto;

import java.util.Map;

public interface IWebClient {
	@Deprecated
	<T> void doRequestAsync(String uri, HttpMethod m, Map<String, String> headers, Map<String, String> params,
			Object body, OnError onError, OnResponse<T> onResponse, Class<T> responseType);
	
	<T> T doRequest(String uri, HttpMethod m, Map<String, String> headers, Map<String, String> params,
			Object body, Class<T> responseType);
	
	ContDto getAuth();
	
	default void setAuth(Map<String, String> headers) {
		ContDto auth = getAuth();
		
		headers.put("token", auth.getAccesToken());
		headers.put("user", auth.getNumeDeUtilizator());
	}

	@Deprecated
	default <T> void doAuthenticatedRequestAsync(String uri, HttpMethod m, Map<String, String> headers,
			Map<String, String> params, Object body, OnError onError, OnResponse<T> onResponse, Class<T> responseType) {
		
		setAuth(headers);
		this.doRequestAsync(uri, m, headers, params, body, onError, onResponse, responseType);
	}
	
	default <T>  T doAuthenticatedRequest(String uri, HttpMethod m, Map<String, String> headers,
			Map<String, String> params, Object body, Class<T> responseType) {
		
		setAuth(headers);
		return this.doRequest(uri, m, headers, params, body, responseType);
	}
}
