package com.hit.dao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Scanner;

import com.google.gson.Gson;
import com.hit.dm.DataModel;

public class DaoFileImpl<T> implements IDao<java.lang.Long,DataModel<T>>
{
	ObjectOutputStream out;
	ObjectInputStream in;
	String filepath;
	boolean empty=true;
	HashMap<Long,DataModel<T>> file_hm;
	public DaoFileImpl(String filePath) throws FileNotFoundException, IOException, ClassNotFoundException{
		this.filepath=filePath;
		out=new ObjectOutputStream(new FileOutputStream(filePath,false));
		in=new ObjectInputStream(new FileInputStream(filePath));
		file_hm=new HashMap<Long, DataModel<T>>();
		if(!empty)
		file_hm=(HashMap<Long,DataModel<T>>)in.readObject();
		
	}

	@Override
	public void delete(DataModel<T> entity) {
		file_hm.remove(entity.getDataModelId(),entity);
		try {
			out.flush();
			out.close();
			out=new ObjectOutputStream(new FileOutputStream(filepath,false));
			out.writeObject(file_hm);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public DataModel<T> find(Long id) {
		if(file_hm.get(id) != null)
			return file_hm.get(id);
		return null;
		
	}

	@Override
	public void save(DataModel<T> entity) {
		file_hm.put(entity.getDataModelId(),entity);
		try {
			out.flush();
			out.close();
			out=new ObjectOutputStream(new FileOutputStream(filepath,false));
			out.writeObject(file_hm);
			out.flush();
			empty=false;

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
