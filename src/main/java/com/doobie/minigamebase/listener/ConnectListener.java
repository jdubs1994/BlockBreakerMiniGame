package com.doobie.minigamebase.listener;

import com.doobie.minigamebase.MinigameBase;
import com.doobie.minigamebase.instance.Arena;
import com.doobie.minigamebase.manager.ConfigManager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class ConnectListener implements Listener {


	private MinigameBase minigameBase;

	public ConnectListener(MinigameBase minigameBase) {
		this.minigameBase = minigameBase;
	}

	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		event.getPlayer().teleport(ConfigManager.getLobbySpawn());
	}
	@EventHandler
	public void onQuit(PlayerQuitEvent event) {
		Arena arena = minigameBase.getArenaManager().getArena(event.getPlayer());
		if(arena != null) {
			arena.removePlayer(event.getPlayer());
		}
	}
}
