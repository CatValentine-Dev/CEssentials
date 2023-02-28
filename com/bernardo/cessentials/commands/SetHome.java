package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.ultilidade.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;

public class SetHome implements CommandExecutor {

    private Map<String, Map<String, Location>> homes;
    private int maxHomes;

    public SetHome(Map<String, Map<String, Location>> homes, int maxHomes) {
        this.homes = homes;
        this.maxHomes = maxHomes;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Este comando só pode ser executado por jogadores.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getUniqueId().toString();
        Map<String, Location> playerHomes = homes.get(playerName);

        if (!player.hasPermission("cessentials.sethome")){
            player.sendMessage(Strings.prefix + "§cSem Permissão");
            return false;
        }

        if (playerHomes != null && playerHomes.size() >= maxHomes) {
            player.sendMessage(Strings.prefix + "§cVocê já definiu o número máximo de casas permitido (" + maxHomes + ").");
            return true;
        }

        String homeName = args.length > 0 ? args[0] : "home";
        if (playerHomes == null) {
            playerHomes = new HashMap<String, Location>();
            homes.put(playerName, playerHomes);
        }
        playerHomes.put(homeName, player.getLocation());

        player.sendMessage(Strings.prefix + "§aCasa definida com sucesso.");
        return true;
    }
}