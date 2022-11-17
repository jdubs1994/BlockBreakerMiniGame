package com.doobie.minigamebase.listener;

import com.doobie.minigamebase.MinigameBase;
import com.doobie.minigamebase.instance.Arena;
import com.doobie.minigamebase.instance.GameState;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class GameListener implements Listener {

	private MinigameBase minigameBase;

	public GameListener(MinigameBase minigameBase) {
		this.minigameBase = minigameBase;
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Arena arena = minigameBase.getArenaManager().getArena(event.getPlayer());
		if(arena != null && arena.getState().equals(GameState.LIVE)) {
			arena.getGame().addPoint(event.getPlayer());
		}
	}

}
