package com.clubobsidian.hydra.plugin.pf4j;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

import org.pf4j.DefaultPluginDescriptor;
import org.pf4j.PluginDependency;
import org.pf4j.PluginDescriptor;
import org.pf4j.PluginDescriptorFinder;
import org.pf4j.PluginException;
import org.pf4j.util.FileUtils;

import com.clubobsidian.wrappy.Configuration;
import com.clubobsidian.wrappy.ConfigurationType;

public class Pf4jYamlPluginDescriptorFinder implements PluginDescriptorFinder {

	@Override
	public boolean isApplicable(Path pluginPath) 
	{
		 return Files.exists(pluginPath) && (Files.isDirectory(pluginPath) || FileUtils.isJarFile(pluginPath));
	}

	@Override
	public PluginDescriptor find(Path pluginPath) throws PluginException 
	{

		JarFile jar = null;
		try 
		{
			jar = new JarFile(pluginPath.toFile());
			ZipEntry entry = jar.getEntry("plugin.yml");
			if(entry != null)
			{
				InputStream pluginStream = jar.getInputStream(entry);
				Configuration pluginConfig = Configuration.load(pluginStream, ConfigurationType.YAML);

				String name = pluginConfig.getString("name");
				String description = "";
				String main = pluginConfig.getString("main");
				String version = pluginConfig.getString("version");
				String requires = "*";
				String provider = "";
				String license = "";
				DefaultPluginDescriptor pluginDescriptor = new DefaultPluginDescriptor(name, description, main, version, requires, provider, license);
				List<String> dependenciesList = pluginConfig.getStringList("dependencies");
				String[] dependencies = dependenciesList.toArray(new String[dependenciesList.size()]);
				for(String depend : dependencies)
				{
					pluginDescriptor.addDependency(new PluginDependency(depend));
				}
				pluginStream.close();

				return pluginDescriptor;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try 
			{
				jar.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
		return null;

	}
}