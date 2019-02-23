package com.Pixeler.TropicalBaltop;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;

//thanks for code: https://github.com/PlaceholderAPI/PlaceholderAPI/wiki/Hook-into-PlaceholderAPI#using-placeholder-expansion-to-register-placeholders-in-your-local-jar
public class PAPI extends PlaceholderExpansion {

	/*
	 * The identifier, shouldn't contain any _ or %
	 */
	public String getIdentifier() {
		return getAuthor().toLowerCase();
	}

	public String getPlugin() {
		return null;
	}

	/*
	 * The author of the Placeholder This cannot be null
	 */
	public String getAuthor() {
		return TropicalBaltop.getInstance().pdf.getName();
	}

	/*
	 * Same with #getAuthor() but for version This cannot be null
	 */
	public String getVersion() {
		return TropicalBaltop.getInstance().pdf.getVersion();
	}

	/*
	 * Use this method to setup placeholders This is somewhat similar to
	 * EZPlaceholderhook
	 */
	public String onPlaceholderRequest(Player player, String identifier) { // tropicalbaltop_baltop_1_user
																			// tropicalbaltop_baltop_1_balance

		// We check if the player is null (not online) before any player-related
		// placeholder
		if (player == null) {
			return "";
		}

		String[] args = identifier.split("_");
		if (args[0].equalsIgnoreCase("baltop") && args.length == 3) {
			try {
				int place = Integer.parseInt(args[1]);
				if (args[2].equalsIgnoreCase("user")) {
					for (OfflinePlayer op : TropicalBaltop.getInstance().top.moneyAmounts.get(place).keySet()) {
						return op.getName();
					}
				} else if (args[2].equalsIgnoreCase("balance")) {
					for (Double money : TropicalBaltop.getInstance().top.moneyAmounts.get(place).values()) {
						return String.format("%." + TropicalBaltop.getInstance().decimalPlaces + "f", money);
					}
				}

			} catch (NumberFormatException e) {
				TropicalBaltop.getInstance().getLogger().warning("An error occurred when handling placeholder tropicalbaltop_" + identifier + ". Second argument " + args[1] + " is not a number.");
				e.printStackTrace();
			} catch (NullPointerException e2) {
				e2.printStackTrace();
			}
		}

		// We return null, if an invalid placeholder was called.
		return null;
	}
}