package com.hit.memory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

import org.junit.jupiter.api.Test;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheImpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

import junit.framework.Assert;

public class CacheUnitTest {

	String pathFile="C:\\Users\\rami\\Desktop\\OPEN_THIS\\CacheUnitProject1\\src\\main\\resources\\Data.txt";
	String id;
	String name;
	ObjectOutputStream outO;
	ObjectInputStream inO;
	HashMap<String,String> hm=new HashMap<String, String>();
	Scanner scan;
	IDao dao;
	IAlgoCache<Long,DataModel<String>> algo;
	CacheUnit<String> cache;
	

	public CacheUnitTest() throws FileNotFoundException, IOException, ClassNotFoundException {
		
		//------------create LRU cache
		try {
			dao=new DaoFileImpl<String>(pathFile);
			algo=new LRUAlgoCacheImpl<Long,DataModel<String>>(3);
			cache= new CacheUnit<String>(algo,dao);
			//------data base
			dao.save(new DataModel<String>((long) 111,"rami"));
			dao.save(new DataModel<String>((long) 222,"aaa"));
			dao.save(new DataModel<String>((long) 333,"bbb"));
			dao.save(new DataModel<String>((long) 444,"ccc"));
			dao.save(new DataModel<String>((long) 555,"ddd"));
			} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void getDataModelsTest()
	{
		DataModel<String>[] dm=new DataModel[3];
		Long [] ids=new Long [3];
		ids[0]=(long) 111;
		ids[1]=(long) 222;
		ids[2]=(long) 333;
		dm=cache.getDataModels(ids);
		Assert.assertEquals("rami", dm[0].getContent());
		Assert.assertEquals("aaa", dm[1].getContent());
		Assert.assertEquals("bbb", dm[2].getContent());
		
		Long [] ids2=new Long [2];
		ids2[0]=(long) 444;
		ids2[1]=(long) 555;
		dm=cache.getDataModels(ids2);
		Assert.assertEquals("ccc", dm[0].getContent());
		Assert.assertEquals("ddd", dm[1].getContent());
	}



}

