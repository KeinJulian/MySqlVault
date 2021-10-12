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

public class PayCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("pay")) { // Falls er erhaltene Befehl "/pay" ist
            if (args.length == 2) { // Falls die Anzahl der Wörter 3 ist
                Player t = Bukkit.getPlayer(args[0]); // Definiere den Target-Spieler
                if (t != null) { // Falls der Target-Spieler online ist
                    if (sender instanceof Player) { // Falls der Command-Sender ein Spieler ist
                        Player p = (Player) sender; // Definiere den Spieler
                        if (HelperClass.isDouble(args[1])) { // Überprüfe mithilfe der "isDouble()" Methode, ober der angegebene Betrag einem Double entspricht
                            if (Double.valueOf(HelperClass.getMoney(p.getUniqueId().toString())) >= Double.valueOf(args[1].replace("-", ""))) { // Falls Der Kontostand ausreicht
                                HelperClass.addMoney(p, HelperClass.shorten(Double.valueOf("-" + args[1].replace("-", "")))); // Füge dem Zahler eine Negative Summe hinzu
                                HelperClass.addMoney(t, HelperClass.shorten(Double.valueOf(args[1].replace("-", "")))); // Füge dem Empfänger eine positive Summe hinzu
                                p.sendMessage(Main.prefix + "§aDu hast §6" + t.getName() + "§a einen Betrag von §6$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(args[1]))) + "§a transferiert."); // sende den Zahler eine Nachricht
                                t.sendMessage(Main.prefix + "§aDu hast §6$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(args[1]))) + "§a von §6" + p.getName() + " §aerhalten."); // Sende dem Empfänger eine Nachricht
                            } else { // Falls das Vermögen nicht ausreicht
                                p.sendMessage(Main.prefix + "§cDein Vermögen reicht für diese Transaktion leider nicht aus."); // Sende dem Zahler eine Nachricht
                            }
                        } else { // Falls der Betrag kein Double ist
                            p.sendMessage(Main.prefix + "§cUngültiger Betrag."); // Gebe dem Absender eine Nachricht aus
                        }
                    } else { // Falls der Sender kein Spieler ist, sondern die Konsole/ein Command-Block
                        if (HelperClass.isDouble(args[1])) { // Überprüfe mithilfe der "isDouble()" Methode, ober der angegebene Betrag einem Double entspricht
                            HelperClass.addMoney(t, Double.valueOf(args[1].replace("-", ""))); // Füge dem Empfänger eine Summe hinzu
                            sender.sendMessage(Main.prefix + "§aDu hast §6" + t.getName() + "§a einen Betrag von §6$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(args[1]))) + "§a transferiert."); // Sende der Konsole eine Nachricht
                            t.sendMessage(Main.prefix + "§aDu hast §6$" + HelperClass.getFormatted(HelperClass.shorten(Double.valueOf(args[1]))) + "§a von §6KONSOLE §aerhalten."); // Sende dem Spieler eine Nachricht
                        } else { // Falls der Betrag kein Double ist
                            sender.sendMessage(Main.prefix + "§cUngültiger Betrag."); // Gebe dem Absender eine Nachricht aus
                        }
                    }
                } else { // Falls der Target-Spieler null ist, also nicht online ist
                    sender.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online."); // Sende dem Sender eine nachricht
                }
            } else {
                sender.sendMessage(Main.prefix + "§cBitte benutze \"/pay (Spielername) (Betrag)\""); // Falls die Anzahl der Wörter nicht 3 ist, sende dem Sender eine Nachricht
            }
        }
        return false;
    }

}
