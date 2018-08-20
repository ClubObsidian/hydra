package com.clubobsidian.hydra.plugin.config;

import java.nio.file.Paths;
import java.util.Arrays;

import org.cfg4j.provider.ConfigurationProvider;
import org.cfg4j.provider.ConfigurationProviderBuilder;
import org.cfg4j.source.ConfigurationSource;
import org.cfg4j.source.context.environment.Environment;
import org.cfg4j.source.context.environment.ImmutableEnvironment;
import org.cfg4j.source.context.filesprovider.ConfigFilesProvider;
import org.cfg4j.source.files.FilesConfigurationSource;


public class PluginConfigurationProvider {

	private String filesPath;
	public PluginConfigurationProvider(String filesPath)
	{
		this.filesPath = filesPath;
	}

	public ConfigurationProvider configurationProvider() 
	{
		ConfigFilesProvider configFilesProvider = () -> Arrays.asList((Paths.get("plugin.yml")));
		ConfigurationSource source = new FilesConfigurationSource(configFilesProvider);

		Environment environment = new ImmutableEnvironment(this.filesPath);

		return new ConfigurationProviderBuilder()
				.withConfigurationSource(source)
				.withEnvironment(environment)
				.build();
	}
}