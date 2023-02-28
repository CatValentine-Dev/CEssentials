package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.ultilidade.Strings;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpcancel implements CommandExecutor {
    private HashMap<Player, Player> requests;

    public Tpcancel(HashMap<Player, Player> requests) {
        this.requests = requests;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§eEste comando só pode ser usado por jogadores.");
            return false;
        }

        Player player = (Player) sender;

        if(!player.hasPermission("cessentials.tpcancel")){
            player.sendMessage(Strings.prefix + "§cSem Permissão");
            return false;
        }

        if (requests.containsKey(player)) {
            Player requester = requests.get(player);
            player.sendMessage(Strings.prefix + "§cPedido de teletransporte cancelado.");
            requester.sendMessage(Strings.prefix + player.getName() + " §ecancelou o pedido de teletransporte.");
            requests.remove(player); // Remove o pedido da lista de pedidos pendentes
        } else {
            player.sendMessage(Strings.prefix + "§cNão há nenhum pedido de teletransporte pendente.");
        }

        return true;
    }
}