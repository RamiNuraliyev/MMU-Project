package com.hit.dm;

import java.io.Serializable;

import com.google.gson.Gson;

public class DataModel<T> implements Serializable{
	private Long id;
	private T content;
	
	public DataModel(java.lang.Long id,T content) {
		this.setDataModelId(id);
		this.setContent(content);
	}

	public T getContent() {
		return content;
	}

	public void setContent(T content) {
		this.content = content;
	}

	public Long getDataModelId() {
		return id;
	}

	public void setDataModelId(Long id) {
		this.id = id;
	}
	public String toString() {
		//id+content
		return "id:  "+id+" "+"name:  "+content.toString();
	}

}
