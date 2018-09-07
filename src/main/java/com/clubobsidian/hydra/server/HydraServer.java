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
package com.clubobsidian.hydra.server;

import com.clubobsidian.hydra.plugin.PluginManager;
import com.clubobsidian.hydra.plugin.pf4j.Pf4jHydraPluginManager;

public class HydraServer implements Server {

	private Pf4jHydraPluginManager pluginManager;
	public HydraServer()
	{
		this.pluginManager = new Pf4jHydraPluginManager();
	}
	
	@Override
	public PluginManager getPluginManager() 
	{
		return this.pluginManager;
	}

	@Override
	public void start() 
	{
		this.pluginManager.loadPlugins();
		this.pluginManager.startPlugins();
	}

	@Override
	public void stop() 
	{
		this.pluginManager.stopPlugins();
	}
}
