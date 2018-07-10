package io.github.scorchis.hydra;

public class Hydra {

	private static HydraServer hydraServer;
	
	public static void main(String[] args)
	{
		Hydra.hydraServer = new HydraServer();
		Hydra.getHydraServer().start();
	}
	
	public static HydraServer getHydraServer()
	{
		return Hydra.hydraServer;
	}
}