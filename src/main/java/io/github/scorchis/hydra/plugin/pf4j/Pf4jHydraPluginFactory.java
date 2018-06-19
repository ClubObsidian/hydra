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
package io.github.scorchis.hydra.plugin.pf4j;

import org.pf4j.DefaultExtensionFactory;
import org.pf4j.Plugin;
import org.pf4j.PluginFactory;
import org.pf4j.PluginWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

/**
 * The Hydra implementation for {@link PluginFactory}.
 * It uses {@link Class#newInstance()} method.
 *
 * @author Decebal Suiu, VirusTotal
 */
public class Pf4jHydraPluginFactory implements PluginFactory {

	/*
	 * Modified implementation of DefaultPluginFactory from pf4j
	 * This version removes the need for a PluginWrapper in the constructor
	 */
	
    private static final Logger log = LoggerFactory.getLogger(DefaultExtensionFactory.class);

    /**
     * Creates a plugin instance. If an error occurs than that error is logged and the method returns null.
     * @param pluginWrapper
     * @return
     */
    @Override
    public Plugin create(final PluginWrapper pluginWrapper) {
        String pluginClassName = pluginWrapper.getDescriptor().getPluginClass();
        log.debug("Create instance for plugin '{}'", pluginClassName);

        Class<?> pluginClass;
        try {
            pluginClass = pluginWrapper.getPluginClassLoader().loadClass(pluginClassName);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
            return null;
        }

        // once we have the class, we can do some checks on it to ensure
        // that it is a valid implementation of a plugin.
        int modifiers = pluginClass.getModifiers();
        if (Modifier.isAbstract(modifiers) || Modifier.isInterface(modifiers)
                || (!Plugin.class.isAssignableFrom(pluginClass))) {
            log.error("The plugin class '{}' is not valid", pluginClassName);
            return null;
        }

        // create the plugin instance
        try {
            Constructor<?> constructor = pluginClass.getDeclaredConstructors()[0];
            Plugin plugin = (Plugin) constructor.newInstance();
            plugin.setWrapper(pluginWrapper);
            return plugin;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }

        return null;
    }
}