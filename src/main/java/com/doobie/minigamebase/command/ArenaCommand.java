package com.doobie.minigamebase.command;

import com.doobie.minigamebase.MinigameBase;
import com.doobie.minigamebase.instance.Arena;
import com.doobie.minigamebase.instance.GameState;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class ArenaCommand implements CommandExecutor
{
	private MinigameBase minigameBase;

	public ArenaCommand(MinigameBase minigameBase) {
		this.minigameBase = minigameBase;
	}

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (sender instanceof Player) {
			Player player = (Player) sender;
			if (args.length == 1 && args[0].equalsIgnoreCase("list")) {
				player.sendMessage(ChatColor.GREEN + "These are the available arenas:");
				for (Arena arena : minigameBase.getArenaManager().getArenas()) {
					player.sendMessage(ChatColor.GREEN + "- " + arena.getId() + " (" + arena.getState().name() + ")");
				}

			} else if (args.length == 1 && args[0].equalsIgnoreCase("leave")) {
				Arena arena = minigameBase.getArenaManager().getArena(player);
				if (arena != null) {
					player.sendMessage(ChatColor.RED + "You have left the arena.");
					arena.removePlayer(player);
				} else {
					player.sendMessage(ChatColor.RED + "You are not in an arena.");
				}

			} else if (args.length == 2 && args[0].equalsIgnoreCase("join")) {
				if (minigameBase.getArenaManager().getArena(player) != null) {
					player.sendMessage(ChatColor.GREEN + "You have already joined an arena.");
					return false;
				} else {
					player.sendMessage(ChatColor.RED + "That arena does not exist.");
				}
				int id;
				try {
					id = Integer.parseInt(args[1]);

				}catch (NumberFormatException e) {
					player.sendMessage(ChatColor.RED + "That is not a valid number.");
					return false;
				}

				if (id >= 0 && id < minigameBase.getArenaManager().getArenas().size()) {
					Arena arena = minigameBase.getArenaManager().getArena(id);
					if(arena.getState() == GameState.RECRUITING || arena.getState() == GameState.COUNTDOWN) {
						player.sendMessage(ChatColor.GREEN + "You have joined arena " + id + ".");
						arena.addPlayer(player);
					}
				} else {
					player.sendMessage(ChatColor.RED + "You cannot join this arena right now.");
				}

			}  else {
				player.sendMessage(ChatColor.RED + "Invalid usage! These are the options");
				player.sendMessage(ChatColor.RED + "/arena list");
				player.sendMessage(ChatColor.RED + "/arena leave");
				player.sendMessage(ChatColor.RED + "/arena join <id>");
			}
		}
		return false;
		}
	}