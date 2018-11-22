package com.hit.server;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.hit.services.CacheUnitController;

public class Server implements Observer{

	boolean state=false;
	Socket someClient;
	ServerSocket server;
	CacheUnitController<String> controller = null;

	public Server() {
		try {
			server=new ServerSocket(1234);
			controller = new CacheUnitController<String>();
		} catch (IOException e) {
			e.printStackTrace();
			System.in.getClass();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	void start() throws ClassNotFoundException {

		System.out.println("server started");
		System.out.flush();
		Executor exec =Executors.newFixedThreadPool(2);
		HandleRequest<String> hr;
		

		while(state)
		{
			try {

				someClient = server.accept();
				hr=new HandleRequest<String>(someClient,controller);
				exec.execute(hr);

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
	}

	@Override
	public void update(Observable arg0, Object arg1) {

		state=(boolean) arg1;	
		if(!state)
		{
			try {
				someClient.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("server closed");
		} else
			try {
				this.start();

			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

	}

}
