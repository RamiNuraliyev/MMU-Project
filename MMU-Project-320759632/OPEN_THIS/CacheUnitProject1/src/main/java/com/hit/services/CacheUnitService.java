package com.hit.services;

import java.io.Serializable;

import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;

public class CacheUnitService<T> {

	IDao<Serializable,DataModel<T>> dao;
	CacheUnit<T> cache;
	Long[] ids;


	public CacheUnitService() throws Exception {
		dao=new DaoFileImpl("C:\\Users\\rami\\Desktop\\OPEN_THIS\\CacheUnitProject1\\src\\main\\resources\\Data.txt");

		cache= new CacheUnit<T>(new LRUAlgoCacheImpl<Long,DataModel<T>>(3),dao);

	}

	boolean delete(DataModel<T>[] dataModels) {
		for(int i=0;i<dataModels.length;i++)
			dao.delete(dataModels[i]);
		return true;
	}
	DataModel<T>[] get(DataModel<T>[] dataModels){
		ids=new Long[dataModels.length];
		for(int i=0;i<dataModels.length;i++)
			ids[i]=dataModels[i].getDataModelId();
		return cache.getDataModels(ids);
	}
	boolean update(DataModel<T>[] dataModels) {
		/*
		dao.save(new DataModel<String>((long) 111,"rami"));
		dao.save(new DataModel<String>((long) 222,"aaa"));
		dao.save(new DataModel<String>((long) 333,"bbb"));
		dao.save(new DataModel<String>((long) 444,"ccc"));
		dao.save(new DataModel<String>((long) 555,"ddd"));
		 */
		for(int i=0;i<dataModels.length;i++)
			dao.save(dataModels[i]);
		return true;
	}
	public CacheUnit<T> getCache() {
		return cache;
	}


}
