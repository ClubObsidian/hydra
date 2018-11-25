package com.clubobsidian.hydra.console;

import org.jline.reader.EndOfFileException;
import org.jline.reader.LineReader;
import org.jline.reader.LineReaderBuilder;
import org.jline.reader.UserInterruptException;

import com.clubobsidian.hydra.Hydra;

public class ConsoleRunnable implements Runnable {

	@Override
	public void run() 
	{
		LineReader reader = LineReaderBuilder.builder().build();
		String prompt = "> ";
		while (Hydra.isRunning())
		{
			String line = null;
			try
			{
				line = reader.readLine(prompt);
				Hydra.getServer().getCommandManager().dispatchCommand(line);
			}
			catch (UserInterruptException e) { }
			catch (EndOfFileException e) 
			{
				return;
			}
		}
	}
}