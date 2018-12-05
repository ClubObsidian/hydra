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
package com.clubobsidian.hydra.command;

import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.clubobsidian.hydra.Hydra;
import com.clubobsidian.hydra.command.impl.StopCommand;
import com.clubobsidian.hydra.event.command.CommandExecuteEvent;
import com.clubobsidian.hydra.plugin.PluginManager;

public class HydraCommandManager implements CommandManager {

	private Map<String,Command> commands;
	
	public HydraCommandManager()
	{
		this.commands = new ConcurrentHashMap<>();
		this.registerBuiltInCommands();
	}
	
	@Override
	public boolean registerCommand(Command command) 
	{
		return this.registerCommand(command, false);
	}

	@Override
	public boolean registerCommand(Command command, boolean force) 
	{
		if(force)
		{
			this.commands.put(command.getName(), command);
			return true;
		}
		
		if(this.commandExists(command.getName()))
		{
			return false;
		}
		
		this.commands.put(command.getName(), command);
		return true;
	}

	@Override
	public boolean commandExists(String command) 
	{
		return this.commands.keySet().contains(command);
	}

	@Override
	public boolean dispatchCommand(String command) 
	{
		if(command.length() <= 0)
		{
			Hydra.getServer().getLogger().info("Invalid command length for command, length is: " + command.length());
			return false;
		}

		String[] args = command.split(" ");
		if(args.length > 0)
		{
			String name = args[0];
			if(this.commandExists(name))
			{
				if(args.length > 1)
				{
					args = Arrays.copyOfRange(args, 1, args.length);
				}
				else
				{
					args = new String[0];
				}
				
				Command cmd = this.commands.get(name);
				CommandExecuteEvent event = new CommandExecuteEvent(cmd);

				PluginManager eventManager = Hydra.getServer().getPluginManager();
				eventManager.callEvent(event);
				
				if(!event.isCanceled())
				{
					cmd.onCommand(args);
					return true;
				}
			}
			else
			{
				Hydra.getServer().getLogger().info("Unknown command " + name + " please try again");
			}
		}
		return false;
	}
	
	private void registerBuiltInCommands()
	{
		this.registerCommand(new StopCommand());
	}
}