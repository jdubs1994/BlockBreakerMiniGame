package com.doobie.minigamebase;

import com.doobie.minigamebase.manager.ArenaManager;
import com.doobie.minigamebase.manager.ConfigManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameBase extends JavaPlugin {

	private ArenaManager arenaManager;

	@Override
	public void onEnable() {
		// Plugin startup logic
		ConfigManager.setConfig(this);
		arenaManager = new ArenaManager(this);
	}

	public ArenaManager getArenaManager() {
		return arenaManager;
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
