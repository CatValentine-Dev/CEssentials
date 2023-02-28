package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.ultilidade.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpaccept implements CommandExecutor {
    private HashMap<Player, Player> requests;

    public Tpaccept(HashMap<Player, Player> requests) {
        this.requests = requests;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§eEste comando só pode ser usado por jogadores.");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("cessentials.tpaccept")){
            player.sendMessage(Strings.prefix + "§cSem Permissão");
            return false;
        }

        if (!requests.containsKey(player)) {
            player.sendMessage(Strings.prefix + "§eVocê não tem nenhum pedido de teletransporte pendente.");
            return false;
        }

        Player target = requests.get(player);
        requests.remove(player);

        target.teleport(player.getLocation());
        target.sendMessage(Strings.prefix + "§eTeleportado para " + player.getName() + ".");
        return true;
    }
}