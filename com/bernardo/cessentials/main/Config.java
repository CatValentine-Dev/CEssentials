package com.bernardo.cessentials.main;

public class Config {
    public static String joinmsg = Main.getInstance().getConfig().getString("joinmsg").replace("&", "§");
    public static String setspawn = Main.getInstance().getConfig().getString("setspawn").replace("&", "§");
    public static String spawnjoin = Main.getInstance().getConfig().getString("spawnjoin").replace("&", "§");

    public static String motdlinha1 = Main.getInstance().getConfig().getString("motdlinha1").replace("&", "§");

    public static String motdlinha2 = Main.getInstance().getConfig().getString("motdlinha2").replace("&", "§");

    public static String porcao_empilhada_msg = Main.getInstance().getConfig().getString("porcao_empilhada_msg").replace("&", "§");

    public static String sem_porcao_inv_msg = Main.getInstance().getConfig().getString("sem_porcao_inv_msg").replace("&", "§");

    public static String gm_msg = Main.getInstance().getConfig().getString("gm_msg").replace("&", "§");

    public static String sethomemsg = Main.getInstance().getConfig().getString("sethomemsg").replace("&", "§");

    public static String homemsg = Main.getInstance().getConfig().getString("homemsg").replace("&", "§");

    public Config() {
    }
}