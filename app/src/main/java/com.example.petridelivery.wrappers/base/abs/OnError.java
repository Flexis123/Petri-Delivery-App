package com.example.petridelivery.wrappers.base.abs;

public interface OnError {
	public void apply(ApiException response);
}
