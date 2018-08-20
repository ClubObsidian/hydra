package io.github.scorchis.hydra;

import io.github.scorchis.hydra.server.HydraServer;
import io.github.scorchis.hydra.server.Server;

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