package com.booksaw.betterTeams.commands.team;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.booksaw.betterTeams.PlayerRank;
import com.booksaw.betterTeams.Team;
import com.booksaw.betterTeams.TeamPlayer;
import com.booksaw.betterTeams.commands.SubCommand;

import net.md_5.bungee.api.ChatColor;

/**
 * This command is for /team if the command is unknown
 * 
 * @author booksaw
 *
 */
public class HelpCommand extends SubCommand {

	@Override
	public String onCommand(CommandSender sender, String label, String[] args) {

		if (!(sender instanceof Player)) {
			showAll(sender, label);
			return null;
		}

		Player p = (Player) sender;
		Team team = Team.getTeam(p);

		if (team == null) {
			sender.sendMessage(createHelpMessage(label, "join <team>", "Join the specified team"));
			sender.sendMessage(createHelpMessage(label, "create <name>", "Create a team with the specified name"));
		} else {

			sender.sendMessage(createHelpMessage(label, "leave", "Leave your current team"));
			sender.sendMessage(createHelpMessage(label, "home", "Teleports you to your team's home"));
			sender.sendMessage(createHelpMessage(label, "chat [message]", "Send a message only to your team"));

			TeamPlayer player = team.getTeamPlayer(p);
			if (player.getRank() == PlayerRank.ADMIN || player.getRank() == PlayerRank.OWNER) {
				sender.sendMessage(
						createHelpMessage(label, "invite <player>", "Invite the specified player to your team"));
				sender.sendMessage(createHelpMessage(label, "kick <player>", "Kick that player from your team"));
				sender.sendMessage(
						createHelpMessage(label, "ban <player>", "Bans the specified player from your team"));
				sender.sendMessage(
						createHelpMessage(label, "unban <player>", "Unbans the specified player from your team"));
				sender.sendMessage(createHelpMessage(label, "sethome", "Sets your team's home"));
			}
			if (player.getRank() == PlayerRank.OWNER) {
				sender.sendMessage(createHelpMessage(label, "disband", "Disband your current team"));
				sender.sendMessage(createHelpMessage(label, "description [description]",
						"View and change your team's description"));
				sender.sendMessage(createHelpMessage(label, "name [name]", "View and change your team's name"));
				sender.sendMessage(createHelpMessage(label, "open", "Toggle if the team is invite only"));
				sender.sendMessage(
						createHelpMessage(label, "promote <player>", "Promote the specified player within your team"));
				sender.sendMessage(
						createHelpMessage(label, "demote <player>", "Demote the specified player within your team"));
			}

		}

		sender.sendMessage(createHelpMessage(label, "help", "View the this help page"));
		sender.sendMessage(
				createHelpMessage(label, "info [team/player]", "View information about the specified player / team"));
		return null;
	}

	/**
	 * This is used to show all help messages to the user, not just the commands
	 * which they can run at the moment
	 * 
	 * @param sender
	 * @param label
	 */
	public void showAll(CommandSender sender, String label) {
		sender.sendMessage(createHelpMessage(label, "help", "View the this help page"));
		sender.sendMessage(createHelpMessage(label, "create <name>", "Create a team with the specified name"));
		sender.sendMessage(createHelpMessage(label, "leave", "Leave your current team"));
		sender.sendMessage(createHelpMessage(label, "disband", "Disband your current team"));
		sender.sendMessage(
				createHelpMessage(label, "description [description]", "View and change your team's description"));
		sender.sendMessage(createHelpMessage(label, "name [name]", "View and change your team's name"));
		sender.sendMessage(createHelpMessage(label, "open", "Toggle if the team is invite only"));
		sender.sendMessage(createHelpMessage(label, "invite <player>", "Invite the specified player to your team"));
		sender.sendMessage(createHelpMessage(label, "join <team>", "Join the specified team"));
		sender.sendMessage(
				createHelpMessage(label, "info [team/player]", "View information about the specified player / team"));
		sender.sendMessage(createHelpMessage(label, "kick <player>", "Kick that player from your team"));
		sender.sendMessage(
				createHelpMessage(label, "promote <player>", "Promote the specified player within your team"));
		sender.sendMessage(createHelpMessage(label, "demote <player>", "Demote the specified player within your team"));
		sender.sendMessage(createHelpMessage(label, "home", "Teleports you to your team's home"));
		sender.sendMessage(createHelpMessage(label, "sethome", "Sets your team's home"));
		sender.sendMessage(createHelpMessage(label, "ban <player>", "Bans the specified player from your team"));
		sender.sendMessage(createHelpMessage(label, "unban <player>", "Unbans the specified player from your team"));
		sender.sendMessage(createHelpMessage(label, "chat [message]", "Send a message only to your team"));

	}

	/**
	 * Used to create a formatted help message to explain what a command does to the
	 * user
	 * 
	 * @param label       the base command
	 * @param commandPath the rest of the command (i.e. help \<param\>)
	 * @param description the description of the command
	 * @return the created message relating to that command
	 */
	public String createHelpMessage(String label, String commandPath, String description) {
		return ChatColor.AQUA + "/" + label + " " + commandPath + ChatColor.WHITE + " - " + ChatColor.GOLD
				+ description;
	}

	@Override
	public String getCommand() {
		return null;
	}

	@Override
	public int getMinimumArguments() {
		return 0;
	}

}