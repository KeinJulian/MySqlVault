package de.vault.main;

import de.vault.commands.EcoCommand;
import de.vault.commands.MoneyCommand;
import de.vault.commands.PayCommand;
import de.vault.mysql.MySql;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

/**
 * Autor: KeinJulian
 * Version: 1.0.0
 * Erstellt am: 12.10.2021
 */

public class Main extends JavaPlugin {

    public static String prefix = "§8[§6MySqlVault§8] ";
    public static String noPerms = "§8[§6MySqlVault§8] §cDir fehlen leider die Berechtigungen, um diesen Befehl auszuführen.";

    @Override
    public void onEnable() {
        super.onEnable();

        File file = new File ("plugins/MySqlVault", "config.yml"); // Config-Datei
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        MySql.setDefaults(); // Setze die Stanarteingaben für die Configdatei, falls noch nicht vorhanden
        MySql.username = (String) cfg.get("username"); // Daten aus der Config-Datei laden.
        MySql.password = (String) cfg.get("password");
        MySql.database = (String) cfg.get("database");
        MySql.host = (String) cfg.get("host");
        MySql.port = (String) cfg.get("port");

        MySql.connect(); // Verbinde zum MySql-Server
        MySql.tableVault(); // Prüfe, ob die Tabelle "Vault" schon existiert. Falls nicht, wird eine erstellt.

        this.getCommand("money").setExecutor(new MoneyCommand()); // Commands werden hinzugefügt
        this.getCommand("eco").setExecutor(new EcoCommand());
        this.getCommand("pay").setExecutor(new PayCommand());
    }
}
