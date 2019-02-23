# TropicalBaltop
TropicalBaltop is a PlaceholderAPI extension designed for TropicalMC (but any server may use it)

Requires PlaceholderAPI and Vault

Placeholders:
%tropicalbaltop_baltop_<place>_user% - The username who has the <place> most money.
%tropicalbatop_baltop_<place>_balance% - The money of the player who was the <place> most money.

Examples:
Assume these are the top 3 players:
player1 who has $10,000
player2 who has $5,000
player3 who has $2,000
%tropicalbaltop_baltop_1_user% - The username of the player who has the most money. (player1)
%tropicalbaltop_baltop_3_balance% - The amount of money the player who has the 3rd most money has. (1000)

Config:
decimalPlaces - The amount of decimals of precision. (default: 2)
debug - Whether or not to show debug messages in console, you shouldn't ever need to use this. (default: false)

You may copy parts or all of the source code to use in your own plugin (or other software) comercially or non-comercially.
You do not have to credit me if you use this, but you may not claim you made the plugin.
You must have my permission before uploading the plugin anywhere.
