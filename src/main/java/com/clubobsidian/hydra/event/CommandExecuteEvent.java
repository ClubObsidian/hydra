package com.clubobsidian.hydra.event;

import com.clubobsidian.hydra.command.Command;
import com.clubobsidian.trident.Cancelable;
import com.clubobsidian.trident.Event;

public class CommandExecuteEvent extends Event implements Cancelable {

	private Command command;
	private boolean canceled;
	public CommandExecuteEvent(Command command) 
	{
		this.command = command;
		this.canceled = false;
	}

	public Command getCommand()
	{
		return this.command;
	}

	@Override
	public boolean isCanceled() 
	{
		return this.canceled;
	}

	@Override
	public void setCanceled(boolean cancel) 
	{
		this.canceled = cancel;
	}
}