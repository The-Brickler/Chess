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
	private BufferedReader input;
	private BufferedWriter output;
	private Controller app;
	
	public ProcessController(Controller app, String file)
	{
		this.app = app;
		
		runtime = Runtime.getRuntime();
		
		try
		{
			process = runtime.exec(file);
			input = new BufferedReader(new InputStreamReader(process.getInputStream()));
			output = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	public void sendInput(String textToSend)
	{
		
	}
	
	public String getLine()
	{
		String line;
		try
		{
			line = input.readLine();
		}
		catch (IOException error)
		{
			line = "";
			app.handleError(error);
		}
		
		return line;
	}
	
	public void sendCommand(String command)
	{
		try
		{
			output.write(command);
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
