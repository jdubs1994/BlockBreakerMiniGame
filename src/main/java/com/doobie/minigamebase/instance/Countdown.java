package com.doobie.minigamebase.instance;

import com.doobie.minigamebase.MinigameBase;
import com.doobie.minigamebase.manager.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitRunnable;

public class Countdown extends BukkitRunnable {

	private MinigameBase minigameBase;
	private Arena arena;
	private int countdownSeconds;

	public Countdown(MinigameBase minigameBase, Arena arena) {
		this.minigameBase = minigameBase;
		this.arena = arena;
		this.countdownSeconds = ConfigManager.getCountdownSeconds();
	}

	public void start() {
		arena.setState(GameState.COUNTDOWN);
		runTaskTimer(minigameBase, 0, 20);
	}

	@Override
	public void run() {
		if (countdownSeconds == 0) {
			arena.setState(GameState.LIVE);
			cancel();
			arena.start();

		} else {
			if(countdownSeconds <= 10 || countdownSeconds % 15 == 0) {
				arena.sendMessage(ChatColor.GREEN + "Game starting in " + countdownSeconds + " second " + (countdownSeconds == 1 ? "" : "s")+ ".");
			}
			countdownSeconds--;
		}
	}
}
