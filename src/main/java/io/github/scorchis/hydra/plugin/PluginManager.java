package io.github.scorchis.hydra.plugin;

import java.io.File;
import java.util.List;

import com.clubobsidian.trident.Event;

public interface PluginManager {

	public List<HydraPlugin> getLoadedPlugins();
	
	public void callEvent(Event event);
	
	public void enablePlugin(HydraPlugin plugin);
	
	public void enablePlugin(File file);
	
	public void disablePlugin(HydraPlugin plugin);	
	
}