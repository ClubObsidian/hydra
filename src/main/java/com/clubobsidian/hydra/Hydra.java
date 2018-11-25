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
package com.clubobsidian.hydra;

import java.io.File;
import java.util.concurrent.atomic.AtomicBoolean;

import org.apache.log4j.BasicConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clubobsidian.hydra.server.HydraServer;
import com.clubobsidian.hydra.server.Server;

public class Hydra {

	private static final Logger logger = LoggerFactory.getLogger(Hydra.class);
	private static Server server;
	private static AtomicBoolean running;

	public static void main(String[] args)
	{
		File hydraLogFile = new File("hydra.log");
		if(hydraLogFile.exists())
			hydraLogFile.delete();

		BasicConfigurator.configure();

		Hydra.server = new HydraServer();
		Hydra.server.start();
		running = new AtomicBoolean(true);
		Thread th = new Thread()
		{
			@Override
			public void run()
			{
				while(running.get())
				{
					try 
					{
						Thread.sleep(1000L);
					} 
					catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
			}
		};
		th.start();

		//Temporary
		//Hydra.running.set(false);
	}

	public static Logger getLogger()
	{
		return Hydra.logger;
	}

	public static Server getServer()
	{
		return Hydra.server;
	}

	public static boolean isRunning()
	{
		return Hydra.running.get();
	}

	public static void shutdown()
	{
		Hydra.running.set(false);
		Hydra.server.stop();
	}
}
