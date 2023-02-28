package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.ultilidade.Strings;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Map;

public class Home implements CommandExecutor {

    private Map<String, Map<String, Location>> homes;

    public Home(Map<String, Map<String, Location>> homes) {
        this.homes = homes;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§cEste comando só pode ser executado por jogadores.");
            return true;
        }

        Player player = (Player) sender;
        String playerName = player.getUniqueId().toString();
        Map<String, Location> playerHomes = homes.get(playerName);

        if (!player.hasPermission("cessentials.home")){
            player.sendMessage(Strings.prefix + "§cSem Permissão");
            return false;
        }
        // Verifica se o jogador tem alguma casa definida
        if (playerHomes == null || playerHomes.isEmpty()) {
            player.sendMessage(Strings.prefix + "§eVocê ainda não definiu nenhuma casa.");
            return true;
        }

        // Teleporta o jogador para a casa especificada (ou a primeira casa se nenhum nome for especificado)
        String homeName = args.length > 0 ? args[0] : (String) playerHomes.keySet().toArray()[0];
        Location homeLocation = playerHomes.get(homeName);
        if (homeLocation == null) {
            player.sendMessage(Strings.prefix + "§eCasa \"" + homeName + "\" §enão encontrada.");
            return true;
        }
        player.teleport(homeLocation);

        player.sendMessage(Strings.prefix + "§aTeleportado para a casa \"" + homeName + "\"§a.");
        return true;
    }
}