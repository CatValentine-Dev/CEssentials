package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.ultilidade.Strings;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class Tpa implements CommandExecutor {
    private HashMap<Player, Player> requests;

    public Tpa(HashMap<Player, Player> requests) {
        this.requests = requests;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Strings.prefix + "§eEste comando só pode ser usado por jogadores.");
            return false;
        }

        Player player = (Player) sender;

        if (!player.hasPermission("cessentials.tpa")){
            player.sendMessage(Strings.prefix + "§cSem Permissão");
            return false;
        }

        if (args.length != 1) {
            sender.sendMessage(Strings.prefix + "§cUse: /tpa <jogador>");
            return false;
        }

        Player target = player.getServer().getPlayer(args[0]);

        if (target == null) {
            player.sendMessage(Strings.prefix + "§eJogador não encontrado.");
            return false;
        }

        if (player == target) {
            player.sendMessage(Strings.prefix + "§eVocê não pode enviar um pedido de teletransporte para si mesmo.");
            return false;
        }

        if (requests.containsKey(target)) {
            player.sendMessage(Strings.prefix + "§eEste jogador já tem um pedido de teletransporte pendente.");
            return false;
        }

        requests.put(target, player);
        player.sendMessage(ChatColor.GREEN + "Pedido de teletransporte enviado para " + target.getName() + ".");
        target.sendMessage(ChatColor.GREEN + "Você recebeu um pedido de teletransporte de " + player.getName() + ".");
        target.sendMessage(ChatColor.GREEN + "Use /tpaccept para aceitar ou /tpcancel para recusar.");
        return true;
    }
}