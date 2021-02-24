package com.example.petridelivery.wrappers.base.abs;

public interface OnResponse<T>{
	public void apply(T body);
}
