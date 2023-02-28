package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.main.Config;
import com.bernardo.cessentials.main.Main;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SetSpawn implements CommandExecutor {

    private final Main plugin;

    public SetSpawn(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                FileConfiguration config = plugin.getConfig();
                config.set("spawn.world", player.getLocation().getWorld().getName());
                config.set("spawn.x", player.getLocation().getX());
                config.set("spawn.y", player.getLocation().getY());
                config.set("spawn.z", player.getLocation().getZ());
                config.set("spawn.yaw", player.getLocation().getYaw());
                config.set("spawn.pitch", player.getLocation().getPitch());
                plugin.saveConfig();
                player.sendMessage(Config.setspawn);
            } else {
                sender.sendMessage("Comando apenas executado por jogadores!");
            }
            return true;
        }
        return false;
    }
}