package me.legrid.newplugin;

import org.bukkit.attribute.Attribute;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;


public final class NewPlugin extends JavaPlugin implements Listener {

    private ArrayList<Player> armletPlayerList = new ArrayList<>();

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("armlet"))
            if (sender instanceof Player) {
                Player p = (Player) sender;
                if(armletPlayerList.contains(p)){
                    armletPlayerList.remove(p);
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(20);
                    p.setHealth(3);
                    p.removePotionEffect(PotionEffectType.WITHER);
                    p.sendMessage("Армлет выключен");
                } else if (!armletPlayerList.contains(p)){
                    armletPlayerList.add(p);
                    p.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(10);
                    p.setHealth(6);
                    p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 30, 1));
                    p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 60000, 10));
                    p.removePotionEffect(PotionEffectType.SPEED);
                    p.removePotionEffect(PotionEffectType.REGENERATION);
                    p.sendMessage("Армлет включен");
                }
            }
        return true;
    }}



