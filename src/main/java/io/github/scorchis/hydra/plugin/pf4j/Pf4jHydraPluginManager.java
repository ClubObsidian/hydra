package io.github.scorchis.hydra.plugin.pf4j;

import java.util.List;

import org.pf4j.CompoundPluginDescriptorFinder;
import org.pf4j.DefaultPluginManager;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginFactory;

import io.github.scorchis.hydra.plugin.PluginManager;
import io.github.scorchis.hydra.plugin.HydraPlugin;

public class Pf4jHydraPluginManager extends DefaultPluginManager implements PluginManager {

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
}