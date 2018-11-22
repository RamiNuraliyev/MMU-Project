package com.hit.server;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.hit.dm.DataModel;
import com.hit.services.CacheUnitController;

public class HandleRequest<T> implements Runnable{

	Socket s;
	CacheUnitController<T> controller;
	Gson gson=new Gson();
	ObjectOutputStream writer;
	ObjectInputStream reader;

	HandleRequest(java.net.Socket s,CacheUnitController<T> controller){
		this.s=s;
		this.controller=controller;
	}
	@Override
	public void run() {
		try {
			String action;
			reader = new ObjectInputStream((s.getInputStream()));
			writer=new ObjectOutputStream(s.getOutputStream());
			//get request string
			String req=(String) reader.readObject();
			java.lang.reflect.Type ref = new TypeToken<Request<DataModel<T>[]>>(){}.getType();
			Request<DataModel<T>[]> request = new Gson().fromJson(req, ref);

			action= request.headers.get("action");  
			if(action.equals("UPDATE"))
			{
				controller.update(request.body);
				Integer swaps=controller.service.getCache().swaps;
				writer.writeObject(swaps);
				writer.flush();

			}

			else
				if(action.equals("GET"))
				{
					String cacheContent=" ";
					controller.get(request.body);
					cacheContent=controller.service.getCache().algo.getCacheContent();


					writer.writeObject(cacheContent);
					writer.flush();
					writer.writeObject(controller.service.getCache().swaps);
					writer.flush();

				}
				else 
					if(action.equals("DELETE"))
						controller.delete(request.body);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally {
			try {
				reader.close();
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

}
