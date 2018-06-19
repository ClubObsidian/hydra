package io.github.scorchis.hydra.plugin.config;

public class PluginConfiguration {

	private PluginConfigurationProvider configurationProvider;
	public PluginConfiguration(PluginConfigurationProvider configurationProvider)
	{
		this.configurationProvider = configurationProvider;
	}
	
	public String getName()
	{
		return this.configurationProvider.configurationProvider().getProperty("name", String.class);
	}
	
	public String getMain()
	{
		return this.configurationProvider.configurationProvider().getProperty("main", String.class);
	}
	
	public String getVersion()
	{
		return this.configurationProvider.configurationProvider().getProperty("version", String.class);
	}
	
	public String[] getDependencies()
	{
		return this.configurationProvider.configurationProvider().getProperty("dependencies", String[].class);
	}
}