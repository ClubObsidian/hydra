package io.github.scorchis.hydra;

import io.github.scorchis.hydra.plugin.PluginManager;

public interface Server {

	public PluginManager getPluginManager();
	
	public void start();
	
	public void stop();
}