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
package com.clubobsidian.hydra.event.command;

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