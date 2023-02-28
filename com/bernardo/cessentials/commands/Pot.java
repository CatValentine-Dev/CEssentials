package com.bernardo.cessentials.commands;

import com.bernardo.cessentials.api.CommandBase;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
import com.bernardo.cessentials.main.Config;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class Pot extends CommandBase {

    public Pot(){
    }

    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!command.getName().equals("cpot")) {
            return false;
        } else if (!(sender instanceof Player)) {
            sender.sendMessage("§c[X]§f・§cComando apenas para Jogadores");
            return true;
        } else {
            Player p = (Player)sender;
            Map<ItemStack, Integer> potionMap = new LinkedHashMap();

            for(int i = 0; i < p.getInventory().getSize(); ++i) {
                ItemStack item = p.getInventory().getItem(i);
                if (item != null && item.getType() == Material.POTION && !Potion.fromItemStack(item).isSplash() && item.getDurability() != 0) {
                    ItemStack contains = null;
                    Iterator var11 = potionMap.keySet().iterator();

                    while(var11.hasNext()) {
                        ItemStack stack = (ItemStack)var11.next();
                        if (stack.getDurability() == item.getDurability() && stack.getItemMeta().equals(item.getItemMeta())) {
                            contains = stack;
                            break;
                        }
                    }

                    if (contains != null) {
                        potionMap.put(contains, (Integer)potionMap.get(contains) + item.getAmount());
                    } else {
                        potionMap.put(item, item.getAmount());
                    }
                }
            }

            if (potionMap.isEmpty()) {
                p.sendMessage(String.valueOf(Config.sem_porcao_inv_msg));
                return true;

            } else {
                ItemStack[] items = p.getInventory().getContents();

                for(int i = 0; i < items.length; ++i) {
                    if (items[i] != null && items[i].getType() == Material.POTION && !Potion.fromItemStack(items[i]).isSplash() && items[i].getDurability() != 0) {
                        p.getInventory().clear(i);
                    }
                }

                Iterator var15 = potionMap.entrySet().iterator();

                while(var15.hasNext()) {
                    Map.Entry<ItemStack, Integer> entry = (Map.Entry)var15.next();
                    ItemStack stack = (ItemStack)entry.getKey();
                    stack.setAmount((Integer)entry.getValue());
                    p.getInventory().addItem(new ItemStack[]{stack});
                }

                p.updateInventory();
                p.sendMessage(String.valueOf(Config.porcao_empilhada_msg));
                return true;
            }
        }
    }
}

