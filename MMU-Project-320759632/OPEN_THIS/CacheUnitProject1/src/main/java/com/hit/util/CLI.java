package com.hit.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Observable;

public class CLI extends Observable implements Runnable{

	BufferedReader Cin;
	OutputStreamWriter Cout;
	boolean state;

	public CLI(InputStream in,OutputStream out){
		Cin= new BufferedReader(new InputStreamReader(in));
		Cout= new OutputStreamWriter(out);
		state=false;
	}

	public void write(String string) throws IOException
	{
		Cout.write(string);
	}
	
	
	@Override
	public void run() {

		String command ="";
		while(!command.equals("shutdown"))
		{
			try {
				Cout.write("Enter Command: ");
				Cout.flush();
			} catch (IOException e) {
				e.printStackTrace();
			}

			try {
				command=Cin.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(command.equals("start"))
			{
				setState(true);
			}
			else
				if(command.equals("shutdown"))
				{
					try {
						Cin.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						Cout.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					setState(false);

				} else
					try {
						Cout.write("---Wrong Command---\n");
					} catch (IOException e) {
						e.printStackTrace();
					}
		}


	}

	public boolean getState() {
		return state;
	}
	public void setState(boolean state) {
		this.state = state;
		setChanged();
		notifyObservers(state);

	}

}
