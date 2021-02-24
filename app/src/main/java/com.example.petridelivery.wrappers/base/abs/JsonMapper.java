package com.example.petridelivery.wrappers.base.abs;

public interface JsonMapper {
	public <T> T fromJson(String json, Class<T> cls);
	public String toJson(Object obj);
	public <T> T fromJson(File f, Class<T> cls);
	public void toJson(File f, Object obj);
}
