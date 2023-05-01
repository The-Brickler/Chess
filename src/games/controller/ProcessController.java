package games.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ProcessController
{
	private Runtime runtime;
	
	private Process process;
	private BufferedWriter output;
	private Controller app;
	
	public ProcessController(Controller app, String file)
	{
		this.app = app;
		
		runtime = Runtime.getRuntime();
		
		try
		{
			process = runtime.exec(file);
			output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	public String getLine()
	{
		InputStream input = process.getInputStream();
		String data = "";
		try
		{
			while (input.available() > 0)
			{
				System.out.println(input.available());
				
				int value = input.read();
				if (value > -1)
				{
					data += (char) value;
				}
			}
			
			System.out.println("end of while");
			
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
		return data;
	}
	
	public void sendCommand(String command)
	{
		try
		{
			output.write(command, 0, command.length());
			output.flush();
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
		
	}
	
	public boolean isAlive()
	{
		return process.isAlive();
	}
}
