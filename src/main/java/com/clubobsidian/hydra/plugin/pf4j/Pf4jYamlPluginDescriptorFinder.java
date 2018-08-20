package com.clubobsidian.hydra.plugin.pf4j;

import java.nio.file.Files;
import java.nio.file.Path;

import org.pf4j.DefaultPluginDescriptor;
import org.pf4j.PluginDependency;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginException;
import org.pf4j.util.FileUtils;

import com.clubobsidian.hydra.plugin.config.PluginConfiguration;
import com.clubobsidian.hydra.plugin.config.PluginConfigurationProvider;

public class Pf4jYamlPluginDescriptorFinder implements PluginDescriptorFinder {

	@Override
	public boolean isApplicable(Path pluginPath) 
	{
		 return Files.exists(pluginPath) && (Files.isDirectory(pluginPath) || FileUtils.isJarFile(pluginPath));
	}

	@Override
	public PluginDescriptor find(Path pluginPath) throws PluginException 
	{
		
		PluginConfigurationProvider configurationProvider = new PluginConfigurationProvider(pluginPath.toString());
		PluginConfiguration pluginConfig = new PluginConfiguration(configurationProvider);
		String name = pluginConfig.getName();
		String description = "";
		String main = pluginConfig.getMain();
		String version = pluginConfig.getVersion();
		String requires = "*";
		String provider = "";
		String license = "";
		DefaultPluginDescriptor pluginDescriptor = new DefaultPluginDescriptor(name, description, main, version, requires, provider, license);
		String[] dependencies = pluginConfig.getDependencies();
		for(String depend : dependencies)
		{
			pluginDescriptor.addDependency(new PluginDependency(depend));
		}
		return pluginDescriptor;
	}
}