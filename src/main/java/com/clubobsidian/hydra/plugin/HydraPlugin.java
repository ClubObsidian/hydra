package com.clubobsidian.hydra.plugin;

import java.nio.file.Path;

/**
 * Extended by different plugin implementations
 * 
 */

public abstract class HydraPlugin  {
	
	public void onEnable() {}
	
	public void onDisable() {}
	
	public abstract Path getPath();
	
	public abstract String getName();
	
}