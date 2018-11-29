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
package com.clubobsidian.hydra.plugin.pf4j;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

import org.pf4j.CompoundPluginDescriptorFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginException;
import org.pf4j.PluginFactory;
import org.pf4j.PluginWrapper;

import com.clubobsidian.hydra.plugin.HydraPlugin;
import com.clubobsidian.hydra.plugin.PluginManager;
import com.clubobsidian.trident.Event;
import com.clubobsidian.trident.EventManager;
import com.clubobsidian.trident.Listener;
import com.clubobsidian.trident.impl.javaassist.JavaAssistEventManager;

public class Pf4jHydraPluginManager extends DefaultPluginManager implements PluginManager {

	private EventManager eventManager;
	private Map<HydraPlugin, Queue<Listener>> pluginListeners;
	public Pf4jHydraPluginManager()
	{
		this.eventManager = new JavaAssistEventManager();
		this.pluginListeners = new ConcurrentHashMap<>();
	}
	
	@Override
	protected PluginDescriptorFinder createPluginDescriptorFinder()
	{
		return new CompoundPluginDescriptorFinder().add(new Pf4jYamlPluginDescriptorFinder());
	}

	@Override
	protected PluginFactory createPluginFactory()
	{
		return new Pf4jHydraPluginFactory();
	}

	@Override
	public void enablePlugins()
	{
		if(Files.notExists(this.getPluginsRoot()))
		{
			this.getPluginsRoot().toFile().mkdir();
		}
		super.loadPlugins();
		this.startPlugins();
	}

	@Override
	public void disablePlugins()
	{
		this.stopPlugins();
	}

	@Override
	public List<HydraPlugin> getLoadedPlugins() 
	{
		List<HydraPlugin> plugins = new ArrayList<>();
		for(PluginWrapper wrapper : this.getPlugins())
		{
			plugins.add(wrapper.getPlugin());
		}
		return plugins;
	}
	

	@Override
	public void callEvent(Event event) 
	{
		this.eventManager.callEvent(event);
	}
	
	@Override
	public void registerEvents(Listener listener, HydraPlugin plugin) 
	{
		Queue<Listener> pluginListeners = this.pluginListeners.get(plugin);
		if(pluginListeners == null)
		{
			pluginListeners = new ConcurrentLinkedQueue<>();
			this.pluginListeners.put(plugin, pluginListeners);
		}
		boolean registered = this.eventManager.registerEvents(listener);
		
		//Don't add if not registered
		if(registered)
		{
			pluginListeners.add(listener);
		}
	}

	@Override
	public void unregisterEvents(Listener listener, HydraPlugin plugin)
	{
		Queue<Listener> pluginListeners = this.pluginListeners.remove(plugin);
		if(pluginListeners != null)
		{
			Iterator<Listener> it = pluginListeners.iterator();
			while(it.hasNext())
			{
				Listener next = it.next();
				this.eventManager.unregisterEvents(next);
			}
		}
	}
	
	@Override
	public void enablePlugin(HydraPlugin plugin) 
	{
		try 
		{
			this.loadPluginFromPath(plugin.getPath());
		} 
		catch (PluginException e) 
		{
			e.printStackTrace();
		}
		
	}

	@Override
	public void enablePlugin(File file) 
	{
		try 
		{
			this.loadPluginFromPath(file.toPath());
		} 
		catch (PluginException e) 
		{
			e.printStackTrace();
		}
	}

	@Override
	public void disablePlugin(HydraPlugin plugin) 
	{
		this.unloadPlugin(plugin.getName());
	}
}