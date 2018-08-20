package io.github.scorchis.hydra.server;

import io.github.scorchis.hydra.plugin.PluginManager;
import io.github.scorchis.hydra.plugin.pf4j.Pf4jHydraPluginManager;

public class HydraServer implements Server {

	private Pf4jHydraPluginManager pluginManager;
	public HydraServer()
	{
		this.pluginManager = new Pf4jHydraPluginManager();
	}
	
	@Override
	public PluginManager getPluginManager() 
	{
		return this.pluginManager;
	}

	@Override
	public void start() 
	{
		this.pluginManager.loadPlugins();
		this.pluginManager.startPlugins();
	}

	@Override
	public void stop() 
	{
		this.pluginManager.stopPlugins();
	}
}