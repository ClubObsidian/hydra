package io.github.scorchis.hydra.plugin;

import java.util.List;

public interface PluginManager {

	public List<HydraPlugin> loadedPlugins();
	
	public void start();
	
	public void stop();
}
