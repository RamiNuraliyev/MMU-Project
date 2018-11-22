package com.hit.model;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CacheUnitClient {
	InetAddress localaddr;
	Socket server;
	ObjectInputStream in;
	ObjectOutputStream out;
	String cacheContent=null;

	public CacheUnitClient() throws IOException{
	}

	public String send(String request){
		String swaps=null;
		Integer sp=0;
		try {
			
			localaddr=InetAddress.getLocalHost();
			server=new Socket(localaddr.getHostAddress(),1234);
			out=new ObjectOutputStream(server.getOutputStream());
			in=new ObjectInputStream(server.getInputStream());
			out.writeObject(request);
			out.flush();
			if(request.contains("GET"))
			cacheContent=(String)in.readObject();
			sp=(Integer) in.readObject();
			swaps=sp.toString(sp.intValue());
			
		}catch (ClassNotFoundException | IOException e) {
			System.out.println("connection eror");
			e.printStackTrace();
		}
		
		return  swaps;//data to GUI string or map
	}
	public String getCacheContent() {
		return cacheContent;
	}
}
