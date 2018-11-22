package com.hit.model;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Observable;


public class CacheUnitModel extends Observable implements Model{

	CacheUnitClient client;
	public Integer swaps=0;
	
	public CacheUnitModel() throws IOException {
		client = new CacheUnitClient();
	}

	@Override
	public <T> void updateModelData(T t){
		setChanged();
		notifyObservers(client.send((String) t));
		
		setChanged();
		notifyObservers(client.getCacheContent());

	}

}
