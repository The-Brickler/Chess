package games.controller;

import java.io.IOException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * ProcessController will start a process, and handles reading / writing to the console
 * 
 * @author camibrunson
 *
 */
public class ProcessController
{
	private Process process;
	private BufferedReader reader;
	private OutputStreamWriter writer;
	
	private String path;
	
	private Controller app;
	
	/**
	 * The constructor will initialize the path variable
	 * 
	 * @param path
	 * 				The file path of the process to be used
	 * @param app
	 * 				Reference to the main app Controller, used for error handling
	 */
	public ProcessController(String path, Controller app)
	{
		this.path = path;
		this.app = app;
	}
	
	/**
	 * This method will start the process and initialize the reader and writer
	 * 
	 * @return True if the process starts successfully, False if it does not
	 */
	public boolean start()
	{
		try
		{
			process = Runtime.getRuntime().exec(path);
			reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
			writer = new OutputStreamWriter(process.getOutputStream());
		}
		catch (IOException error)
		{
			return false;
		}
		return true;
	}
	
	/**
	 * This method will close the process and it's readers and writers
	 */
	public void stop()
	{
		try
		{
			sendCommand("quit");
			reader.close();
			writer.close();
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	/**
	 * Sends a command to the process
	 * 
	 * @param command
	 * 					The command to be sent
	 */
	public void sendCommand(String command)
	{
		try
		{
			writer.write(command + "\n");
			writer.flush();
		}
		catch (IOException error)
		{
			app.handleError(error);
		}
	}
	
	public String getOutput(int delay)
	{
		return null;
	}
}