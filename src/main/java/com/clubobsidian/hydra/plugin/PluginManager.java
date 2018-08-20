package com.clubobsidian.hydra.plugin;

import java.io.File;
import java.util.List;

import com.clubobsidian.trident.Event;
import com.clubobsidian.trident.Listener;

public interface PluginManager {

	public List<HydraPlugin> getLoadedPlugins();
	
	public void callEvent(Event event);
	
	public void registerEvents(Listener listener, HydraPlugin plugin);
	
	public void unregisterEvents(Listener listener, HydraPlugin plugin);
	
	public void enablePlugin(HydraPlugin plugin);
	
	public void enablePlugin(File file);
	
	public void disablePlugin(HydraPlugin plugin);	
	
}