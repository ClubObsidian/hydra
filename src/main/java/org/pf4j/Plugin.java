/*   Copyright 2018 Club Obsidian and contributors.

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
/*
 * Copyright (C) 2012-present the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.pf4j;

import java.nio.file.Path;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.clubobsidian.hydra.plugin.HydraPlugin;

/**
 * This class will be extended by all plugins and
 * serve as the common class between a plugin and the application.
 *
 * @author Decebal Suiu, VirusTotal
 */

/*
 * Modified version of original pf4j Plugin class
 * removes need to use PluginWrapper in the constructor.
 * Removed for convenience for use in Hydra.
 * Also integrates with Hydra by extending the HydraPlugin abstract class.
 */
public class Plugin extends HydraPlugin {

    /**
     * Makes logging service available for descending classes.
     */
    protected final Logger log = LoggerFactory.getLogger(getClass());

    /**
     * Wrapper of the plugin.
     */
    protected PluginWrapper wrapper;

    /**
     * Used to set the PluginWrapper instance for the plugin
     */
    public void setWrapper(PluginWrapper wrapper)
    {
    	if(wrapper == null)
    	{
    		this.wrapper = wrapper;
    	}
    }

    /**
     * Retrieves the wrapper of this plug-in.
     */
    public final PluginWrapper getWrapper() 
    {
        return wrapper;
    }

    /**
     * Start method is called by the application when the plugin is loaded.
     */
    protected void start() throws PluginException 
    {
    	this.onEnable();
    }

    /**
     * Stop method is called by the application when the plugin is unloaded.
     */
    protected void stop() throws PluginException 
    {
    	this.onDisable();
    }

	@Override
	public Path getPath() 
	{
		return this.wrapper.getPluginPath();
	}

	@Override
	public String getName() 
	{
		return this.wrapper.getPluginId();
	}
}
