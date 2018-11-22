
package com.hit.memory;

import java.io.Serializable;
import java.util.HashMap;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;


public class CacheUnit<T>{

	IDao<Serializable,DataModel<T>> dao;
	public IAlgoCache<java.lang.Long,DataModel<T>> algo;
	public HashMap<String,String> model;
	public Integer swaps=0;

	public CacheUnit(IAlgoCache<java.lang.Long,DataModel<T>> algo,
			IDao<Serializable,DataModel<T>> dao){
		this.algo=algo;
		this.dao=dao;;
		model=new HashMap<String, String>();
	}
	public DataModel<T>[] getDataModels(java.lang.Long[] ids){
		DataModel<T>[] dms=new DataModel[ids.length];
		DataModel<T> tmp=new DataModel(null,null);
		int d=0;
		for(int i=0;i<ids.length;i++)
		{
			tmp=algo.getElement(ids[i]);
			if(tmp!=null) {
				dms[d++]=tmp;
			}
			else
				if(dao.find(ids[i])!=null) {
					algo.putElement(ids[i],dao.find(ids[i]));
					dms[d++]=algo.getElement(ids[i]);
					swaps++;

				}
		}
		return dms;

	}

}
