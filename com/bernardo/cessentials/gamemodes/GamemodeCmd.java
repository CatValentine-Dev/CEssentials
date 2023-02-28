package com.bernardo.cessentials.gamemodes;


import com.bernardo.cessentials.ultilidade.Strings;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;


public class GamemodeCmd implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        if(sender instanceof Player){
            Player p = (Player) sender;
            if(!p.hasPermission("cessentials.gamemode")){
                p.sendMessage(Strings.noPerms);
            }else{
                if(args.length == 0){
                    p.sendMessage(Strings.prefix + "§aUse: §f/gm §e<survival§f|§ecreative§f|§eadventure§f|§espectator> §7[player] §b- Seta o modo de jogo de um jogador específico");
                }else if(args.length == 1){
                    String ar0 = args[0];
                    if(ar0.equalsIgnoreCase("survival") || ar0.equalsIgnoreCase("0")){
                        if(!p.hasPermission("cessentials.survival") && !p.hasPermission("cessentials.survival.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.SURVIVAL);
                            p.sendMessage(Strings.prefix +"§bModo de jogo Setado §csurvival §bpara §f" + p.getName() + "§b.");
                        }
                    }else if(ar0.equalsIgnoreCase("creative") || ar0.equalsIgnoreCase("1")){
                        if(!p.hasPermission("cessentials.creative") && !p.hasPermission("cessentials.creative.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.CREATIVE);
                            p.sendMessage(Strings.prefix +"§bModo de jogo Setado §ccreative §bpara §f" + p.getName() + "§b.");
                        }
                    }else if(ar0.equalsIgnoreCase("adventure") || ar0.equalsIgnoreCase("2")){
                        if(!p.hasPermission("cessentials.adventure") && !p.hasPermission("cessentials.adventure.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.ADVENTURE);
                            p.sendMessage(Strings.prefix +"§bModo de jogo Setado §cadventure §bpara §f" + p.getName() + "§b.");
                        }
                    }else if(ar0.equalsIgnoreCase("spectator") || ar0.equalsIgnoreCase("spec") || ar0.equalsIgnoreCase("3")){
                        if(!p.hasPermission("cessentials.spectator") && !p.hasPermission("cessentials.spectator.self")){
                            p.sendMessage(Strings.noPerms);
                        }else{
                            p.setGameMode(GameMode.SPECTATOR);
                            p.sendMessage(Strings.prefix +"§bModo de jogo Setado §cspectator §bpara §f" + p.getName() + "§b.");
                        }
                    }else{
                        p.sendMessage(Strings.prefix +"§aUse: §f/gm §e<survival§f|§ecreative§f|§eadventure§f|§espectator> §7[player] §b- Seta o modo de jogo de um jogador específico");
                    }
                }else if(args.length == 2){
                    Player t = Bukkit.getPlayer(args[1]);
                    if(t != null){
                        String ar0 = args[0];
                        if(ar0.equalsIgnoreCase("survival") || ar0.equalsIgnoreCase("0")){
                            if(!p.hasPermission("cessentials.survival") && !p.hasPermission("cessentials.survival.outro")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.SURVIVAL);
                                p.sendMessage(Strings.prefix +"§bModo de jogo Setado §csurvival §apara §f" + t.getName() + "§b.");
                            }
                        }else if(ar0.equalsIgnoreCase("creative") || ar0.equalsIgnoreCase("1")){
                            if(!p.hasPermission(Strings.prefix +"cessentials.creative") && !p.hasPermission("cessentials.creative.outro")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.CREATIVE);
                                p.sendMessage(Strings.prefix +"§bModo de jogo Setado §ccreative §apara §f" + t.getName() + "§b.");
                            }
                        }else if(ar0.equalsIgnoreCase("adventure") || ar0.equalsIgnoreCase("2")){
                            if(!p.hasPermission(Strings.prefix +"cessentials.adventure") && !p.hasPermission("cessentials.adventure.outro")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.ADVENTURE);
                                p.sendMessage(Strings.prefix +"§bModo de jogo Setado §cadventure §apara §f" + t.getName() + "§b.");
                            }
                        }else if(ar0.equalsIgnoreCase("spectator") || ar0.equalsIgnoreCase("spec") || ar0.equalsIgnoreCase("3")){
                            if(!p.hasPermission("cessentials.spectator") && !p.hasPermission("cessentials.spectator.outro")){
                                p.sendMessage(Strings.noPerms);
                            }else{
                                t.setGameMode(GameMode.SPECTATOR);
                                p.sendMessage(Strings.prefix +"§bModo de jogo Setado §cspectator §apara §f" + t.getName() + "§b.");
                            }
                        }else{
                            p.sendMessage(Strings.prefix +"§aUse: §f/gm §e<survival§f|§ecreative§f|§eadventure§f|§espectator> §7[player] §b- Seta o modo de jogo de um jogador específico");
                        }
                    }else{
                        p.sendMessage(Strings.prefix +"§cErro &f・ §4Jogador Nao Encontrado.");
                    }
                }else{
                    p.sendMessage(Strings.prefix +"§aUse: §f/gm §e<survival§f|§ecreative§f|§eadventure§f|§espectator> §7[player] §b- Seta o modo de jogo de um jogador específico");
                }
            }
        }

        return true;
    }
}