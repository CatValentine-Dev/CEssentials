package com.bernardo.cessentials.main;

import com.bernardo.cessentials.commands.*;
import com.bernardo.cessentials.eventos.Join;
import com.bernardo.cessentials.eventos.Motd;
import com.bernardo.cessentials.gamemodes.*;
import com.bernardo.cessentials.ultilidade.Strings;
import com.bernardo.cessentials.ultilidade.UpdateChecker;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    private HashMap<Player, Player> requests = new HashMap<>();
    public static Main instance;
    public File spawn2;
    public YamlConfiguration spawn;

    public Main() {
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

    public void onLoad() {
        instance = this;
        Bukkit.getConsoleSender().sendMessage("§eCEssentials iniciando...");
    }

    private Map<String, Map<String, Location>> homes = new HashMap<>();
    private int maxHomes;

    public void onEnable() {
        requests = new HashMap<>();
        new UpdateChecker(this, 108070).getVersion(version -> {
            if (this.getDescription().getVersion().equals(version)) {
                Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§aNão há uma nova atualização disponível.");
            } else {
                Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§cHá uma nova atualização disponível.");
            }
        });
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§aCEssentials ativado.");
        Bukkit.getPluginManager().registerEvents(new Join(), this);
        Bukkit.getPluginManager().registerEvents(new Motd(), this);
        PluginCommand setSpawnCommand = getCommand("setspawn");
        setSpawnCommand.setExecutor(new SetSpawn(this));
        PluginCommand spawnCommand = getCommand("spawn");
        spawnCommand.setExecutor(new Spawn(this));
        getCommand("tpa").setExecutor(new Tpa(requests));
        getCommand("tpaccept").setExecutor(new Tpaccept(requests));
        getCommand("tpcancel").setExecutor(new Tpcancel(requests));
        PluginCommand setHome = getCommand("sethome");
        setHome.setExecutor(new SetHome(homes, maxHomes));
        PluginCommand home = getCommand("home");
        home.setExecutor(new Home(homes));
        this.getCommand("voar").setExecutor(new voar());
        this.getCommand("cpot").setExecutor(new Pot());
        this.getCommand("gamemode").setExecutor(new GamemodeCmd());
        saveDefaultConfig();
        this.maxHomes = this.getConfig().getInt("maximodehomes", 3);
        getServer().getConsoleSender().sendMessage(Strings.prefix + "§aIniciado com Sucesso!");
    }


    public void onDisable() {
        instance = null;
        Bukkit.getConsoleSender().sendMessage(Strings.prefix + "§cCEssentials desativado.");
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("setspawn")) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
                Location location = player.getLocation();
                FileConfiguration config = getConfig();
                config.set("spawn.world", location.getWorld().getName());
                config.set("spawn.x", location.getX());
                config.set("spawn.y", location.getY());
                config.set("spawn.z", location.getZ());
                config.set("spawn.yaw", location.getYaw());
                config.set("spawn.pitch", location.getPitch());
                saveConfig();
                player.sendMessage(Config.setspawn);
            } else {
                sender.sendMessage("Apenas jogadores podem executar o comando.");
            }
            return true;
        }
        return false;
    }
}
