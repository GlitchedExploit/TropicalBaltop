package com.Pixeler.TropicalBaltop;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

import net.milkbowl.vault.economy.Economy;

public class Top {

	private Economy econ = TropicalBaltop.getEconomy();
	private HashMap<OfflinePlayer, Double> unsortedTempAmounts = new HashMap<OfflinePlayer, Double>();
	HashMap<OfflinePlayer, Double> unsortedAmounts = new HashMap<OfflinePlayer, Double>();
	HashMap<Integer, HashMap<OfflinePlayer, Double>> moneyAmounts = new HashMap<Integer, HashMap<OfflinePlayer, Double>>();
	
	Top() {
		unsortedTempAmounts = new HashMap<OfflinePlayer, Double>();
		unsortedAmounts = new HashMap<OfflinePlayer, Double>();
	}
	
	void getTopPlayers() {
		unsortedTempAmounts.clear();
		for (OfflinePlayer op : Bukkit.getOfflinePlayers()) {
			if (econ.hasAccount(op)) {
				unsortedTempAmounts.put(op, econ.getBalance(op));
			}
		}
		
		unsortedAmounts = unsortedTempAmounts;
		
		double[] ints = unsortedTempAmounts.values().stream().mapToDouble(Double::intValue).toArray(); // convert values to int array
		Arrays.sort(ints);
		ArrayUtils.reverse(ints); // so its descending not ascending
		HashMap<Integer, HashMap<OfflinePlayer, Double>> finalAmounts = new HashMap<Integer, HashMap<OfflinePlayer, Double>>();
		
		for (int i = 0; i < ints.length; i++) { // need i for finalAmounts key
			HashMap<OfflinePlayer, Double> value = new HashMap<OfflinePlayer, Double>();
			OfflinePlayer key = getKey(ints[i]);
			if (key == null) continue;
			value.put(key, ints[i]);
			TropicalBaltop.getInstance().debug("Baltop player " + (i+1) + " is " + key.getName() + " and they have " + ints[i] + " money");
			finalAmounts.put(i+1, value);
		}

		moneyAmounts = finalAmounts;
	}
	
	private OfflinePlayer getKey(Double value) {
		for(Entry<OfflinePlayer, Double> entry: unsortedTempAmounts.entrySet()){
            if(value == entry.getValue()) {
            	unsortedTempAmounts.remove(entry.getKey());
                return entry.getKey();
            }
        }
		return null;
	}
}
