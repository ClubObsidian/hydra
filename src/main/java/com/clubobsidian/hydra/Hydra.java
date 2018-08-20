package com.clubobsidian.hydra;

import com.clubobsidian.hydra.server.HydraServer;
import com.clubobsidian.hydra.server.Server;

public class Hydra {

	private static Server hydraServer;
	
	public static void main(String[] args)
	{
		Hydra.hydraServer = new HydraServer();
		Hydra.hydraServer.start();
	}
	
	public static Server getServer()
	{
		return Hydra.hydraServer;
	}
}