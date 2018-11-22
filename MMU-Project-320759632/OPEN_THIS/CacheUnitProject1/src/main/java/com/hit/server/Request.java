package com.hit.server;

import java.io.Serializable;
import java.util.Map;

public class Request<T> implements Serializable{

	T body;
	Map<String,String> headers;

	Request(Map<String,String> headers, T body){
		this.body=body;
		this.headers=headers;
	}

	public T getBody() {
		return body;
	}
	public void setBody(T body) {
		this.body = body;
	}

	public Map<String,String> getHeaders() {
		return headers;
	}
	public void setHeaders(Map<String,String> headers) {
		this.headers = headers;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}

}
