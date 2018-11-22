package com.hit.services;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.hit.dm.DataModel;

public class CacheUnitController<T> {

	public CacheUnitService<T> service;
	public CacheUnitController() throws FileNotFoundException, ClassNotFoundException, IOException{
		try {
			service =new CacheUnitService<T>();
		} catch (Exception e) {
			System.out.println("Can't Define servic");
			e.printStackTrace();
		}
	}
	public boolean delete(DataModel<T>[] dataModels) {

		return service.delete(dataModels);
	}
	public DataModel<T>[] get(DataModel<T>[] dataModels){

		return service.get(dataModels);
	}
	public boolean update(DataModel<T>[] dataModels) {

		return service.update(dataModels);
	}

}
