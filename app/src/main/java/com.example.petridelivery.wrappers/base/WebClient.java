package com.example.petridelivery.wrappers.base;

import android.os.Looper;
import android.widget.Toast;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.base.abs.ApiException;
import com.example.petridelivery.wrappers.base.abs.HttpMethod;
import com.example.petridelivery.wrappers.base.abs.IWebClient;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;
import com.example.petridelivery.wrappers.base.abs.OnError;
import com.example.petridelivery.wrappers.base.abs.OnResponse;
import com.petri.delivery.web.objects.ContDto;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public class WebClient implements IWebClient {
	
	private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
	private static final OkHttpClient client = new OkHttpClient();
	
	private JsonMapper mapper;
	private String baseUrl;
	private PetriDeliveryApp app;

	public WebClient(JsonMapper mapper, String baseUrl, PetriDeliveryApp context) {
		this.mapper = mapper;
		this.baseUrl = baseUrl;
		this.app = context;
	}
	
	private Request getRequest(String uri, HttpMethod m, Map<String, String> headers, Map<String, String> params, Object body) {
		HttpUrl.Builder url = HttpUrl.parse(baseUrl + uri).newBuilder();
		
		if(params != null) {
			for(Map.Entry<String, String> en : params.entrySet()){
				url.addQueryParameter(en.getKey(), en.getValue());
			}
		}
		
		Request.Builder req = new Request.Builder()
				.url(url.build());

		if(m != HttpMethod.GET){
			req.method(m.name(), RequestBody.create(mapper.toJson(body), JSON));
		}else{
			req.get();
		}
		
		if(headers != null) {
			for(Map.Entry<String, String> en : headers.entrySet()){
				req.addHeader(en.getKey(), en.getValue());
			}
		}
		
		return req.build();
	}

	private void showErrorToast(String body) throws IOException{
		String text = "";
		try{
			for(Object constraint : mapper.fromJson(body, List.class)){
				text += constraint + "\n";
			}
		}catch (Throwable e){
			text = body;
		}
		try {
			Looper.prepare();
		}catch (RuntimeException e){}
		Toast.makeText(app, text, Toast.LENGTH_LONG).show();
	}

	@Override
	@Deprecated
	public <T> void doRequestAsync(String uri, HttpMethod m, Map<String, String> headers, Map<String, String> params,
								   Object body, OnError onError, OnResponse<T> onResponse, Class<T> responseType) {
		
		client.newCall(getRequest(uri, m, headers, params, body)).enqueue(new Callback() {
			@Override
			public void onFailure(Call call, IOException e) {
				e.printStackTrace();
			}

			@Override
			public void onResponse(Call call, Response response) throws IOException {
				if(response.isSuccessful()) {
					onResponse.apply(mapper.fromJson(response.body().string(), responseType));
				}else if(onError != null){
					onError.apply(new ApiException(response.code(), response.body().string(), response.message()));
				}else{
					showErrorToast("");
				}
			}
		});
	}

	@Override
	public <T> T doRequest(String uri, HttpMethod m, Map<String, String> headers, Map<String, String> params,
			Object body, Class<T> responseType) {
		try {
			Response res = client.newCall(getRequest(uri, m, headers, params, body)).execute();
			String responseBody = res.body().string();

			if(!res.isSuccessful()){
				showErrorToast(responseBody);
				throw new ApiException(res.code(), responseBody, res.message());
			}

			return mapper.fromJson(responseBody, responseType);
		} catch (IOException e) {
			throw new ApiException(500, null, e.getMessage());
		}
	}

	@Override
	public ContDto getAuth() {
		return app.getCont();
	}
}
