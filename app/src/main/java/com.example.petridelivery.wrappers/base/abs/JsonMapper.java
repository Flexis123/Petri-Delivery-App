package com.example.petridelivery.wrappers.base.abs;

public interface JsonMapper {
	<T> T fromJson(String json, Class<T> cls);
	String toJson(Object obj);
	<T> T fromJson(File f, Class<T> cls);
	void toJson(File f, Object obj);
}
