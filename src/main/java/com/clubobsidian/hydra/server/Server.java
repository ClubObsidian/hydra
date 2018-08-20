package com.clubobsidian.hydra.server;

import com.clubobsidian.hydra.plugin.PluginManager;

public interface Server {

	public PluginManager getPluginManager();
	
	public void start();
	
	public void stop();
}