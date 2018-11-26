package com.clubobsidian.hydra.server.module;

import com.clubobsidian.hydra.command.CommandManager;
import com.clubobsidian.hydra.command.HydraCommandManager;
import com.clubobsidian.hydra.plugin.PluginManager;
import com.clubobsidian.hydra.plugin.pf4j.Pf4jHydraPluginManager;
import com.clubobsidian.hydra.server.HydraServer;
import com.clubobsidian.hydra.server.Server;
import com.google.inject.Binder;
import com.google.inject.Module;

public class HydraServerModule implements Module {


	@Override
	public void configure(Binder binder) 
	{
		binder.bind(Server.class).to(HydraServer.class);
		binder.bind(PluginManager.class).to(Pf4jHydraPluginManager.class);
		binder.bind(CommandManager.class).to(HydraCommandManager.class);
	}
}