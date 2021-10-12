package de.vault.commands;

import de.vault.helpers.HelperClass;
import de.vault.main.Main;
import org.bukkit.Bukkit;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

/**
 * Autor: KeinJulian
 * Version: 1.0.0
 * Erstellt am: 12.10.2021
 */

public class MoneyCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("money")) { // Falls der eingegebene Befehl "/money" ist
            if (sender instanceof Player) { // Falls der Absender ein Spieler ist
                Player p = (Player) sender; // Definiere den Spieler
                if (args.length == 1) { // Falls die Anzahl der Wörter "2" ist
                    if (p.hasPermission("vault.money.others")) { // Falls der Spieler die nötige Permission hat
                        Player t = Bukkit.getPlayer(args[0]); // Definiere den Target-Spieler
                        if (t != null) { // Falls der Target-Spieler online ist
                            p.sendMessage(Main.prefix + "§aKontostand von §6" + t.getName() + "§8: §a$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(HelperClass.getMoney(t.getUniqueId().toString()).replace("-1", "0"))))); // Sende dem Absender den Kontostand des Target-Spielers
                        } else { // Falls der Target-Spieler offline ist
                            sender.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online."); // Sende dem Absender eine Nachricht
                        }
                    } else { // Falls der Spieler nicht die nötigen Rechte hat
                        p.sendMessage(Main.prefix + "§aDein Kontostand§8: §a$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(HelperClass.getMoney(p.getUniqueId().toString()).replace("-1", "0"))))); // Sende dem Absender seinen eigenen Kontostand zu
                    }
                } else { // Falls kein Target-Spieler angegeben ist
                    p.sendMessage(Main.prefix + "§aDein Kontostand§8: §a$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(HelperClass.getMoney(p.getUniqueId().toString()).replace("-1", "0"))))); // Sende dem Absender seinen eigenen Kontostand zu
                }
            } else { // Falls der Absender die Konsole ist
                if (args.length == 1) { // Gleicher Code, wie oben
                    Player t = Bukkit.getPlayer(args[0]);
                    if (t != null) {
                        sender.sendMessage(Main.prefix + "§aKontostand von §6" + t.getName() + "§8: §a$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(HelperClass.getMoney(t.getUniqueId().toString()).replace("-1", "0")))));
                    } else {
                        sender.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online.");
                    }
                } else {
                    sender.sendMessage(Main.prefix + "§aDein Kontostand§8: §a$unlimited");
                }
            }
        }
        return false;
    }

}
