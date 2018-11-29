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
package com.clubobsidian.hydra.server;

import java.util.concurrent.atomic.AtomicBoolean;

import com.clubobsidian.hydra.command.CommandManager;
import com.clubobsidian.hydra.console.ConsoleRunnable;
import com.clubobsidian.hydra.event.server.ServerStartedEvent;
import com.clubobsidian.hydra.plugin.PluginManager;
import com.google.inject.Inject;


public class HydraServer implements Server {

	private PluginManager pluginManager;
	private CommandManager commandManager;
	private Thread consoleThread;
	private AtomicBoolean running;
	
	@Inject
	public HydraServer(PluginManager pluginManager, CommandManager commandManager)
	{
		this.pluginManager = pluginManager;
		this.commandManager = commandManager;
		this.running = new AtomicBoolean(false);
	}
	
	@Override
	public PluginManager getPluginManager() 
	{
		return this.pluginManager;
	}
	
	@Override
	public CommandManager getCommandManager() 
	{
		return this.commandManager;
	}
	
	@Override
	public Thread getConsoleThread()
	{
		return this.consoleThread;
	}

	@Override
	public boolean isRunning() 
	{
		return this.running.get();
	}

	@Override
	public boolean start() 
	{
		if(!this.running.get())
		{
			this.running.set(true);
			this.consoleThread = new Thread(new ConsoleRunnable());
			this.consoleThread.start();
			this.pluginManager.enablePlugins();
			ServerStartedEvent event = new ServerStartedEvent(this);
			this.pluginManager.callEvent(event);
			return true;
		}
		return false;
	}

	@Override
	public boolean stop() 
	{
		this.running.set(false);
		this.pluginManager.disablePlugins();
		return true;
	}
}