/*
   Copyright 2018 Club Obsidian and contributors.

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
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
		while (Hydra.getServer().isRunning())
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