package com.doobie.minigamebase.instance;

import com.doobie.minigamebase.MinigameBase;
import com.doobie.minigamebase.manager.ConfigManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Arena {

	private MinigameBase minigameBase;
	private int id;
	private Location spawn;

	private List<UUID> players;
	private GameState state;
	private Countdown countdown;

	private Game game;



	public Arena(MinigameBase minigameBase, int id, Location spawn) {
		this.id = id;
		this.spawn = spawn;
		this.state = GameState.RECRUITING;
		this.players = new ArrayList<>();
		this.countdown = new Countdown(minigameBase, this);
		this.game = new Game(this);
		this.minigameBase = minigameBase;
	}

	public void sendMessage (String message) {
		for(UUID uuid: players) {
			Bukkit.getPlayer(uuid).sendMessage(message);
		}
	}

	public void sendTitle (String title, String subtitle) {
		for(UUID uuid: players) {
			Bukkit.getPlayer(uuid).sendTitle(title, subtitle);
		}
	}

	public void addPlayer(Player player) {
		players.add(player.getUniqueId());
		player.teleport(spawn);
		if(state.equals(GameState.RECRUITING)) {
			if(players.size() >= ConfigManager.getRequiredPlayers()) {
				countdown.start();
			}
		}
	}
	public void start() {
		game.start();
	}

	public void reset(boolean kickPlayers) {
		if(kickPlayers) {
			Location lobbySpawn = ConfigManager.getLobbySpawn();
			for (UUID uuid : players) {
				Bukkit.getPlayer(uuid).teleport(lobbySpawn);
			}
			players.clear();
		}
		sendTitle("","");
		state = GameState.RECRUITING;
		countdown.cancel();

		countdown = new Countdown(minigameBase, this);
		game = new Game(this);

	}

	public void removePlayer(Player player) {
		players.remove(player.getUniqueId());
		player.teleport(ConfigManager.getLobbySpawn());
		player.sendTitle("","");

		if(state == GameState.COUNTDOWN && players.size() < ConfigManager.getRequiredPlayers()) {
			sendMessage(ChatColor.RED + "Not enough players to start the game!");
			reset(false);
			return;
		}

		if(state == GameState.LIVE && players.size() < ConfigManager.getRequiredPlayers()) {
			sendMessage(ChatColor.RED + "Not enough players to continue the game!");
			reset(false);
		}


	}

	// Getters
	public int getId() {
		return id;
	}
	public List<UUID> getPlayers() {
		return players;
	}

	public GameState getState() {
		return state;
	}

	public void setState(GameState state) {
		this.state = state;
	}

	public Game getGame() {
		return game;
	}
}
