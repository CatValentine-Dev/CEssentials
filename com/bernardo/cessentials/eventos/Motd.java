package com.bernardo.cessentials.eventos;

import com.bernardo.cessentials.main.Config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class Motd implements Listener {
    public Motd() {
    }

    @EventHandler
    public void onMotd(ServerListPingEvent e) {
        e.setMotd(String.valueOf(Config.motdlinha1 + "\n" + Config.motdlinha2));
    }
}