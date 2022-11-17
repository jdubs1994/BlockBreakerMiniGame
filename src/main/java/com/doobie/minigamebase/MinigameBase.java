package com.doobie.minigamebase;

import com.doobie.minigamebase.command.ArenaCommand;
import com.doobie.minigamebase.listener.ConnectListener;
import com.doobie.minigamebase.listener.GameListener;
import com.doobie.minigamebase.manager.ArenaManager;
import com.doobie.minigamebase.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class MinigameBase extends JavaPlugin {

	private ArenaManager arenaManager;

	@Override
	public void onEnable() {
		ConfigManager.setConfig(this);
		arenaManager = new ArenaManager(this);

		Bukkit.getPluginManager().registerEvents(new ConnectListener(this), this);
		Bukkit.getPluginManager().registerEvents(new GameListener(this), this);
		getCommand("arena").setExecutor(new ArenaCommand(this));

	}

	public ArenaManager getArenaManager() {
		return arenaManager;
	}

	@Override
	public void onDisable() {
		// Plugin shutdown logic
	}
}
