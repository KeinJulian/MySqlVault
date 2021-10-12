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
 * Erstellt am: 10.10.2021
 */

public class EcoCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("eco")) { // Falls der empfangene Command "/eco" ist
            if (sender.hasPermission("vault.eco.modify")) { // Falls der Absender die nötigen Rechte hat
                if (args.length == 3) { // Falls der empfangene Befel 4 Wörter enthält
                    Player t = Bukkit.getPlayer(args[1]); // Definiere den Target-Spieler
                    if (t != null) { // Falls der Target-Spieler online ist
                        if (HelperClass.isDouble(args[2])) { // Überprüfe mithilfe der "isDouble()" Methode, ober der angegebene Betrag einem Double entspricht
                            if (args[0].equalsIgnoreCase("set")) { // Falls das erste Wort nach "/eco" / der Subcommand "set" ist
                                if (HelperClass.getMoney(t.getUniqueId().toString()).equalsIgnoreCase("-1")) { // Überprüfe, ob bereits ein Eintrag für diesen Spieler in der Datenbank enthalten ist
                                    HelperClass.setMoney(t.getUniqueId().toString(), Double.valueOf(args[2]), false); // Erstelle einen Eintrag
                                } else {                                                                                    // Falls bereits einer vorhanden ist
                                    HelperClass.setMoney(t.getUniqueId().toString(), Double.valueOf(args[2]), true); // Update den Eintrag
                                }
                                sender.sendMessage(Main.prefix + "§aDas Geld von §6" + t.getName() + "§a wurde angepasst."); // Gebe dem Absender eine Nachricht aus
                            } else if (args[0].equalsIgnoreCase("give")) { // Falls das erste Wort nach "/eco" / der Subcommand "set" ist
                                HelperClass.addMoney(t, Double.valueOf(args[2].replace("-", ""))); // Füge dem Target-Spieler den Inhalt von dem 3. Wort des eingegebenen Commands als Geld hinzu
                                sender.sendMessage(Main.prefix + "§aDas Geld von §6" + t.getName() + "§a wurde angepasst."); // Gebe dem Absender eine Nachricht aus
                            } else { // Falls nichts davon zutrifft
                                sender.sendMessage(Main.prefix + "§cBitte benutze \"/eco (set/give) (Spielername) (Betrag)\""); // Gebe dem Absender eine Nachricht aus
                            }
                        } else { // Falls der Betrag kein Double ist
                            sender.sendMessage(Main.prefix + "§cUngültiger Betrag."); // Gebe dem Absender eine Nachricht aus
                        }
                    } else { // Fals der Target-Spieler nicht online ist
                        sender.sendMessage(Main.prefix + "§cDieser Spieler ist nicht online."); // Gebe dem Absender eine Nachricht aus
                    }
                } else { // Falls die Anzahl der Wörter nicht 4 Beträgt
                    sender.sendMessage(Main.prefix + "§cBitte benutze \"/eco (set/give) (Spielername) (Betrag)\""); // Gebe dem Absender eine Nachricht aus
                }
            } else { // Falls der Absender keine Berechtigung zum nutzen dieses Befehls hat
                sender.sendMessage(Main.noPerms); // Gebe dem Absender eine Nachricht aus
            }
        }
        return false;
    }

}
