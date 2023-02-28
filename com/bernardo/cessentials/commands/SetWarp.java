package com.bernardo.cessentials.commands;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SetWarp implements CommandExecutor {

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setwarp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Este comando só pode ser executado por jogadores.");
                return true;
            }
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage("Use: /setwarp <nome do warp>");
                return true;
            }
            // Salva as coordenadas do jogador em um arquivo de configuração
            Location location = player.getLocation();
            File configFile = new File(getDataFolder(), "warps.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            config.set("warps." + args[0], location);
            try {
                config.save(configFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            player.sendMessage("Warp " + args[0] + " criado.");
            return true;
        } else if (cmd.getName().equalsIgnoreCase("warp")) {
            if (!(sender instanceof Player)) {
                sender.sendMessage("Este comando só pode ser executado por jogadores.");
                return true;
            }
            Player player = (Player) sender;
            if (args.length != 1) {
                player.sendMessage("Use: /warp <nome do warp>");
                return true;
            }
            // Recupera as coordenadas do warp do arquivo de configuração
            File configFile = new File(getDataFolder(), "warps.yml");
            YamlConfiguration config = YamlConfiguration.loadConfiguration(configFile);
            Location location = config.getLocation("warps." + args[0]);
            if (location == null) {
                player.sendMessage("Warp " + args[0] + " não existe.");
                return true;
            }
            // Teleporta o jogador para o warp
            player.teleport(location);
            player.sendMessage("Teleportado para " + args[0] + ".");
            return true;
        }
        return false;
    }
}


