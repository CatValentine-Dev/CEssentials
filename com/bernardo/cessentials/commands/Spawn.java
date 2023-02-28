package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.main.Config;
import com.bernardo.cessentials.main.Main;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {

    private final Main plugin;

    public Spawn(Main plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("spawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                FileConfiguration config = plugin.getConfig();
                String world = config.getString("spawn.world");
                double x = config.getDouble("spawn.x");
                double y = config.getDouble("spawn.y");
                double z = config.getDouble("spawn.z");
                float yaw = (float) config.getDouble("spawn.yaw");
                float pitch = (float) config.getDouble("spawn.pitch");
                Location location = new Location(player.getServer().getWorld(world), x, y, z, yaw, pitch);
                player.teleport(location);
                player.sendMessage(Config.spawnjoin);
            } else {
                sender.sendMessage("Comando so pode ser executado por jogadores.");
            }
            return true;
        }
        return false;
    }
}