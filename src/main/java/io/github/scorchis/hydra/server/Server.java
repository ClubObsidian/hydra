package io.github.scorchis.hydra.server;

import io.github.scorchis.hydra.plugin.PluginManager;

public interface Server {

	public PluginManager getPluginManager();
	
	public void start();
	
	public void stop();
}