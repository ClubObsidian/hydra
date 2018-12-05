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

import org.apache.log4j.BasicConfigurator;

import com.clubobsidian.hydra.server.Server;
import com.clubobsidian.hydra.server.module.ServerModule;

import com.google.inject.Guice;
import com.google.inject.Injector;

public class Hydra {

	private static Server server;

	public static void main(String[] args)
	{
		File hydraLogFile = new File("hydra.log");
		if(hydraLogFile.exists())
			hydraLogFile.delete();

		BasicConfigurator.configure();
		
		Injector injector = Guice.createInjector(new ServerModule());
		Hydra.server = injector.getInstance(Server.class);
		
		Hydra.server.start();
		
		Thread th = new Thread()
		{
			@Override
			public void run()
			{
				while(Hydra.getServer().isRunning())
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
	}

	public static Server getServer()
	{
		return Hydra.server;
	}
}