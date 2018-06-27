package io.github.scorchis.hydra.plugin.pf4j;

import java.io.File;
import java.util.List;

import org.pf4j.CompoundPluginDescriptorFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginException;
import org.pf4j.PluginFactory;

import com.clubobsidian.trident.Event;
import com.clubobsidian.trident.EventManager;
import com.clubobsidian.trident.impl.javaassist.JavaAssistEventManager;

import io.github.scorchis.hydra.plugin.PluginManager;
import io.github.scorchis.hydra.plugin.HydraPlugin;

public class Pf4jHydraPluginManager extends DefaultPluginManager implements PluginManager {

	private EventManager eventManager;
	public Pf4jHydraPluginManager()
	{
		this.eventManager = new JavaAssistEventManager();
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
	public List<HydraPlugin> getLoadedPlugins() 
	{
		
	}

	@Override
	public void callEvent(Event event) 
	{
		this.eventManager.callEvent(event);
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