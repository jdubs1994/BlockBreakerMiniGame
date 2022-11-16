package com.doobie.minigamebase.instance;

import org.bukkit.ChatColor;

public class Game {
	private Arena arena;

	public Game(Arena arena) {
		this.arena = arena;
	}

	public void start() {
		arena.setState(GameState.LIVE);
		arena.sendMessage(ChatColor.GREEN + "Game started!");
	}
}
