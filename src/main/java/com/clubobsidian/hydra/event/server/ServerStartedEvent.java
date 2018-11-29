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
package com.clubobsidian.hydra.event.server;

import com.clubobsidian.hydra.server.Server;
import com.clubobsidian.trident.Event;

public class ServerStartedEvent extends Event {

	private Server server;
	public ServerStartedEvent(Server server)
	{
		this.server = server;
	}
	
	public Server getServer()
	{
		return this.server;
	}
}