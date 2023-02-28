package com.bernardo.cessentials.commands;

import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class voar implements CommandExecutor {
    private boolean modovoar = false;

    public voar() {
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player)sender;
            if (player.hasPermission("cessentials.voar")) {
                if (command.getName().equalsIgnoreCase("voar")) {
                    if (!this.modovoar) {
                        player.setAllowFlight(true);
                        this.modovoar = true;
                        player.playSound(player.getLocation(), Sound.LEVEL_UP, 1.0F, 1.0F);
                        player.sendMessage("§a[+]§f・§aModo Voar Ativado!");
                    } else {
                        player.setAllowFlight(false);
                        this.modovoar = false;
                        player.playSound(player.getLocation(), Sound.VILLAGER_YES, 1.0F, 1.0F);
                        player.sendMessage("§c[X]§f・§cModo Voar Desativado");
                    }
                }
            } else {
                player.sendMessage("§cVocê não tem permissão para fazer isso!");
            }
        } else {
            sender.sendMessage("§cEsse comando deve ser executado por um jogador!");
        }

        return false;
    }
}
