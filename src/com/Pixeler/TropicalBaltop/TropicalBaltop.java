package com.Pixeler.TropicalBaltop;

import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import net.milkbowl.vault.economy.Economy;

public class TropicalBaltop extends JavaPlugin {

	private static TropicalBaltop inst;
	public static TropicalBaltop getInstance() { return inst; }
	public PluginDescriptionFile pdf = getDescription();
	private static Economy econ = null;
	public Top top;
	
	public int decimalPlaces;
	public boolean debug;
	
	public void onEnable() {
		inst = this;
		
		saveDefaultConfig();
		getConfig().options().copyDefaults(true);
		
		decimalPlaces = getConfig().getInt("decimalPlaces");
		debug = getConfig().getBoolean("debug");
		
		if (!setupEconomy() || getServer().getPluginManager().getPlugin("PlaceholderAPI") == null) {
			getServer().getPluginManager().disablePlugin(this);
			return;
		}
		
		top = new Top();
		top.getTopPlayers();
		
		new BukkitRunnable() {
			@Override
			public void run() {
				top.getTopPlayers();
			}
		}.runTaskTimerAsynchronously(this, 0, 20*60); // 1 minute
		
		new PAPI().register();
	}
	
	public void debug(String message) {
		if (debug) getLogger().info("TropicalBaltop - DEBUG: " + message);
	}
	
	private boolean setupEconomy() {
        if (getServer().getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }
	
	public static Economy getEconomy() {
        return econ;
    }
}
