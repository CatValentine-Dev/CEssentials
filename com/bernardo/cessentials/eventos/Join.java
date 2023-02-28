package com.bernardo.cessentials.eventos;

import com.bernardo.cessentials.commands.Spawn;
import com.bernardo.cessentials.main.Config;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    public Join() {
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        e.setJoinMessage(Config.joinmsg + "§f・" + p.getName());
    }
}