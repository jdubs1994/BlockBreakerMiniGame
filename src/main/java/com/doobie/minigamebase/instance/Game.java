package com.doobie.minigamebase.instance;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.UUID;

public class Game {

	private HashMap<UUID, Integer> points;
	private Arena arena;

	public Game(Arena arena) {
		this.arena = arena;
		points = new HashMap<>();
	}

	public void start() {
		arena.setState(GameState.LIVE);
		arena.sendMessage(ChatColor.GREEN + "Game started!");

		for (UUID uuid : arena.getPlayers()) {
			points.put(uuid, 0);
		}

	}

	public void addPoint(Player player) {
		int playerPoints = points.get(player.getUniqueId()) +1;
		if(playerPoints == 20) {
			arena.sendMessage(ChatColor.GOLD + player.getName() + " has won the game!");
			arena.reset(true);
		}
		else {
			player.sendMessage(ChatColor.GREEN + "+0 POINT!");
			points.replace(player.getUniqueId(), playerPoints);
		}
	}


}
