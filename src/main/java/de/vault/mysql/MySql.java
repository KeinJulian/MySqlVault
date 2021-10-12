package de.vault.mysql;

import de.vault.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Autor: KeinJulian
 * Version: 1.0.0
 * Erstellt am: 12.10.2021
 */

public class MySql {

    public static String username;
    public static String password;
    public static String database;
    public static String host;
    public static String port;

    public static Connection con;

    public static void setDefaults() {
        File file = new File("plugins/MySqlVault", "config.yml"); // Config-Datei
        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file); // Config-Datei

        // Hinzufügen der Standart-Werte in die Config

        cfg.options().copyDefaults(true);

        cfg.addDefault("username", "plugin");
        cfg.addDefault("password", "password");
        cfg.addDefault("database", "Vault");
        cfg.addDefault("host", "localhost");
        cfg.addDefault("port", "3306");

        // Versuche die Datei zu speichern. Falls ein Fehler auftritt, wird er ausgegeben.
        try {
            cfg.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void connect() {
        if (!isConnected()) { // Falls der Boolean isConnected false ist
            try {
                con = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database, username, password); // Baue eine verbindung auf
                Bukkit.getConsoleSender().sendMessage(Main.prefix + "§fDie MySql-Verbindung wurde erfolgreich aufgebaut."); // Gebe eine bestätigung aus
            } catch (SQLException e) {
                e.printStackTrace(); // Falls ein Fehler auftritt, gebe ihn aus.
                Bukkit.getConsoleSender().sendMessage(e.toString()); // Falls ein Fehler auftritt, gebe ihn aus.
            }
        }
    }

    public static void close() {
        if (isConnected()) { // Falls der Boolean isConnected false ist
            try {
                con.close(); // Schließe die Verbindung
                Bukkit.getConsoleSender().sendMessage(Main.prefix + "§fDie MySql-Verbindung wurde erfolgreich getrennt."); // Gebe eine bestätigung aus
            } catch (SQLException e) {
                e.printStackTrace(); // Falls ein Fehler auftritt, gebe ihn aus.
            }
        }
    }

    public static boolean isConnected() {
        return con != null; // Gebe die Connection zurück sofern diese nicht null ist.
    }

    public static void tableVault() {
        if (isConnected()) { // Falls der boolean isConnected true ist

            try {
                con.createStatement().executeUpdate("CREATE TABLE IF NOT EXISTS Vault (UUID VARCHAR(100), Money VARCHAR(100))"); // Erstelle eine Tabelle, sofern diese noch nicht vorhanden ist
                Bukkit.getConsoleSender().sendMessage(Main.prefix + "§fTable-Überprüfung erfolgreich."); // Gebe eine Nachricht aus
            } catch (Exception e) {
                // Falls ein Fehler passiert, passiert nichts
            }

        }
    }

    public static void update (String qry) {
        if (isConnected()) { // Falls eine verbindung besteht
            try { // Versuche
                con.createStatement().executeUpdate(qry); // MySql befehl mit dem Inhalt von dem String qry absenden
            } catch (SQLException e) {
                e.printStackTrace(); // Falls ein Fehler passiert, gebe ihn aus.
            }
        }
    }

    public static ResultSet getResult (String qry) {
        if (isConnected()) { // Falls eine verbindung besteht
            try { // Versuche
                return con.createStatement().executeQuery(qry); // Gebe das Ergebnis der Anfrage aus
            } catch (SQLException e) {
                e.printStackTrace(); // Falls ein Fehler passiert, gebe ihn aus
            }
        }
        return null; // Falls kein Ergebnis der Anfrage vorhanden ist, gebe null aus
    }

}
