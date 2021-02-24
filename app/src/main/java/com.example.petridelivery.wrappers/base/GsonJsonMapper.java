package com.example.petridelivery.wrappers.base;


import android.content.Context;

import com.example.petridelivery.app.PetriDeliveryApp;
import com.example.petridelivery.wrappers.base.abs.File;
import com.example.petridelivery.wrappers.base.abs.JsonMapper;
import com.google.gson.Gson;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class GsonJsonMapper implements JsonMapper {
	
	private Gson m = new Gson();
	private PetriDeliveryApp app;

	public GsonJsonMapper(PetriDeliveryApp app){
		this.app = app;
	}

	@Override
	public <T> T fromJson(String json, Class<T> cls) {
		return m.fromJson(json, cls);
	}

	@Override
	public String toJson(Object obj) {
		return m.toJson(obj);
	}

	@Override
	public <T> T fromJson(File f, Class<T> cls){
		try{
			InputStream in = app.openFileInput(f.toString());
			InputStreamReader reader = new InputStreamReader(in);
			return m.fromJson(reader, cls);
		}catch (FileNotFoundException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void toJson(File f, Object obj) {
		try {
			OutputStream out = app.openFileOutput(f.toString(), Context.MODE_PRIVATE);
			try(OutputStreamWriter writer = new OutputStreamWriter(out)) {
				String json = toJson(obj);
				writer.write(json);
			}catch (IOException e) {e.printStackTrace(); }
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
}
